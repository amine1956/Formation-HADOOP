package SparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;

public class AppStudentF_JSON {
    public static void main(String[] args)  throws Exception{
        SparkSession ss=SparkSession.builder().appName("tp1").master("local[*]").getOrCreate();
        Dataset<Row> df=ss.read().option("multiline",true).json("studen.json");
       // row n'import quel type dans fichier peut sekectionner
        df.show();
        Dataset<Row> df1=df.select("name","note");
        df1.show();
        df.select(col("name"),col("note").plus(1)).show();
        df.filter(col("note").gt(10).and(col("name").startsWith("a"))).show();
        df.createOrReplaceTempView("student");

        ss.sql("SELECT * FROM student WHERE note>=18").show(); // la vue crier apartire de d'autre table mais na pas stocker

    }
}

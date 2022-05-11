package SparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;

public class AppStudentF_CSV {
    public static void main(String[] args) {
        SparkSession ss=SparkSession.builder().appName("tp1").master("local[*]").getOrCreate();
        Dataset<Row> df=ss.read().option("header",true).csv("student.csv");
        df.show();
        df.select(col("note").cast("double")).show();
        df.filter(col("note").cast("double")
                .gt(10)).show();
        df.createOrReplaceTempView("student");

        ss.sql("SELECT * FROM student WHERE note>14").show(); // la vue crier apartire de d'autre table mais na pas stocker

    }
}


package SparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class AppDataFJson {
    public static void main(String[] args)  throws Exception{
        SparkSession ss=SparkSession.builder().appName("SparkF json").master("local[*]").getOrCreate();
        Dataset<Row> df=ss.read().option("multiline",true).json("Emplyer.json");
       // row n'import quel type dans fichier peut selectionner
        df.show();
        df.filter(col("age").gt(18).and(col("age").lt(35))).show();
        df.select(max("salary")).show();
        df.groupBy("departement").mean("salary").show();
        df.groupBy("departement").count().show();

    }
}

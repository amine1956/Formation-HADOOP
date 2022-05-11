package SparkSQL;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class AppDataFCSV {
    public static void main(String[] args) {
        SparkSession ss=SparkSession.builder().appName("SparkF csv").master("local[*]").getOrCreate();
        Dataset<Row> df=ss.read().option("header",true).csv("Employer.csv");
         df.filter(col("age").cast("double").gt(18).and(col("age").cast("double").lt(35))).show();
        df.select(max("salary")).show();
        df.select(col("departement"),col("salary").cast("double")).groupBy("departement").avg("salary").show();
        df.groupBy("departement").count().show();
    }
}


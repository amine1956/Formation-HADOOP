package SparkSQL;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.max;

public class AppDataStCSV {
    public static void main(String[] args) {
        SparkSession ss = SparkSession.builder().appName("SparkS csv").master("local[*]").getOrCreate();
        Encoder<Employer> employeeBeanEncoder = Encoders.bean(Employer.class);
        Dataset<Employer> ds = ss.read().format("csv").option("delimiter",",").option("header", true).option("charset", "UTF8") .option("inferSchema", "true").csv("Employer.csv").as(employeeBeanEncoder);
        System.out.println("age entre 30 et 35");
        ds.filter((FilterFunction<Employer>) employeeBean -> employeeBean.getAge()>=30 && employeeBean.getAge()<=35).show();
        System.out.println("salaire maximal");
        ds.select(max("salary")).show();
        System.out.println("le minimume des salires des departemebt ");
        ds.groupBy("departement").mean("salary").show();
        System.out.println("le numero des salries ");
        ds.groupBy("departement").count().show();


    }
}

package Exrcice3;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
public class Q1 {
    static Double a = Double.MAX_VALUE;
    static String b = null;
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("word count").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rdd1 = sc.textFile("1900.csv");
        JavaPairRDD<Double, String> rdd3 = rdd1.mapToPair(s -> {
            return new Tuple2<>(Double.parseDouble(s.split(",")[3]), s.split(",")[0]);
        });
        rdd3.foreach(nameTuple ->
        {
            if (nameTuple._1() < a) {
                a = nameTuple._1();
                b = nameTuple._2;
            }
        });
        System.out.println("la temperature minimal qui l'ID =" + b + "ET  " + a);
    }
}
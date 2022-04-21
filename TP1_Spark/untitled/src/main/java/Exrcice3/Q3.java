package Exrcice3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
public class Q3 {
    static Double a=0.0;
    static Double b=0.0;
    public static void main(String[] args) {
        SparkConf conf =new SparkConf().setAppName("word count").setMaster("local[*]");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=sc.textFile("1859.csv");
        JavaPairRDD<Double ,String> rdd3=rdd1.mapToPair(s->{
            return new Tuple2<>(Double.parseDouble(s.split(",")[3]), s.split(",")[2]);

        });
      JavaPairRDD<Double, String> rdd4=rdd3.filter(a -> a._2.equals("TMIN"));
        System.out.println(rdd4.collect());
        rdd4.foreach(nameTuple->
        {
            a= nameTuple._1()+a;

        });
        b=a/rdd4.count();
        System.out.println(b);
}
}

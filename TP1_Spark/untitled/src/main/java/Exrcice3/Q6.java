package Exrcice3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.*;
import java.util.function.DoubleUnaryOperator;

public class Q6 {
    static Double a1 = Double.MIN_VALUE;
    static String b = "";
    static HashMap<Double, String> capitalCities = new HashMap<Double, String>();



    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("word count").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rdd1 = sc.textFile("1859.csv");

        JavaRDD<String > RddId=rdd1.map(s -> s.split(",")[0]).distinct();
        System.out.println(RddId.collect());
        System.out.println(rdd1.count()+" "+RddId.count());
        JavaPairRDD<Double ,String> Last=rdd1.mapToPair(s->{
            return new Tuple2<>(Double.parseDouble(s.split(",")[3]), s.split(",")[0]);

        });
        List<String> IDs=RddId.take((int) RddId.count());
        JavaPairRDD<Double, String> finall ;

        for ( int i=0;i<RddId.count();i++) {

            String aze=IDs.get(i);
            JavaPairRDD<Double, String> FilterId = Last.filter(a -> a._2().equals(aze));
            System.out.println(FilterId.collect());
            FilterId.foreach(nameTuple->
            {
                if(nameTuple._1()>a1){
                    a1=nameTuple._1();
                    b= nameTuple._2();
                }

            });
            capitalCities.put(a1, b);

            a1=Double.MIN_VALUE;
        }
        List<Tuple2<Double,String>> fianlListe=new ArrayList<>();
        for (Map.Entry<Double, String> c:capitalCities.entrySet()) {
            fianlListe.add(new Tuple2<>(c.getKey(),c.getValue()));
        }
        finall=sc.parallelizePairs(fianlListe).sortByKey(false);
        System.out.println(finall.take(5).toString());


    }
}
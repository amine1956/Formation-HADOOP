import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class Exercice1 {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("Tp1 1 RDD").setMaster("local[*]");// * c'est le nombre de tread crier on peut faire une valeur
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=sc.parallelize(Arrays.asList("amine ","noufal ","khalid ","sofianMG ","mohamed ","leader "));
       JavaRDD<String> rdd2=rdd1.flatMap(s->Arrays.asList(s.replace(" ","A")).iterator());
       System.out.println(rdd2.collect());
       JavaRDD<String > rdd3=rdd2.filter(a ->a.length()>4 );
       System.out.println(rdd3.collect());
       JavaRDD<String > rdd4=rdd2.filter(a->a.length()>5);
        System.out.println(rdd4.collect());
        JavaRDD<String > rdd5=rdd2.filter(a->a.length()>=6);
        System.out.println(rdd5.collect());
        JavaRDD<String> rdd6=rdd3.union(rdd4);
        System.out.println(rdd6.collect());
        JavaRDD<String> rdd81=rdd6.map(a->a.toLowerCase());
        System.out.println(rdd81.collect());
        JavaRDD<String> rdd71=rdd5.map(a->a.toUpperCase());
        System.out.println(rdd71.collect());
        JavaPairRDD<String ,Integer> rdd72=rdd71.mapToPair(s->new Tuple2<>(s,1));
        JavaPairRDD<String,Integer> rdd7=rdd72.reduceByKey((v1,v2)->{
            System.out.println(v1+" "+v2);
            return  v1+v2;
        });
        System.out.println(rdd7.collect());
        JavaPairRDD<String ,Integer> rdd82=rdd71.mapToPair(s->new Tuple2<>(s,1));
        JavaPairRDD<String,Integer> rdd8=rdd72.reduceByKey((v1,v2)->{
            System.out.println(v1+" "+v2);
            return  v1+v2;
        });
        System.out.println(rdd8.collect());
        JavaPairRDD<String, Integer> rdd9=rdd7.union(rdd8);
        System.out.println(rdd9.collect());
        JavaPairRDD<String, Integer> rdd10=rdd9.sortByKey();
        System.out.println(rdd10.collect());




    }
}

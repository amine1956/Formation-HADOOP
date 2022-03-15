package tpMaprduce;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;

public class ApplicationQ2 {
    public static void main(String[] args) throws IOException {
        JobConf conf=new JobConf();
        conf.setJobName("TP 3 : MapReduce et YARN");
        conf.setJarByClass(ApplicationQ1.class);

        conf.setMapperClass(OcurenceMapperQ2.class);
        conf.setReducerClass(OccurencesReduce.class);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.addInputPath(conf,new Path("ventes.txt"));
        FileOutputFormat.setOutputPath(conf,new Path("./output2"));

        JobClient.runJob(conf);
    }
}
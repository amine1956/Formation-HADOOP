package EX2_QUESTION2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        JobConf conf=new JobConf();
        conf.setJobName("TP 4 : MapReduce et YARN");
        conf.setJarByClass(EX1_QUESTION1.Application.class);

        conf.setMapperClass(OccurenceMapper.class);
        conf.setReducerClass(OccurenceReduce.class);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        FileInputFormat.addInputPath(conf,new Path("employes.csv"));
        FileOutputFormat.setOutputPath(conf,new Path("./output2"));
        JobClient.runJob(conf);
    }
}
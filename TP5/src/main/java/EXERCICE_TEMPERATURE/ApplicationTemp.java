package EXERCICE_TEMPERATURE;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;

public class ApplicationTemp {
    public static void main(String[] args) throws IOException {
        JobConf conf = new JobConf();
        conf.setJobName("TP 4 : MapReduce et YARN");
        conf.setJarByClass(ApplicationTemp.class);

        conf.setMapperClass(OccurenceMaaperTemp.class);
        conf.setReducerClass(OccurenceReduceTEMP.class);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(DoubleWritable.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        FileInputFormat.addInputPath(conf, new Path("04065099999.csv"));
        FileOutputFormat.setOutputPath(conf, new Path("./output3"));
        JobClient.runJob(conf);
    }
}
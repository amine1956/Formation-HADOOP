package EXERCICE_TEMPERATURE;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class OccurenceMaaperTemp extends MapReduceBase
        implements Mapper<LongWritable, Text,Text, DoubleWritable> {

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
        String data=
                value.toString().split("\",\"")[1].split("-")[1];
        String TEMP=value.toString().split("\",\"")[13];
        StringBuilder stringBuilder= new StringBuilder(TEMP);
       Double a=Double.parseDouble( stringBuilder.toString().replace(",","."));
        System.out.println(a);
            output.collect(new Text(data),new DoubleWritable(a));
    }
}
package EX2_QUESTION2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class OccurenceReduce extends MapReduceBase
        implements Reducer<Text, IntWritable,Text,IntWritable> {

    @Override
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        int  val=0;

        while (values.hasNext()) {
          val += values.next().get();

        }
        output.collect(new Text("le nombre des employer dans le deparetemnt "+key.toString()), new IntWritable(val));

    }
}
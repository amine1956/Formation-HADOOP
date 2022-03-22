package EX1_QUESTION1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class OccurencesReduce extends MapReduceBase
        implements Reducer<Text, IntWritable,Text,IntWritable> {

    @Override
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        int max = 0, min = 0, val;
        min = max = values.next().get();
        while (values.hasNext()) {
            val = values.next().get();
            if (max < val) {
                max = val;
            } else min = val;

        }
        output.collect(new Text("le salaire maximal dens le departement "+key.toString()), new IntWritable(max));
        output.collect(new Text("le salaire minimal dens le departement "+key.toString()), new IntWritable(min));
    }
}
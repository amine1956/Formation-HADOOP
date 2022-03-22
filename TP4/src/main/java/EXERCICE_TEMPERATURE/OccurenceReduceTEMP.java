package EXERCICE_TEMPERATURE;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class OccurenceReduceTEMP extends MapReduceBase
        implements Reducer<Text, DoubleWritable,Text,DoubleWritable> {

    @Override
    public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
        Double max = Double.valueOf(0), min = Double.valueOf(0), val;
        min = max = values.next().get();
        while (values.hasNext()) {
            val = values.next().get();
            if (max < val) {
                max = val;
            } else min = val;

        }
        output.collect(new Text("this is the max of the month "+key.toString()), new DoubleWritable(max));
        output.collect(new Text("this is the min of the month "+key.toString()), new DoubleWritable(min));
    }
}
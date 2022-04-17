package mapreduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class KameansCombiner extends Reducer<Text, Text, Text, Text> {


    protected void reduce(Text key, Iterable<Text> values, Reducer.Context context)
            throws IOException, InterruptedException {

        // We count the min and max of the values




        // we write only the min and max to the output
        context.write( new Text(" "), new Text(" "));
    }
}
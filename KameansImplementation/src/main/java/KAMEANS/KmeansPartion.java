package mapreduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class KmeansPartion extends Partitioner< Text, Text>{


    @Override
    public int getPartition(Text text, Text text2, int i) {
        return 0;
    }
}

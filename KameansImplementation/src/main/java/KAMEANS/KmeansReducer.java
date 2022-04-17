package mapreduce;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.zookeeper.txn.Txn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
public class KmeansReducer extends Reducer<Text,  Text ,Text,String> {
    int  J=0;
    static String list= "";
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, String>.Context context) throws IOException, InterruptedException {

        double somme1=0,somme2=0,p1=0,p2=0;
            Iterator<Text> it=values.iterator();

        int j=0;
        while (it.hasNext()){
            String a= String.valueOf(it.next());

            p1=Double.parseDouble(a.toString().split(" ")[0]);;
            p2=Double.parseDouble(a.toString().split(" ")[1]);;
            somme1+=p1;
            somme2+=p2;
            list+=a+" ";
            j++;

        }
        String key1= String.valueOf(somme1/j);
        String key2= String.valueOf(somme2/j);


        context.write(new Text(key1+" "+key2+" "+"["+list+"]"+" "),"");
        list="";

    }
}
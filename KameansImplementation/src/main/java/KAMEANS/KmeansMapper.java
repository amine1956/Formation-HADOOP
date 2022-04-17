package mapreduce;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static mapreduce.KmeansDriver.a;

public class KmeansMapper extends Mapper<LongWritable, Text, Text,Text> {
    List<Point> centers=new ArrayList<>();
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {

        URI uri[]= context.getCacheFiles();

        FileSystem fs=FileSystem.get(context.getConfiguration());
        InputStreamReader is=new InputStreamReader(fs.open(new Path(uri[0])));
        BufferedReader br=new BufferedReader(is);
        String ligne=null;
        int j=0;
        while ((ligne=br.readLine())!=null){

            Double p1=(Double.parseDouble((ligne.split(" ")[0])));
            Double p2=(Double.parseDouble((ligne.split(" ")[1])));
            Point point=new Point();
            point.setX(p1);
            point.setY(p2);
            centers.add(point);

        }

        j++;
    }

    @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        double p1=Double.parseDouble(value.toString().split(" ")[0]);
        double p2=Double.parseDouble(value.toString().split(" ")[1]);
        Point point =new Point();
        point.setX(p1);
        point.setY(p2);

        double min=Double.MAX_VALUE,d;

      Point nearest_center = null;
      Point centere=new Point();
      Point P=new Point();
      for(int i=0;i<centers.size();i++){
            d=Math.sqrt(Math.pow(point.getX()-centers.get(i).getX(),2)+Math.pow(point.getY()- centers.get(i).getY(),2));
            if (d<min){
                min=d;
                centere.setX(centers.get(i).getX());
                centere.setY(centers.get(i).getY());
                nearest_center=centere;


            }
        }
        context.write(new Text(nearest_center.toString()) ,new Text(point.toString()));

    }

}

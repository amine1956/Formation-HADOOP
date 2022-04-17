package mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class KmeansDriver {
    static int a=0;
    static boolean fineched=true;
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
        int i = 0;
        while (fineched){
            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf, "Job Kmeans");
            job.setJarByClass(KmeansDriver.class);
            job.setMapperClass(KmeansMapper.class);
            job.setReducerClass(KmeansReducer.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);
            job.setPartitionerClass(KmeansPartion.class);
            job.setCombinerClass(KameansCombiner.class);
            job.setOutputKeyClass(String.class);
            job.setOutputValueClass(String.class);

            if(i==0)
            job.addCacheFile(new URI("hdfs://localhost:9000/input/yarbi.txt"));
            if(i>0)
                job.addCacheFile(new URI("hdfs://localhost:9000/output501F"+(i-1)+"/part-r-00000"));

            FileInputFormat.addInputPath(job, new Path("/input/DATAC.txt"));

            FileOutputFormat.setOutputPath(job, new Path("/output501F"+i));
            job.waitForCompletion(true);
            if(i>1){
                FileSystem fs=FileSystem.get(conf);
                InputStreamReader is=new InputStreamReader(fs.open(new Path("hdfs://localhost:9000/output501F"+(i-1)+"/part-r-00000")));
                BufferedReader br=new BufferedReader(is);
                FileSystem fs1=FileSystem.get(conf);
                InputStreamReader is1=new InputStreamReader(fs1.open(new Path("hdfs://localhost:9000/output501F"+(i)+"/part-r-00000")));
                BufferedReader br1=new BufferedReader(is);
                String ligne=null;
                String ligne1=null;
                int f=0;
                while ((ligne=br.readLine())!=null&&(ligne1=br1.readLine())!=null){
                    if(!ligne.equals(ligne1)) ++f;
                }
                if(f==0) fineched=false;

            }
            ++i;
        }
    }


            }







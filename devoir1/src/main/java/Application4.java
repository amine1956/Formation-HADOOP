import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

public class Application4{
    public static void main(String[] args) {
        System.setProperty("HADOOP_USER_NAME","root");
        Configuration cf=new Configuration();
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        try {
                FileSystem fs = FileSystem.get(cf);
                Path path1 = new Path("/BDDC/JAVA/Cours/CoursCPP3");
                fs.delete(path1,true);
                fs.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            for (int i=1;i<=2;i++) {
                FileSystem fs = FileSystem.get(cf);
                Path path1 = new Path("/BDDC/JAVA/Cours/CoursCPP"+i);
                Path path2 = new Path("/BDDC/JAVA/Cours/CoursJAVA"+i);
                fs.rename(path1,path2);


                fs.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
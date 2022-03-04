import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Application6 {
    public static void main(String[] args) {
        System.setProperty("HADOOP_USER_NAME","root");
        Configuration cf=new Configuration();
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");

     try {
        FileSystem fs = FileSystem.get(cf);
        Path path1 = new Path("/BDDC/JAVA");
        fs.delete(path1,true);
        fs.close();

    }catch (Exception e){
        e.printStackTrace();
    }}
}

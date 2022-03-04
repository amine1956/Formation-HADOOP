
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Application2 {
    public static void main(String[] args) {
        Configuration cf=new Configuration();
        System.setProperty("HADOOP_USER_NAME","root");
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        try {
            FileSystem fs=FileSystem.get(cf);
            /*Path path=new Path("/file1");
            fs.delete(path,true);*/
            Path path=new Path("/BDDC");
            RemoteIterator<LocatedFileStatus> it = fs.listFiles(path,true);
            while (it.hasNext()){
                LocatedFileStatus lfs=it.next();
                System.out.println(lfs.getPath().toString()+"  "+lfs.getBlockSize()+" "+ lfs.getReplication());
            }

            fs.close();

        }catch (Exception e){
            e.printStackTrace();
        }}}
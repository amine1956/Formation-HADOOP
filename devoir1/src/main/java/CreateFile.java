import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateFile {
    public static void main(String[] args) {
        try {
            for (int i = 1; i <= 2;i++) {
                File myObj = new File("TP" + i + "CPP"+".txt");
                File myObj2= new File("TP" + i + "JAVA"+".txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
                if (myObj2.createNewFile()) {
                    System.out.println("File created: " + myObj2.getName());
                } else {
                    System.out.println("File already exists.");
                }
            }
            File myObj2= new File("TP" + 3 + "JAVA"+".txt");
            if (myObj2.createNewFile()) {
                System.out.println("File created: " + myObj2.getName());
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.setProperty("HADOOP_USER_NAME","root");
        Configuration cf=new Configuration();
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        try {

            for (int i=1;i<=2;i++) {
                FileSystem fs = FileSystem.get(cf);
                Path path3 = new Path("TP" + i + "CPP"+".txt");
                Path path4 = new Path("/BDDC/CPP/TPs");
                fs.copyFromLocalFile(path3,path4);
                fs.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {

            for (int i=1;i<=3;i++) {
                FileSystem fs = FileSystem.get(cf);
                Path path3 = new Path("TP" + i + "JAVA"+".txt");
                Path path4 = new Path("/BDDC/JAVA/TPs");
                fs.copyFromLocalFile(path3,path4);
                fs.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

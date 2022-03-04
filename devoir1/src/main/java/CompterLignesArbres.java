import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
public class CompterLignesArbres
{
    public static void main(String[] args) throws IOException
    {
// nom complet du fichier

// ouvrir le fichier sur HDFS
        for (int i=1;i<=3;i++) {
        System.setProperty("HADOOP_USER_NAME","root");
        Configuration cf=new Configuration();
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        FileSystem fs = FileSystem.get(cf);
        System.out.println("le fichier CoursCPP"+i);
        Path nomcomplet = new Path("/BDDC/CPP/Cours/CoursCPP"+i);
        FSDataInputStream inStream = fs.open(nomcomplet);
        try {

// préparer un lecteur séquentiel
            InputStreamReader isr = new InputStreamReader(inStream);
            BufferedReader br = new BufferedReader(isr);
// parcourir les lignes une par une
            String ligne = br.readLine();
            while (ligne != null) {
// traiter la ligne courante
                System.out.println(ligne);
// passer à la ligne suivante (retourne null si fin fichier)
                ligne = br.readLine();
            }
        } finally {
// fermer le fichier quoi qu'il arrive
            inStream.close();
            fs.close();
        }
    }
}}

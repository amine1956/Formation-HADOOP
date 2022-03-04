import java.io.File;

public class Application5 {
    public static void main(String args[]) {

        try {
            for (int i = 1 ; i <= 2; i++) {

                // Recevoir le fichier
                File f = new File("TP"+i+"CPP");
                File f2 = new File("TP"+i+"JAVA");

                // Créer un nouveau fichier
                // Vérifier s'il n'existe pas
            }
            new File("TP"+3+"JAVA");
            }
        catch(Exception e){
                System.err.println(e);
            }


}}
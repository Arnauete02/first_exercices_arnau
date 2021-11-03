/**
 * Join.java		28 sept. 2021
 *
 * Arnau Gràcia Taberner
 */
package exercici_1_join;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Join {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get(args[0]);

        int a = 0;
        Path pathN = Paths.get(path + ".part." + a);

        while (Files.exists(pathN)){
            a++;
            pathN = Paths.get(path + ".part." + a);
        }

        InputStream is;
        OutputStream os = Files.newOutputStream(path);

        for (int i = 0; i < a; i++){
            is = Files.newInputStream(Paths.get(path + ".part." + i));
            for (int j; (j = is.read()) != -1;){
                os.write(j);
            }
            Files.delete(Paths.get(path + ".part." + i));
        }


        os.close();
    }
}

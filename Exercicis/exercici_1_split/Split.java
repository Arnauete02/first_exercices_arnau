/**
 * Split.java		28 sept. 2021
 * 
 * Arnau Gràcia Taberner
 */
package exercici_1_split;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Split {
	
	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get(args[0]);
		int num = Integer.parseInt(args[1]);

		int a = 0;
		Path pathN = Paths.get(path + ".part." + a);
		OutputStream os = Files.newOutputStream(pathN);

		InputStream is = Files.newInputStream(path);

		int size = is.available();
		int nParts = (int)Math.ceil(size / num);
		int count = 0;

		for (int i; (i = is.read()) != -1;){
			count++;
			if (count > nParts) {
				count = 0;
				a++;
				pathN = Paths.get(path + ".part." + a);
				os = Files.newOutputStream(pathN);
			}
			os.write(i);
		}
		os.close();

	}
	
}

/**
 * Flatten.java		27 sept. 2021
 * 
 * Arnau Gràcia Taberner
 */
package exercici_1_flatten;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class Flatten {
	public static void main(String[] args) throws IOException{
	
		/*
		 * Fes un programa que rebi com a argument la ruta d'un directori i "aplani" (flatten) el seu contingut, 
		 * és a dir, que mogui a aquest mateix directori tots els fitxers que tingui niats. També haurà d'esborrar 
		 * tots els directoris niats. Per exemple, si executem el programa amb aquest directori "niats":
		 */
		 
		Path path = Paths.get("/tmp/niats");
		
		/*
		 * Movem tots els fitxers a l'arrel del path.
		 */
		Files.walk(path)
			.filter(Files::isRegularFile)
			.forEach(file -> {
				try {
					Files.move(file, path.resolve(file.getFileName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		
		/*
		 * Eliminem tots els directoris invertint l'ordre per a que comenci per la que
		 * esta mes a dins. COmparem path amb dir, per a que vegi que no es igual i elimina
		 * les que no son iguals
		 */

		Files.walk(path)
			.filter(Files::isDirectory)
			.sorted(Comparator.reverseOrder())
			.forEach(dir -> {
				try {
					if (!path.equals(dir)) Files.delete(dir);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

	}
}

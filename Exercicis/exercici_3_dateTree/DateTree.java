/**
 * DateTree.java		27 sept. 2021
 * 
 * Arnau Gràcia Taberner
 */
package exercici_3_dateTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class DateTree {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get("/tmp/directori");
		
		Files.walk(path)
			.filter(Files::isRegularFile)
			.forEach(file -> {
				try {
					Files.move(file, path.resolve(file.getFileName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

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
		
		Files.walk(path)
			.filter(Files::isRegularFile)
			.forEach(file -> {
				try {
					LocalDateTime time = LocalDateTime.parse(Files.getLastModifiedTime(file).toString(), DateTimeFormatter.ISO_DATE_TIME);
					Path new_path = Paths.get(path.toString() + "/" + time.getYear() + "/" + time.getMonthValue() + "/" + time.getDayOfMonth() + "/" + file.getFileName());
					Files.createDirectories(new_path);
					Files.delete(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
	}

}

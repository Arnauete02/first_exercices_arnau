/**
 * Treediff.java		27 sept. 2021
 * 
 * Arnau Gràcia Taberner
 */

package exercici_2_treediff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Treediff {

	static boolean isRepeated = false;
	static boolean isRepeatedName = false;
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		
		Path dirA = Paths.get("/tmp/dirA");
		Path dirB = Paths.get("/tmp/dirB");
		
		/*
		 * 1. Els fitxers del DirectoriA que no estan al DirectoriB
		 */
		
		System.out.println("1.");
		
		Files.walk(dirA)
		.filter(Files::isRegularFile)
		.forEach(fileA -> {
			isRepeated = false;
			byte[] hash1;
			try {
				hash1 = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(fileA));
				try {
					Files.walk(dirB)
					.filter(Files::isRegularFile)
					.forEach(fileB -> {
						try {
							byte[] hash2 = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(fileB));
							if (Arrays.equals(hash1, hash2)) isRepeated = true;
						} catch (NoSuchAlgorithmException | IOException e) {
							e.printStackTrace();
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (NoSuchAlgorithmException | IOException e1) {
				e1.printStackTrace();
			}
			if (!isRepeated) {
				System.out.println(fileA);
			}
		});
		
		/*
		 * 2. Els fitxers del DirectoriA que estan al DirectoriB però en una altra ruta
		 */
		
		System.out.println("\n2.");
		
		Files.walk(dirA)
		.filter(Files::isRegularFile)
		.forEach(fileA -> {
			isRepeated = false;
			isRepeatedName = false;
			byte[] hash1;
			try {
				hash1 = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(fileA));
				Path pathA = dirA.relativize(fileA);
				try {
					Files.walk(dirB)
					.filter(Files::isRegularFile)
					.forEach(fileB -> {
						try {
							byte[] hash2 = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(fileB));
							Path pathB = dirB.relativize(fileB);
							if (pathA.equals(pathB)) isRepeatedName = true;
							if (Arrays.equals(hash1, hash2)) isRepeated = true;
						} catch (NoSuchAlgorithmException | IOException e) {
							e.printStackTrace();
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (NoSuchAlgorithmException | IOException e1) {
				e1.printStackTrace();
			}
			if (isRepeated && !isRepeatedName) {
				System.out.println(fileA);
			}
		});
		
		/*
		 * 3. Els fitxers del DirectoriB que no estan al DirectoriA
		 */
		
		System.out.println("\n3.");
		
		Files.walk(dirB)
		.filter(Files::isRegularFile)
		.forEach(fileB -> {
			isRepeated = false;
			byte[] hash1;
			try {
				hash1 = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(fileB));
				try {
					Files.walk(dirA)
					.filter(Files::isRegularFile)
					.forEach(fileA -> {
						try {
							byte[] hash2 = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(fileA));
							if (Arrays.equals(hash1, hash2)) isRepeated = true;
						} catch (NoSuchAlgorithmException | IOException e) {
							e.printStackTrace();
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (NoSuchAlgorithmException | IOException e1) {
				e1.printStackTrace();
			}
			if (!isRepeated) {
				System.out.println(fileB);
			}
		});
		
		/*
		 * 4. Els fitxers del DirectoriB que estan al DirectoriA però en una altra ruta
		 */
		
		System.out.println("\n4.");
		
		Files.walk(dirB)
		.filter(Files::isRegularFile)
		.forEach(fileB -> {
			isRepeated = false;
			isRepeatedName = false;
			byte[] hash1;
			try {
				hash1 = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(fileB));
				Path pathB = dirB.relativize(fileB);
				try {
					Files.walk(dirA)
					.filter(Files::isRegularFile)
					.forEach(fileA -> {
						try {
							byte[] hash2 = MessageDigest.getInstance("MD5").digest(Files.readAllBytes(fileA));
							Path pathA = dirA.relativize(fileA);
							if (pathB.equals(pathA)) isRepeatedName = true;
							if (Arrays.equals(hash1, hash2)) isRepeated = true;
						} catch (NoSuchAlgorithmException | IOException e) {
							e.printStackTrace();
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (NoSuchAlgorithmException | IOException e1) {
				e1.printStackTrace();
			}
			if (isRepeated && !isRepeatedName) {
				System.out.println(fileB);
			}
		});
		
		/*
		 * 5. Els fitxers que estan a la mateixa ruta al DirectoriA i al DirectoriB
		 */
		
		System.out.println("\n5.");
		
		Files.walk(dirA)
		.filter(Files::isRegularFile)
		.forEach(fileA -> {
			isRepeatedName = false;
				Path pathA = dirA.relativize(fileA);
				try {
					Files.walk(dirB)
					.filter(Files::isRegularFile)
					.forEach(fileB -> {
							Path pathB = dirB.relativize(fileB);
							if (pathA.equals(pathB)) isRepeatedName = true;
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (isRepeatedName) {
				System.out.println(fileA);
			}
		});
		
	}

}

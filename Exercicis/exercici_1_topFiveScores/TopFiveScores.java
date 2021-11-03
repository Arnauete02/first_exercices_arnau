package exercici_1_topFiveScores;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopFiveScores {

    public static ArrayList<Score> scores = new ArrayList<Score>();
    public static String path;

    public static void main(String[] args){

        try {
            writeScore(args[0], args[1]);
        } catch (IOException e) {
            System.out.println("You don't put args! --> Name & Score");
            System.exit(1);
        }

    }

    public static void writeScore(String name, String score) throws IOException {
        Path file = Paths.get(path);

        if (!Files.exists(file)) { Files.createFile(file); }

        List<String> lines = Files.lines(file).collect(Collectors.toList());

        for (String line : lines) {
            String[] chunkyLine = line.split(" ");
            scores.add(new Score(chunkyLine[0], chunkyLine[1]));
        }

        scores.add(new Score(name, score));

        for (int i = 0; i <= scores.size(); i++){
            for (int j = 0; j < scores.size()-i-1; j++){
                if (scores.get(j).compareTo(scores.get(j+1)) < 0){
                    Score temporal = scores.get(j);
                    scores.set(j, scores.get(j+1));
                    scores.set(j+1, temporal);
                }
            }
        }

        BufferedWriter bufferedWriter = Files.newBufferedWriter(file);

        if (scores.size() >= 5){
            for (int i = 0; i < 5; i++){
                bufferedWriter.write(scores.get(i).name + " " + scores.get(i).score);
                bufferedWriter.newLine();
            }
        } else {
            for (int i = 0; i < scores.size(); i++){
                bufferedWriter.write(scores.get(i).name + " " + scores.get(i).score);
                bufferedWriter.newLine();
            }
        }

        bufferedWriter.close();
    }
}

class Score{
    protected String name;
    public int score;

    public Score (String name, String score){
        this.name = name;
        this.score = Integer.parseInt(score);
    }

    public int compareTo(Score s){
        int r = 0;
        if (score < s.score) { r = -1; }
        if (score > s.score) { r = 1; }
        return r;
    }
}

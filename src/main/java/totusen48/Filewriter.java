package totusen48;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Filewriter {

    String filename = "C:/Users/auras/OneDrive/Documents/Koding/Objektorientert/tdt4100-students-24/minegenkode/src/main/java/totusen48/highscores.txt";

    public List<String> getHighscoreList() {
        List<String> scores = new ArrayList<String>();
        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            
            while(scanner.hasNextLine()){
                String data =  scanner.nextLine();
                scores.add(data);
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        return scores;
    }

    public String getHighscore(){
        List<String> highscores = getHighscoreList();
        return highscores.get(0);
    }

    public void addHighscore(int newScore){
        boolean insert = false;
        int insertIndex = 0;
        List<String> highscores = getHighscoreList();
        for(int i=0; i<9; i++){
            if( Integer.parseInt(highscores.get(i)) < newScore ){
                insert = true;
                insertIndex = i;
                break;
            }
        }
        if(insert){
            highscores.removeLast();
            highscores.add(insertIndex, Integer.toString(newScore));
            try{
                FileWriter fw = new FileWriter(filename);
                for(String score : highscores){
                    fw.write(score + "\n");
                }
                fw.close();
            }
            catch(IOException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public void setFilename(String filename){
        this.filename = filename;
    }
}
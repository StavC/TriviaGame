package TriviaGame;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class QuestionsList {

   private ArrayList<Question> myQuestions=new ArrayList<>();

    public void readFile(File file) { // reading the file with file chooser line by line
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()){
                myQuestions.add(new Question(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine()));
            }
            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  ArrayList<Question> getMyQuestions() {//shuffling the questions so they will appear in random order
        Collections.shuffle(myQuestions);
        return myQuestions;
    }
}





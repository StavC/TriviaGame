package TriviaGame;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args){

        try {

            JFrame app = new JFrame("Trivia Game!");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setSize(600, 600);
            QuestionsList questions = new QuestionsList();
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.CANCEL_OPTION) { // if you click cancel ull get an error msg and will have to re run the program
                JOptionPane.showMessageDialog(null, "You Must Choose A File To Play The Game");
                System.exit(1);

            }
            File file = fc.getSelectedFile();
            questions.readFile(file);
            TriviaPanel triviaPanel = new TriviaPanel(questions);
            app.add(triviaPanel, BorderLayout.CENTER);
            app.setVisible(true);
        }
        catch (IndexOutOfBoundsException | NoSuchElementException e){ // making sure u choose the right file otherwise rerun the program
            JOptionPane.showMessageDialog(null, "You must choose the right file to Play The Game");
            System.exit(1);

        }



    }

}

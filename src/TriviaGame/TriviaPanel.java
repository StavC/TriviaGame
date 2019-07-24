package TriviaGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class TriviaPanel extends JPanel implements ActionListener {

    private ArrayList<Question> myQuestions;
    private JLabel question;
    private ButtonGroup group = new ButtonGroup();
    private static JRadioButton[] answersRadioButtons;
    private ArrayList<String> answers;
    private JButton nextQuestion;
    private JButton newGame;
    private JButton finishGame;
    private static int score;
    private static int maxScore; // Extra for maxScore
    private int i;
    private int questionCount;
    private Timer timer;
    private String timeRanOut;
    private JTextArea info; //Extra Info
    private JLabel scoreJLabel; //Extra scoreJLabel
    private JLabel maxScoreJLabel;// Extra maxScore per program run

    public TriviaPanel(QuestionsList QuestionsList) {

        setLayout(new GridLayout(6, 1));
        myQuestions = QuestionsList.getMyQuestions(); //getting the questions
        questionCount = myQuestions.size(); // checking how many questions we have
        maxScore=questionCount*-5;// Extra calculating the min score that someone can have in our quizz so everyscore about it wil change the max score

        // getting ready for the first question
        i = 0;
        question = new JLabel();
        question.setText(myQuestions.get(i).getQuestion());
        answers = myQuestions.get(i).getAnswers();
        answersRadioButtons = new JRadioButton[4];
        for (int j = 0; j < 4; j++) {
            answersRadioButtons[j] = new JRadioButton(answers.get(j));
            group.add(answersRadioButtons[j]);
        }


        timer = new Timer(10000,this);// our timer for 10 secs for each question

        JPanel topJpanel = new JPanel();
        topJpanel.setLayout(new GridLayout(1, 3, 40, 60));
        newGame = new JButton("New Game");
        newGame.addActionListener(this);
        nextQuestion = new JButton("Next Question");
        nextQuestion.setEnabled(false);
        nextQuestion.addActionListener(this);
        finishGame = new JButton("Finish Game");
        finishGame.addActionListener(this);
        topJpanel.add(newGame, BorderLayout.EAST);
        topJpanel.add(nextQuestion, BorderLayout.CENTER);
        topJpanel.add(finishGame, BorderLayout.WEST);
        add(topJpanel, BorderLayout.SOUTH); // Adding button rows
         info=new JTextArea("Hello! Welcome to our Trivia Game! \n you have 10 secs to answer each question " +
                "\n if you wont answer in time we gonna chose the answer that selected for u!" +
                "\n click on the button new game to start!" +
                "\n good luck!" ); // extra explantion on JtextArea to look nice

        info.setEditable(false);
        add(info,BorderLayout.NORTH); //Adding our explantion
        scoreJLabel= new JLabel();// extra Label for score
        maxScoreJLabel=new JLabel(); // Extra Label for max score


    }

    public void displayQuestion(int i) {// method to display the current question and answers in the game

        if (i == 0) {// if we started an new game add the question and the buttons
            add(question);
            for (int j = 0; j < 4; j++) {
                add(answersRadioButtons[j]);
            }
        }
        question.setText(myQuestions.get(i).getQuestion());
        answers = myQuestions.get(i).getAnswers();
        for (int j = 0; j < 4; j++) {
            answersRadioButtons[j].setText(answers.get(j));

        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {// our event listener using Sys.out.println to not annoy with popup msgs

        if(e.getSource()==nextQuestion){// checking if the event was triggred by the timer
            timeRanOut="";
        }else{
            timeRanOut="you time ran out !!! but your answer is ";
        }
        if(e.getSource()!=finishGame && e.getSource()!=newGame) {// if the event wasnt triggred from the finish game\newgame
            if (i < questionCount) {// if we got question left
                if (getSelectedButton() == null) {// if nothing was selected -5 points
                    System.out.println(timeRanOut + " WRONGGGGGGG -5 points you didnt select any option!");
                    score -= 5;
                } else if (myQuestions.get(i - 1).getCorrectAnswer() == getSelectedButton()) {

                    System.out.println(timeRanOut + "CORRECT!!!! +10 Points");
                    score += 10;
                } else {
                    System.out.println(timeRanOut + "WRONGGGGGGG -5 points");
                    score -= 5;
                }
                displayQuestion(i);
                i++;
            } else if (i == questionCount) { //if we reached the end of the questions
                if (getSelectedButton() == null) {// if nothing was selected -5 points
                    System.out.println(timeRanOut + " WRONGGGGGGG -5 points you didnt select any option!");
                    score -= 5;
                } else if (myQuestions.get(i - 1).getCorrectAnswer() == getSelectedButton()) {
                    System.out.println(timeRanOut + "CORRECT!!!! +10 Points");
                    score += 10;
                } else {
                    System.out.println(timeRanOut + "WRONGGGGGGG -5 points");
                    score -= 5;
                }
                finishGame();
                System.out.println("You Have Finished the Trivia");
            }
        }
        if(e.getSource()==finishGame){ //if we clicked on finish game button
            finishGame();
        }
        if(e.getSource()==newGame){// if we click on the new Game button starting new game
            remove(info); //removing the explantion
            remove(scoreJLabel);//removing the extras
            remove(maxScoreJLabel);//removing the extras
            score=0;
            nextQuestion.setEnabled(true);
            finishGame.setEnabled(true);
            i=1;
            Collections.shuffle(myQuestions); // shuffle the question
            displayQuestion(0);
            timer.restart();
            //refresh the panel
            revalidate();
            repaint();
        }
        group.clearSelection();

    }

    private void finishGame(){ // finish game button
        JOptionPane.showMessageDialog(this,"You Finished the Trivia with "+score+" Points! Click new game if u want to restart");
        nextQuestion.setEnabled(false);
        finishGame.setEnabled(false);
        timer.stop();
        //removing the questions and adding back the explantion score and maxscore
        remove(question);
        for (int j = 0; j < 4; j++) {
            remove(answersRadioButtons[j]);

        }
        add(info,BorderLayout.NORTH);
        scoreJLabel.setText("your Last score is: "+score);
        add(scoreJLabel,BorderLayout.NORTH);
        checkMax();
        maxScoreJLabel.setText("Your Best Score is: "+maxScore+" Out of "+ questionCount*10 + "Total Points");
        add(maxScoreJLabel,BorderLayout.NORTH);
        revalidate();
        repaint();


    }

   private String getSelectedButton() {// method to get the radio button text
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private void checkMax(){ //checking if we should change the maxScore
        if(score>maxScore)
            maxScore=score;
    }
}



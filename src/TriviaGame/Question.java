package TriviaGame;


import java.util.ArrayList;
import java.util.Collections;

public class Question {

    private String question; //saves the question
    private String correctAnswer; // saves the correct answer
    private String[] wrongAnswers=new String[3]; // saves the wrong answers

    public Question(String question, String correctAnswer, String wrongAnswer1,String wrongAnswer2,String wrongAnswer3) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        wrongAnswers[0] = wrongAnswer1;
        wrongAnswers[1] = wrongAnswer2;
        wrongAnswers[2] = wrongAnswer3;
    }


    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers(){

        ArrayList<String> answers=new ArrayList<>();
        answers.add(correctAnswer);
        answers.add(wrongAnswers[0]);
        answers.add(wrongAnswers[1]);
        answers.add(wrongAnswers[2]);
        Collections.shuffle(answers); //shuffling the answers to make them appear in random order
        return answers;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

package SQLite;

import java.util.ArrayList;

public class Question {
    int ID;
    String question;
    ArrayList<String> answerChoice;
    String right_answer;

    String choice1;
    String choice2;
    String choice3;
    String choice4;

    // Id  question right_answer choice1 choice2 choice3 choice4
    public Question(int ID, String question, String right_answer, String choice1
            , String choice2, String choice3, String choice4){
        this.ID=ID;
        this.question = question;
       // this.answerChoice = answerChoice; //  Four choices that the user will select the correct answer out of.
        this.right_answer=right_answer;
        this.choice1=choice1;
        this.choice2=choice2;
        this.choice3=choice3;
        this.choice4=choice4;

    }
}

package Model;

import SQLite.DBRetriever;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

public class MockDoor {
    BufferedImage myImage;
    private MockQuestion myQuestion;
    private boolean myUnlocked;
    private boolean myAttempted;
    private String myPlayerAnswer;


//    For testing, delete later
//    For testing, delete later
//    public static void main(String[] args) {
//        Door door = new Door();
//        door.displayQuestion();
//        System.out.println(door.getAnswer());
//        DBRetriever tr = new DBRetriever();
//        tr.resetAllToUnused();
//    }

    public MockDoor () {
        myQuestion = new MockQuestion();
        myUnlocked = false;
        myAttempted = false;
        myPlayerAnswer = null;
    }
    public void displayQuestion() {
        // Testing purposes. Will be done in GUI implementation once we have SQLite database.
        System.out.println(myQuestion.getMyQuestion());
    }
    public void lockedDoor(final Player thePlayer) {
        if (thePlayer.hasSoulStone() && myAttempted) {
            System.out.println("Would you like to use the Soul Stone to attempt this door again?");
            // In GUI, if res.player chooses yes button, it will call the useAbility() method for Soul Stone.
        } else if (!thePlayer.hasSoulStone() && myAttempted) {
            System.out.println("Door has been attempted.");
        }
    }
    public void checkPlayerAnswer() {
        String myAnswer = myQuestion.getMyAnswer();
        if (myAnswer.toLowerCase().equals(myPlayerAnswer.toLowerCase())) {
            System.out.println("Correct");
            myUnlocked = true;
        } else {
            System.out.println("Wrong");
        }
        myAttempted = true;
    }

    public boolean getMyUnlock() {
        return myUnlocked;
    }
    public void setMyUnlock(final boolean lock) {
        myUnlocked= lock;
    }
    public boolean getAttempt() {
        return myAttempted;
    }
    public void setAttempted(final boolean attempt) {
        myAttempted = attempt;
    }
    public String getPlayerMyAnswer() {
        return myPlayerAnswer;
    }
    public void setPlayerMyAnswer(final String answer) {
        myPlayerAnswer = answer;
    }


    public MockQuestion getQuestionObject() {
        return myQuestion;
    }

    public String getQuestion() {
        return myQuestion.getMyQuestion();
    }

    public String getAnswer() {
        return myQuestion.getMyAnswer();
    }

    public String getOption1() {
        return myQuestion.getMyOption1();
    }
    public String getMyOption2() {
        return myQuestion.getMyOption2();
    }
    public String getMyOption3() {
        return myQuestion.getMyOption3();
    }
    public String getMyOption4() {
        return myQuestion.getMyOption4();
    }

}
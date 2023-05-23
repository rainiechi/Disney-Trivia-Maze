package Model;

import SQLite.DBRetriever;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Door {
    BufferedImage myImage;
    private DBRetriever myRetriever;
    private Question myQuestion;
    private boolean myUnlocked;
    private boolean myAttempted;
    private String myPlayerAnswer;
    private QuestionRecord myRecord;


//    For testing, delete later
//    For testing, delete later
    public static void main(String[] args) {
        QuestionRecord qr = new QuestionRecord();
        Door door = new Door(qr);
        door.displayQuestion();
        //System.out.println(door.getAnswer());
    }

    public Door (final QuestionRecord theRecord) {

        myRecord = theRecord;
        myRetriever = new DBRetriever();
        myQuestion = getUnusedQuestion(myRetriever);
        myUnlocked = false;
        myAttempted = false;
        myPlayerAnswer = null;
        try{
            myImage = (ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall_door.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Question getUnusedQuestion(final DBRetriever theRetriever) {
        if (theRetriever == null) {
            throw new IllegalArgumentException("DBRetriever must not be null");
        }
        Question question = theRetriever.retrieveQuestion();
        boolean questionFound = false;

        while (!questionFound) {
            if (myRecord.checkIfUnused(question.getMyID())) {
                myRecord.addToUsedQuestion(question.getMyID());
                questionFound = true;
            } else {
                question = theRetriever.retrieveQuestion(); // Get another question if the current one is already used
            }
        }
        return question;
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
            myUnlocked = true;
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
    public BufferedImage getImage() {
        return myImage;
    }

    public Question getQuestionObject() {
        return myQuestion;
    }



//    public String getQuestion() {
//        return myQuestion.getMyQuestion();
//    }
//
//    public String getAnswer() {
//        return myQuestion.getMyAnswer();
//    }
//
//    public String getOption1() {
//        return myQuestion.getMyOption1();
//    }
//    public String getMyOption2() {
//        return myQuestion.getMyOption2();
//    }
//    public String getMyOption3() {
//        return myQuestion.getMyOption3();
//    }
//    public String getMyOption4() {
//        return myQuestion.getMyOption4();
//    }

}

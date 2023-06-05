package Model;

import SQLite.DBRetriever;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class Door implements Serializable {
    private transient BufferedImage myImage;
    private final Question myQuestion;
    private boolean myUnlocked;
    private boolean myAttempted;
    private String myPlayerAnswer;
    private final QuestionRecord myRecord;


    public Door (final QuestionRecord theRecord) {

        myRecord = theRecord;
        DBRetriever myRetriever = new DBRetriever();
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
        if (myAnswer.equalsIgnoreCase(myPlayerAnswer)) {
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


}

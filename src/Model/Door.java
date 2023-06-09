package Model;

import SQLite.DBRetriever;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
/**
 * Door class represents the door object in game.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */

public class Door implements Serializable {
    /** Image of the door object */
    private transient BufferedImage myImage;
    /** DBRetriever object */
    private transient DBRetriever myRetriever;
    /** Question object */
    private Question myQuestion;
    /** Boolean if door is unlocked */
    private boolean myUnlocked;
    /** Boolean if door has been attempted */
    private boolean myAttempted;

    /** QuestionRecord object */
    private QuestionRecord myRecord;

    /**
     * Constructor initializes fields.
     * @param theRecord QuestionRecord object
     */
    public Door (final QuestionRecord theRecord) {

        myRecord = theRecord;
        myRetriever = new DBRetriever();
        myQuestion = getUnusedQuestion(myRetriever);
        myUnlocked = false;
        myAttempted = false;
        // sets door image
        try{
            myImage = (ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall_door.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method gets an unused question from the database.
     * @param theRetriever Database retriever
     * @return unused question
     */
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

    /**
     * Getter method for unlock
     * @return true if door has been unlocked, false otherwise.
     */
    public boolean isUnlocked() {
        return myUnlocked;
    }

    /**
     * Setter method for unlock.
     * @param lock boolean
     */
    public void setMyUnlock(final boolean lock) {
        myUnlocked= lock;
    }

    /**
     * Getter method for attempt.
     * @return True if door has been attempted, false otherwise.
     */
    public boolean isAttempted() {
        return myAttempted;
    }

    /**
     * Setter method for attempt.
     * @param attempt boolean
     */
    public void setAttempted(final boolean attempt) {
        myAttempted = attempt;
    }


    /**
     * Getter method for image of door.
     * @return BufferedImage of door
     */
    public BufferedImage getImage() {
        return myImage;
    }

    /**
     * Getter method for QuestionObject attached to Door.
     * @return QuestionObject attached.
     */
    public Question getQuestionObject() {
        return myQuestion;
    }

}

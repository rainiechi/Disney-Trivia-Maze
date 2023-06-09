package Model;

import java.awt.image.BufferedImage;

/**
 * Mock Door object used to represent Door. The Mock Door was used
 * for testing before SQLite database was implemented.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class MockDoor {
    /** Image of the door object */
    BufferedImage myImage;
    /** Question object */
    private MockQuestion myQuestion;
    /** Boolean if door is unlocked */
    private boolean myUnlocked;
    /** Boolean if door has been attempted */
    private boolean myAttempted;
    /** Player answer */
    private String myPlayerAnswer;

    /**
     * Constructor initializes the Mock Door object
     */
    public MockDoor () {
        myQuestion = new MockQuestion();
        myUnlocked = false;
        myAttempted = false;
        myPlayerAnswer = null;
    }

    /**
     * Used for testing. Displays the question
     */
    public void displayQuestion() {
        // Testing purposes. Will be done in GUI implementation once we have SQLite database.
        System.out.println(myQuestion.getMyQuestion());
    }

    /**
     * Method if door is locked and player has soul stone.
     * @param thePlayer player
     */
    public void lockedDoor(final Player thePlayer) {
        if (thePlayer == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (thePlayer.isSoulStone() && myAttempted) {
            System.out.println("Would you like to use the Soul Stone to attempt this door again?");
            // In GUI, if res.player chooses yes button, it will call the useAbility() method for Soul Stone.
        } else if (!thePlayer.isSoulStone() && myAttempted) {
            System.out.println("Door has been attempted.");
        }
    }

    /**
     * Method checks if player answer is correct.
     */
    public void checkPlayerAnswer() {
        // Might not be needed in actual code.
        String myAnswer = myQuestion.getMyAnswer();
        if (myAnswer.toLowerCase().equals(myPlayerAnswer.toLowerCase())) {
            System.out.println("Correct");
            myUnlocked = true;
        } else {
            System.out.println("Wrong");
        }
        myAttempted = true;
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
     * Getter method for player answer.
     * @return player answer
     */
    public String getPlayerMyAnswer() {
        return myPlayerAnswer;
    }
    /**
     * Setter method for player answer.
     * @param answer player answer
     */
    public void setPlayerMyAnswer(final String answer) {
        myPlayerAnswer = answer;
    }

    /**
     * Getter method for question object.
     * @return question of door
     */
    public MockQuestion getQuestionObject() {
        return myQuestion;
    }

}

package Model;

import java.io.Serializable;
/**
 * Question class contains the answer to the question and choices.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class Question implements Serializable {
    /** Correct answer */
    private String myAnswer;
    /** Question */
    private String myQuestion;
    /** Option 1 */
    private String myOption1;
    /** Option 2 */
    private String myOption2;
    /** Option 3 */
    private String myOption3;
    /** Option 4 */
    private String myOption4;
    /** Question Id */
    private int myID;

    /**
     * Constructor initializes the fields.
     * @param theQuestion trivia question
     * @param theAnswer correct answer to question
     * @param theOption1 option 1
     * @param theOption2 option 2
     * @param theOption3 option 3
     * @param theOption4 option 4
     * @param theID id of question
     */
    public Question(final String theQuestion, final String theAnswer, final String theOption1,
                    final String theOption2, final String theOption3, final String theOption4, final int theID) {
        if (theQuestion == null || theAnswer == null || theOption1 == null || theOption2 == null
        || theOption3 == null || theOption4 == null) {
            throw new IllegalArgumentException("All required parameters must not be null.");
        }
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myOption1 = theOption1;
        myOption2 = theOption2;
        myOption3 = theOption3;
        myOption4 = theOption4;
        myID = theID;
    }

    /**
     * Getter method for trivia question.
     * @return trivia question
     */
    public String getMyQuestion() {
        return myQuestion;
    }

    /**
     * Getter method for option 1.
     * @return option 1
     */
    public String getMyAnswer() {
        return myAnswer;
    }

    /**
     * Getter method for option 1.
     * @return option 1.
     */
    public String getMyOption1() {
        return myOption1;
    }

    /**
     * Getter method for option 2.
     * @return option 2.
     */
    public String getMyOption2() {
        return myOption2;
    }

    /**
     * Getter method for option 3.
     * @return option 3.
     */
    public String getMyOption3() {
        return myOption3;
    }

    /**
     * Getter method for option 4.
     * @return option 4.
     */
    public String getMyOption4() {
        return myOption4;
    }

    /**
     * Getter method for id
     * @return question id
     */
    public int getMyID() {
        return myID;
    }
}

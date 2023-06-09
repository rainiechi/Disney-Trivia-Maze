package Model;
/**
 * Mock question object used to represent Question. The Mock question was used
 * for testing before SQLite database was implemented.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class MockQuestion {
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

    /**
     * Constructor to initialize fields.
     */
    public MockQuestion() {
        myQuestion = "What is the capital of France?";
        myAnswer = "Paris";
        myOption1 = "Paris";
        myOption2 = "London";
        myOption3 = "Berlin";
        myOption4 = "Madrid";
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




}
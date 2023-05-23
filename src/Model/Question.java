package Model;

public class Question {
    private String myAnswer;
    private String myQuestion;
    private String myOption1;
    private String myOption2;
    private String myOption3;
    private String myOption4;

    private int myID;


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


    public String getMyQuestion() {
        return myQuestion;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public String getMyOption1() {
        return myOption1;
    }

    public String getMyOption2() {
        return myOption2;
    }

    public String getMyOption3() {
        return myOption3;
    }
    public String getMyOption4() {
        return myOption4;
    }

    public int getMyID() {
        return myID;
    }



}

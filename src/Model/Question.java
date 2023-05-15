package Model;

public class Question {
    private String myAnswer;
    private String myQuestion;
    private String myOption1;
    private String myOption2;
    private String myOption3;
    private String myOption4;

    public Question() {
        myAnswer = null;
        myQuestion = null;
        myOption1 = null;
        myOption2 = null;
        myOption3 = null;
        myOption4 = null;
    }

    public void setMyQuestion(final String theQuestion) {
        if (theQuestion == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        myQuestion = theQuestion;
    }

    public void setMyAnswer(final String theAnswer) {
        if (theAnswer == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        myAnswer = theAnswer;
    }

    public void setMyOption1(final String theOption) {
        if (theOption == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        myOption1 = theOption;
    }
    public void setMyOption2(final String theOption) {
        if (theOption == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        myOption2 = theOption;
    }

    public void setMyOption3(final String theOption) {
        if (theOption == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        myOption3 = theOption;
    }

    public void setMyOption4(final String theOption) {
        if (theOption == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        myOption4 = theOption;
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




}

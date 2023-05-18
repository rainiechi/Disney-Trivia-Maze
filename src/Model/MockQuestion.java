package Model;

public class MockQuestion {
    private String myAnswer;
    private String myQuestion;
    private String myOption1;
    private String myOption2;
    private String myOption3;
    private String myOption4;


    public MockQuestion() {
        myQuestion = "What is the capital of France?";
        myAnswer = "Paris";
        myOption1 = "Paris";
        myOption2 = "London";
        myOption3 = "Berlin";
        myOption4 = "Madrid";
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
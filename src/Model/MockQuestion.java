package Model;

public class MockQuestion {
    private String myAnswer;
    private String myQuestion;
    public MockQuestion() {
        myAnswer = null;
        myQuestion = null;
    }
    public String getAnswer() {
        return myAnswer;
    }
    public void setAnswer(String answer) {
        myAnswer = answer;
    }
    public String getQuestion() {
        return myQuestion;
    }
    public void setQuestion(String question) {
        myQuestion = question;
    }

}

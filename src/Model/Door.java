package Model;

import SQLite.Question;

public class Door {

    private MockQuestion myQuestion;
    /** Boolean checks if door is unlocked*/
    private boolean myUnlocked;
    /** Boolean checks if door has been attempted*/
    private boolean myAttempted;
    private String myPlayerAnswer;

    public Door () {
        myQuestion = new MockQuestion();
        myQuestion.setQuestion(" In Finding Nemo, what's the name of Nemo's mother?");
        myQuestion.setAnswer("Coral");
        myUnlocked = false;
        myAttempted = false;
        myPlayerAnswer = null;
    }
    public void displayQuestion() {
        // Testing purposes. Will be done in GUI implementation once we have SQLite database.
        System.out.println(myQuestion.toString());
    }
    public void lockedDoor(Player thePlayer) {
        if (thePlayer.hasSoulStone() && myAttempted) {
            System.out.println("Would you like to use the Soul Stone to attempt this door again?");
            // In GUI, if player chooses yes button, it will call the useAbility() method for Soul Stone.
        } else if (!thePlayer.hasSoulStone() && myAttempted) {
            System.out.println("Door has been attempted.");
        }
    }
    public void checkPlayerAnswer() {
        if (myQuestion.getAnswer().toLowerCase().equals(myPlayerAnswer.toLowerCase())) {
            myUnlocked = true;
        }
        myAttempted = true;
    }
    public String getAnswer() {
        return myQuestion.getAnswer();
    }
    public boolean getMyUnlock() {
        return myUnlocked;
    }
    public void setMyUnlock(boolean lock) {
        myUnlocked= lock;
    }
    public boolean getAttempt() {
        return myAttempted;
    }
    public void setAttempted(boolean attempt) {
        myAttempted = attempt;
    }
    public String getPlayerMyAnswer() {
        return myPlayerAnswer;
    }
    public void setPlayerMyAnswer(String answer) {
        myPlayerAnswer = answer;
    }
}

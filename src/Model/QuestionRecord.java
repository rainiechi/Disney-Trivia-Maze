package Model;

import java.util.ArrayList;
import java.util.List;

public class QuestionRecord {
    /**
     * The list of used question.
     */
    private List<Integer> myQuestionRecord;

    /**
     * QuestionRecord constructor. It keeps track of used questions.
     */
    public QuestionRecord() {
        myQuestionRecord = new ArrayList<Integer>();
    }

    /**
     * Returns the list of used questions.
     * @return list of used questions
     */
    public List getQuestionRecord() {
        return myQuestionRecord;
    }

    /**
     * Adds the question ID to the list of used questions.
     * @param theID the ID of the question to be added
     */
    public void addToUsedQuestion(final int theID) {
        if (theID < 1 || theID > 82) {
            throw new IllegalArgumentException("ID must be >= 1 and <= 82");
        }
        myQuestionRecord.add(theID);
    }

    /**
     * Checks if the question was already used.
     * Throws exception if all questions have been used.
     * @param theID the ID of the question to be added.
     * @return if the question was already used
     */
    public boolean checkIfUnused(final int theID) {
        int usedQuestions = myQuestionRecord.size();
        if (usedQuestions == 82) {
            throw new RuntimeException("All questions have been used");
        }
        return !(myQuestionRecord.contains(theID));
    }

}

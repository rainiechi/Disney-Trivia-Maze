package Model;

import SQLite.DBRetriever;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * QuestionRecord class keeps track of questions that have been used.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class QuestionRecord implements Serializable {
    /**
     * The list of used question.
     */
    private List<Integer> myQuestionRecord;
    /** Total number of questions */
    private int myTotalQuestions;

    /**
     * QuestionRecord constructor. It keeps track of used questions.
     */
    public QuestionRecord() {
        DBRetriever db = new DBRetriever();
        myQuestionRecord = new ArrayList<Integer>();
        myTotalQuestions = db.getEntryCount();
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
        if (theID < 1 || theID > myTotalQuestions) {
            throw new IllegalArgumentException("Invalid ID" + theID);
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
        if (usedQuestions == myTotalQuestions) {
            throw new RuntimeException("All questions have been used");
        }
        return !(myQuestionRecord.contains(theID));
    }

}

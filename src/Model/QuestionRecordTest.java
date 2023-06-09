package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionRecordTest {

    private QuestionRecord questionRecord;

    @BeforeEach
    public void setUp() {
        questionRecord = new QuestionRecord();
    }

    @Test
    public void testAddToUsedQuestion_ValidID() {
        questionRecord.addToUsedQuestion(1);
        questionRecord.addToUsedQuestion(2);
        questionRecord.addToUsedQuestion(3);

        assertTrue(questionRecord.checkIfUnused(4));
        assertFalse(questionRecord.checkIfUnused(2));
    }


    @Test
    public void testAddToUsedQuestion_InvalidID() {
        assertThrows(IllegalArgumentException.class, () -> {
            questionRecord.addToUsedQuestion(-1);
        });
    }

    //Testing checkIfUnused exception.
    @Test
    public void testCheckIfUnused_AllQuestionsUsed() {
        for (int i = 1; i < 82; i++) {
            questionRecord.addToUsedQuestion(i);
        }
        assertThrows(RuntimeException.class, () -> {
            questionRecord.checkIfUnused(4);
        });
    }

    @Test
    public void testGetQuestionRecord() {
        questionRecord.addToUsedQuestion(1);
        questionRecord.addToUsedQuestion(2);
        questionRecord.addToUsedQuestion(3);

        assertEquals(3, questionRecord.getQuestionRecord().size());
        assertTrue(questionRecord.getQuestionRecord().contains(1));
        assertTrue(questionRecord.getQuestionRecord().contains(2));
        assertTrue(questionRecord.getQuestionRecord().contains(3));
    }


}
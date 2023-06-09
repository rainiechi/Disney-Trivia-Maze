package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    private Question question;

    @BeforeEach
    public void setUp() {
        question = new Question("What is the capital of France?", "Paris",
                "London", "Berlin", "Madrid", "Paris", 1);
    }

    @Test
    public void testConstructor_ValidArguments() {
        assertEquals("What is the capital of France?", question.getMyQuestion());
        assertEquals("Paris", question.getMyAnswer());
        assertEquals("London", question.getMyOption1());
        assertEquals("Berlin", question.getMyOption2());
        assertEquals("Madrid", question.getMyOption3());
        assertEquals("Paris", question.getMyOption4());
        assertEquals(1, question.getMyID());
    }

    @Test
    public void testConstructor_NullArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Question("Question", "Answer", null, "Option 2", "Option 3", "Option 4", 2);
        });
    }

}
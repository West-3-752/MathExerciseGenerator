package MathExerciseGenerator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createExercise() {
        assertEquals("3",new Creator().plusNum("2","1"));
    }

    @Test
    public void correctAnswer() {
    }

    @Test
    public void getGreatestCommonDivisor() {
        assertEquals(6,new Creator().getGreatestCommonDivisor(12,6));
    }

    @Test
    public void reduction() {
        assertEquals("7/24",new Creator().reduction("14/48"));
    }
}
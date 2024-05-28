package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import utils.Normaliser;

public class NormaliserTest {

    @Test
    public void testNormaliseWithExactMatch() {
        Normaliser normaliser = new Normaliser();
        assertEquals("Architect", normaliser.normalise("Architect"));
    }

    @Test
    public void testNormaliseWithCaseInsensitiveMatch() {
        Normaliser normaliser = new Normaliser();
        assertEquals("Software engineer", normaliser.normalise("Software Engineer"));
    }

    @Test
    public void testNormaliseWithPartialMatch() {
        Normaliser normaliser = new Normaliser();
        assertEquals("Quantity surveyor", normaliser.normalise("Quantity surveyor"));
    }

    @Test
    public void testNormaliseWithNoMatch() {
        Normaliser normaliser = new Normaliser();
        assertEquals("No match found", normaliser.normalise("Teacher"));
    }

    @Test
    public void testNormaliseWithEmptyString() {
        Normaliser normaliser = new Normaliser();
        assertEquals("No match found", normaliser.normalise(""));
    }

    @Test
    public void testNormaliseWithNullInput() {
        Normaliser normaliser = new Normaliser();
        assertThrows(IllegalArgumentException.class, () -> {
            normaliser.normalise(null);
        });
    }

    @Test
    public void testNormaliseWithSimilarTitle() {
        Normaliser normaliser = new Normaliser();
        assertEquals("Software engineer", normaliser.normalise("software engenier"));
    }
}

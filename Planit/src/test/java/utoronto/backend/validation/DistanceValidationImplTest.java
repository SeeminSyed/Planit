package utoronto.backend.validation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utoronto.backend.controller.business.validation.ValidationImpl;

public class DistanceValidationImplTest {

    private final ValidationImpl validator = new ValidationImpl();

    @Test
    public void testValidInput() {
        String input = "9";
        boolean expected = true;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should accept normal input", expected, actual);
    }

     @Test
    public void testRandomNonNumericalString() {
        String input = "non Numerical String";
        boolean expected = false;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should reject non numerical string", expected, actual);
    }

      @Test
    public void testFloatInput() {
        String input = "3.47";
        boolean expected = true;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should accept floating point values", expected, actual);
    }

      @Test
    public void testNumberWithSpaces() {
        String input = "    17    ";
        boolean expected = true;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should accept numbers with spaces", expected, actual);
    }

      @Test
    public void testNullString() {
        String input = null;
        boolean expected = false;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should reject null values", expected, actual);
    }

      @Test
    public void testNegativeValue() {
        String input = "-20";
        boolean expected = false;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should reject negative numbers", expected, actual);
    }

      @Test
    public void testPostiveNumberLassThanOne() {
        String input = ".5";
        boolean expected = false;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should reject values less than 1", expected, actual);
    }

    /**
     * For now, we'll accept reject input less than 50.
     */
      @Test
    public void testNumberGreaterThanFifty() {
        String input = "70";
        boolean expected = false;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should reject numbers greater than 50", expected, actual);
    }

      @Test
    public void testExactlyFifty() {
        String input = "50";
        boolean expected = true;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should accept 50", expected, actual);
    }

      @Test
    public void testExactlyOne() {
        String input = "1";
        boolean expected = true;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should accept 1", expected, actual);
    }
}
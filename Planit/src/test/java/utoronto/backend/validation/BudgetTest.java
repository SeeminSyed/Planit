package utoronto.backend.validation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utoronto.backend.controller.business.validation.ValidationImpl;

public class BudgetTest {

    private final ValidationImpl validator = new ValidationImpl();

    @Test
    public void testValidInput() {
        String input = "6";
        boolean expected = true;
        boolean actual = validator.validateBudget(input);
        assertEquals("Should accept normal input", expected, actual);
    }

    @Test
    public void testRandomNonNumericalString() {
        String input = "aaaaaaaaa";
        boolean expected = false;
        boolean actual = validator.validateBudget(input);
        assertEquals("Should reject non numerical string", expected, actual);
    }

    @Test
    public void testFloatInput() {
        String input = "2.56";
        boolean expected = true;
        boolean actual = validator.validateBudget(input);
        assertEquals("Should accept floating point values", expected, actual);
    }

    @Test
    public void testNumberWithSpaces() {
        String input = "    76    ";
        boolean expected = true;
        boolean actual = validator.validateBudget(input);
        assertEquals("Should accept numbers with spaces", expected, actual);
    }

    @Test
    public void testNullString() {
        String input = null;
        boolean expected = false;
        boolean actual = validator.validateBudget(input);
        assertEquals("Should reject null values", expected, actual);
    }

    @Test
    public void testNegativeValue() {
        String input = "-12";
        boolean expected = false;
        boolean actual = validator.validateBudget(input);
        assertEquals("Should reject negative numbers", expected, actual);
    }

    @Test
    public void testPostiveNumberLassThanOne() {
        String input = ".5";
        boolean expected = false;
        boolean actual = validator.validateBudget(input);
        assertEquals("Should reject values less than 1", expected, actual);
    }

    @Test
    public void testExactlyOne() {
        String input = "1";
        boolean expected = true;
        boolean actual = validator.validateDistance(input);
        assertEquals("Should accept 1", expected, actual);
    }
}
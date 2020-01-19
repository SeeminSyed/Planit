package utoronto.backend.validation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utoronto.backend.controller.business.validation.ValidationImpl;

public class NumberOfParticipantsTest {
	private ValidationImpl val = new ValidationImpl();
	
    @Test
    public void testEmptyString() {
        assertEquals("Should return false", false, val.validateparticipants(""));
    }
    
    @Test
    public void testNegativeInput() {
        assertEquals("Should return false", false, val.validateparticipants("-1"));
    }
    
    @Test
    public void testZeroInput() {
        assertEquals("Should return false", false, val.validateparticipants("0"));
    }
    
    @Test
    public void testNonIntegerInput1() {
        assertEquals("Should return false", false, val.validateparticipants("invalid input"));
    }
    
    @Test
    public void testNonIntegerInput2() {
        assertEquals("Should return false", false, val.validateparticipants("invalid3input"));
    }
    
    @Test
    public void testNonIntegerInput3() {
        assertEquals("Should return false", false, val.validateparticipants("2.5"));
    }
    
    @Test
    public void testPositiveInput() {
        assertEquals("Should return true", true, val.validateparticipants("2"));
    }
    
    @Test
    public void testValidInputithLeadingWhitespace() {
        assertEquals("Should return true", true, val.validateparticipants("  2"));
    }
    
    @Test
    public void testValidInputWithTrailingWhitespace() {
        assertEquals("Should return true", true, val.validateparticipants("2  "));
    }
    
    @Test
    public void testValidInputWithLeadingAndTrailingWhitespace() {
        assertEquals("Should return true", true, val.validateparticipants("  2  "));
    }

}

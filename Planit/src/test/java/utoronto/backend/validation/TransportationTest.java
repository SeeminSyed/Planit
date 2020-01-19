package utoronto.backend.validation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utoronto.backend.controller.business.validation.ValidationImpl;

public class TransportationTest {
	private ValidationImpl val = new ValidationImpl();
	
	/**
	 * Options: 
	 * 1. Walking
	 * 2. Driving
	 * 3. Taking public transit
	 */
	
    @Test
    public void testEmptyString() {
        assertEquals("Should return false", false, val.validateTransportationModes(""));
    }
    
    @Test
    public void testValidOptionChosen() {
        assertEquals("Should return true", true, val.validateTransportationModes("1"));
    }
    
    @Test
    public void testInvalidOptionChosen1() {
        assertEquals("Should return false", false, val.validateTransportationModes("4"));
    }
    
    @Test
    public void testInvalidOptionChosen2() {
        assertEquals("Should return false", false, val.validateTransportationModes("-1"));
    }
    
    @Test
    public void testInvalidOptionChosen3() {
        assertEquals("Should return false", false, val.validateTransportationModes("0"));
    }
    
    @Test
    public void testMultipleValidOptionsChosen() {
        assertEquals("Should return true", true, val.validateTransportationModes("1, 2"));
    }
    
    @Test
    public void testMultipleValidInvalidOptionsChosen() {
        assertEquals("Should return false", false, val.validateTransportationModes("1, 2, 5"));
    }
    
    @Test
    public void testDuplicateValidOptionsChosen() {
        assertEquals("Should return true", true, val.validateTransportationModes("1, 2, 1, 1"));
    }
    
    @Test
    public void testValidOptionsWithNoSpaces() {
        assertEquals("Should return true", true, val.validateTransportationModes("1,2,3"));
    }


}

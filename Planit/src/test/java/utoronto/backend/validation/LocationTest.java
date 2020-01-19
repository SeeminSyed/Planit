package utoronto.backend.validation;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utoronto.backend.controller.business.validation.LocationValidationImpl;
import utoronto.backend.controller.business.validation.ValidationImpl;
import utoronto.backend.controller.connection.Connector;


public class LocationTest {

    private ValidationImpl val = new LocationValidationImpl();

    @BeforeClass
    public static void connect() {
        Connector.establishConnections();
    }

    @Before

    public void setUp(){

    }


    @Test

    public void testEmptyCountry() {

        assertEquals("Should return false", false, val.validateCountry(" "));

    }

    @Test

    public void testEmptyCity() {

        assertEquals("Should return false", false, val.validateCity(" ", "Canada"));

    }

    @Test

    public void testNullCountry() {

        assertEquals("Should return false", false, val.validateCountry(null));

    }

    @Test

    public void testNullCity() {

        assertEquals("Should return false", false, val.validateCity(null, "Canada"));

    }

    @Test

    public void testInvalidCountry() {

        assertEquals("Should return false", false, val.validateCountry("Can7da"));

    }

    @Test

    public void testNotRealCity() {

        assertEquals("Should return false", false, val.validateCity("6ix", "Canada"));

    }

    @Test

    public void testValidCountry() {

        assertEquals("Should return true", true, val.validateCountry("Canada"));

    }

    @Test

    public void testValidCityInvalidCountry() {

        assertEquals("Should return false", false, val.validateCity("Toronto", "U.S.A"));

    }

    @Test

    public void testValidCity() {

        assertEquals("Should return true", true, val.validateCity("Toronto", "Canada"));

    }

    @After

    public void after() {

    }

    @AfterClass
    public static void closeConnections() {
        Connector.closeConnections();
    }

}
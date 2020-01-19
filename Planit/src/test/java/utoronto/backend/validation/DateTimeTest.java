package utoronto.backend.validation;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import utoronto.backend.controller.business.validation.ValidationImpl;

public class DateTimeTest {
    private ValidationImpl val = new ValidationImpl();
    private SimpleDateFormat dateFormat = val.getDateFormat();
    private SimpleDateFormat timeFormat = val.getTimeFormat();

    @Before
    public void setUp() throws Exception {
        dateFormat.setLenient(false);
        timeFormat.setLenient(false);

    }

    /* ---------------------------------- Date Tests ---------------------------------- */

    @Test
    public void testValidatePastDate() {
        assertEquals("Should return false", false, val.validateDate("11/11/1111"));
    }

    @Test
    public void testValidateCurrentDate() {
        String date = dateFormat.format(new Date());
        assertEquals("Should return true", true, val.validateDate(date));
    }

    @Test
    public void a() {
        assertEquals("Should return false", false, val.validateDate("22/22/22"));
    }

    @Test
    public void testValidateFutureDate() {
        assertEquals("Should return true", true, val.validateDate("12/12/2222"));
    }

    @Test
    public void testValidateInvalidFormatDate1() {
        assertEquals("Should return false", false, val.validateDate("12122222"));
    }

    @Test
    public void testValidateInvalidFormatDate2() {
        assertEquals("Should return false", false, val.validateDate("12/2222"));

    }

    @Test
    public void testValidateInvalidFormatDate3() {
        assertEquals("Should return false", false, val.validateDate("12/22/22"));

    }

    /* ---------------------------------- Start Time Tests ---------------------------------- */

    @Test
    public void testValidateStartTimeStartOfFutureDay() {
        assertEquals("Should return true", true, val.validateStartTime("0000", "12/12/2222"));
    }

    @Test
    public void b() {
        assertEquals("Should return false", false, val.validateStartTime("0099", "12/12/2222"));
    }

    @Test
    public void testValidateStartTimePostCurrent() {
        String date = dateFormat.format(new Date());
        Long time = Long.parseLong(timeFormat.format(new Date()));
        if (time < 2259) {
            assertEquals("Should return true", true, val.validateStartTime("2259", date));
        } else {
            assertEquals("Try again in 1 hour", true, false);
        }
    }

    @Test
    public void testValidateStartTimePriorCurrent() {
        String date = dateFormat.format(new Date());
        Long time = Long.parseLong(timeFormat.format(new Date()));
        if (time > 0000) {
            assertEquals("Should return false", false, val.validateStartTime("0000", date));
        } else {
            assertEquals("Try again in 1 minute", true, false);
        }
    }

    @Test
    public void testValidateStartTimeEndOfDay() {
        assertEquals("Should return false", false, val.validateStartTime("2359", "12/12/2222"));
    }

    @Test
    public void testValidateCurrentStartTime() {
        String date = dateFormat.format(new Date());
        assertEquals("Should return false", false, val.validateStartTime(timeFormat.format(new Date()), date));
    }

    @Test
    public void testValidateInvalidFormatStartTime1() {
        assertEquals("Should return false", false, val.validateStartTime("-50", "12/12/2222"));
    }

    @Test
    public void testValidateInvalidFormatStartTime2() {
        assertEquals("Should return false", false, val.validateStartTime("11111", "12/12/2222"));

    }

    @Test
    public void testValidateInvalidFormatStartTime3() {
        assertEquals("Should return false", false, val.validateStartTime("1.11", "12/12/2222"));

    }

    /* ---------------------------------- End Time Tests ---------------------------------- */

    @Test
    public void testValidateEndTimeBeforeStart() {
        assertEquals("Should return false", false, val.validateEndTime("0000", "1010"));
    }

    @Test
    public void testValidateEndTimeAtStart() {
        assertEquals("Should return false", false, val.validateEndTime("1010", "1010"));
    }

    @Test
    public void testValidateEndTimeLessThanHourAfterStart() {
        assertEquals("Should return false", false, val.validateEndTime("1109", "1010"));
    }

    @Test
    public void testValidateEndTimeHourAfterStart() {
        assertEquals("Should return true", true, val.validateEndTime("1110", "1010"));
    }

    @Test
    public void testValidateEndTimeMoreThanHourAfterStart() {
        assertEquals("Should return true", true, val.validateEndTime("1200", "1010"));
    }

    @Test
    public void testValidateEndTimeEndOfDay() {
        assertEquals("Should return true", true, val.validateEndTime("2359", "0000"));
    }

    @Test
    public void testValidateInvalidFormatEndTime1() {
        assertEquals("Should return false", false, val.validateEndTime("-50", "0000"));
    }

    @Test
    public void testValidateInvalidFormatEndTime2() {
        assertEquals("Should return false", false, val.validateEndTime("111111", "0000"));
    }

    @Test
    public void testValidateInvalidFormatEndTime3() {
        assertEquals("Should return false", false, val.validateEndTime("1200.11", "0000"));

    }


}
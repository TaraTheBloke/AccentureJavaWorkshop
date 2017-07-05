package finish.date;

import static exercises.date.Date.dateFor;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import exercises.date.DateFormatException;

public class DateTest {

    @Test
    public void shouldCompareSameDateAsEqual() throws Exception {
        assertThat(dateFor("02/22/2011"), is(dateFor("02/22/2011")));
        assertThat(dateFor("02/22/2011"), is(dateFor("2011-02-22")));
    }

    @Test
    public void shouldCompareDifferentDatesAsNotEqual() throws Exception {
        assertThat(dateFor("02/22/2011"), not(dateFor("01/22/2011")));
        assertThat(dateFor("02/22/2011"), not(dateFor("03/22/2011")));
        assertThat(dateFor("02/22/2011"), not(dateFor("02/21/2011")));
        assertThat(dateFor("02/22/2011"), not(dateFor("02/23/2011")));
        assertThat(dateFor("02/22/2011"), not(dateFor("02/22/2010")));
        assertThat(dateFor("02/22/2011"), not(dateFor("02/22/2012")));
    }

    @Test
    public void shouldAddDaysToEndOfLongMonth() throws Exception {
        assertThat(dateFor("01/30/2011").addDays(2), is(dateFor("02/01/2011")));
        assertThat(dateFor("03/31/2011").addDays(2), is(dateFor("04/02/2011")));
        assertThat(dateFor("05/29/2011").addDays(3), is(dateFor("06/01/2011")));
        assertThat(dateFor("07/30/2011").addDays(2), is(dateFor("08/01/2011")));
        assertThat(dateFor("08/30/2011").addDays(2), is(dateFor("09/01/2011")));
        assertThat(dateFor("10/30/2011").addDays(2), is(dateFor("11/01/2011")));
    }

    @Test
    public void shouldAddDaysToEndOfShortMonth() throws Exception {
        assertThat(dateFor("04/30/2011").addDays(2), is(dateFor("05/02/2011")));
        assertThat(dateFor("06/29/2011").addDays(2), is(dateFor("07/01/2011")));
        assertThat(dateFor("09/30/2011").addDays(2), is(dateFor("10/02/2011")));
        assertThat(dateFor("11/30/2011").addDays(2), is(dateFor("12/02/2011")));
    }

    @Test
    public void shouldAddDaysToEndOfLeapFebruary() throws Exception {
        assertThat(dateFor("02/28/2008").addDays(2), is(dateFor("03/01/2008")));
        assertThat(dateFor("02/29/2000").addDays(2), is(dateFor("03/02/2000")));
    }

    @Test
    public void shouldAddDaysToEndOfNonLeapFebruary() throws Exception {
        assertThat(dateFor("02/28/2011").addDays(2), is(dateFor("03/02/2011")));
        assertThat(dateFor("02/27/1900").addDays(2), is(dateFor("03/01/1900")));
    }

    @Test
    public void shouldAddDaysToEndOfYear() throws Exception {
        assertThat(dateFor("12/28/2011").addDays(4), is(dateFor("01/01/2012")));
    }

    @Test
    public void shouldAddNoDaysToDate() throws Exception {
        assertThat(dateFor("12/28/2011").addDays(0), is(dateFor("12/28/2011")));
    }

    @Test
    public void shouldThrowDateFormatExceptionWithInvalidDate() throws Exception {
        verifyInvalidDateFormat("13/28/2011");
        verifyInvalidDateFormat("12/32/2011");
        verifyInvalidDateFormat("abc");
    }

    private void verifyInvalidDateFormat(String date) {
        try {
            dateFor(date);
            fail(date + " was treated as a valid date");
        } catch (DateFormatException e) {
            // success!
        }
    }
}

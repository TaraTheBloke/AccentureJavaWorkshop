package exercises.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simplistic immutable date class.  Provides factory
 * methods for instantiating immutable dates from a
 * formatted string (i.e. MM/dd/yyyy and yyyy-MM-dd).
 * Functions have also been supplied for adding days and
 * years.  However, all such functions are side effect
 * free - they do not change the immutable state of
 * the current object but instead return a new Date object
 * <p>
 * For example, to add 1 year and 2 days to today's date,
 * you would
 * <p>
 * <pre>
 *     Date date = Date.today().addYears(1).addDays(2);
 * </pre>
 */
public class Date {

    private final Calendar calendar;

   /**
    * Returns today's date.
    */
    public static Date today() {
        return new Date();
    }

   /**
    * Returns date representation of <code>date</code>.
    *
    * @param   date an MM/dd/yyyy or yyyy-MM-dd formatted date.
    * @return  a newly constructed date object
    * @throws  DateFormatException
    *          if <code>date</code> is an invalid date
    */
    public static Date dateFor(String date) throws DateFormatException {
        return new Date(date);
    }

   /**
    * Returns a new date representing the supplied number of days
    * added to this date.
    *
    * @param  days  the number of days to add (or subtract)
    * @return a new date object representing <code>days</code> added
    *         to this <code>this</code> date.  If <code>days == 0</code>
    *         then <code>this</code> date has been returned.
    */
    public Date addDays(int days) {
        if (days == 0) {
            return this;
        }
        return add(Calendar.DATE, days);
    }

   /**
    * Returns a new date representing the supplied number of years
    * added to this date.
    *
    * @param  years  the number of years to add (or subtract)
    * @return a new date object representing <code>years</code> added
    *         to <code>this</code> date.  If <code>years == 0</code>
    *         then <code>this</code> date has been returned.
    */
    public Date addYears(int years) {
        if (years == 0) {
            return this;
        }
        return add(Calendar.YEAR, years);
    }

   /**
    * Returns string representation of this date.
    *
    * @see #dbDateString()
    */
    @Override
    public String toString() {
        return dbDateString();
    }

   /**
    * Returns string representation of this date in
    * standard database format.  That is, in 'yyyy-MM-dd'
    * format.
    */
    public String dbDateString() {
        StringBuffer sb = new StringBuffer(getYear(calendar));
        sb.append("-");
        sb.append(getMonth(calendar));
        sb.append("-");
        sb.append(getDay(calendar));
        return sb.toString();
    }

   /**
    * Returns string representation of this date in 'US'
    * format.  That is, in 'MM/dd/yyyy' format.
    */
    public String usDateString()
    {
        StringBuilder sb = new StringBuilder(getMonth(calendar));
        sb.append("/");
        sb.append(getDay(calendar));
        sb.append("/");
        sb.append(getYear(calendar));
        return sb.toString();
    }

    /**
     * Returns this date's hash code
     */
    @Override
    public int hashCode() {
        return calendar.hashCode();
    }

   /**
    * Is object equal to this date.
    *
    * @param  o  object to compare with.
    * @return <code>true</code> if <code>o</code> equals <code>this</code>
    *         date. Otherwise <code>false</code> has been returned.
    */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Date) {
            Calendar c = ((Date) o).calendar;
            return isEqual(calendar, c, Calendar.DAY_OF_YEAR) &&
                   isEqual(calendar, c, Calendar.YEAR);
        }
        return false;
    }

    private boolean isEqual(Calendar c1, Calendar c2, int field) {
        return c1.get(field) == c2.get(field);
    }

    private Date add(int field, int amount) {
        Calendar c = (Calendar)calendar.clone();
        c.add(field, amount);
        String date = getYear(c) + "-" + getMonth(c) + "-" + getDay(c);
        return dateFor(date);
    }

    private String getYear(Calendar calendar) {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    private String getMonth(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10)
        {
            return "0" + month;
        }
        return String.valueOf(month);
    }

    private String getDay(Calendar calendar)
    {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < 10)
        {
            return "0" + day;
        }
        return String.valueOf(day);
    }

    private Date() {
        calendar = Calendar.getInstance();
    }

    private Date(String date) throws DateFormatException {
        try {
            String pattern = (date.indexOf("/") == 2) ? "MM/dd/yyyy" : "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
            calendar = sdf.getCalendar();
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            throw new DateFormatException(date + " is formatted incorrectly", e);
        }
    }
}
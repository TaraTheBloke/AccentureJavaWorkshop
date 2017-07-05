package exercises.date;

/**
 * Exception thrown when an invalid date is supplied 
 * to {@link Date#newDate}.
 */
public class DateFormatException extends RuntimeException
{
    /** Generated serial ID */
    private static final long serialVersionUID = -5208268610809352956L;

    /**
     * Constructs a date format exception.
     * @param message non-null description of the error.
     * @param cause   underlying cause of the error.
     */
    public DateFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}

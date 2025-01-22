package daisy.error;

/**
 * The IllegalDeadlineFormatException class signifies the error condition which a user input for a deadline does not match
 * the expected format of the program. This is caused by either the user has a missing task name/due date, forgets to
 * use "/by" to separate the task name and date, or has not followed the date and time format for the due date.
 */
public class IllegalDeadlineFormatException extends Exception{
}

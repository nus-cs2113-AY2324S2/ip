package daisy.error;

/**
 * The IllegalEventFormatException class signifies the error condition which a user input for an event does not match the
 * expected format of the program. This is caused by either the user have a missing task name/date/from time/to time,
 * forgets to use "/from" and "/to" to separate the different information, or is not following the format for the date and
 * time inputs.
 */

public class IllegalEventFormatException extends Exception{
}

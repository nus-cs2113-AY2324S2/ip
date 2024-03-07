package daisy.error;

/**
 * The IllegalEventFormatException class signifies the error condition which a user input for an event does not match the
 * expected format of the program. This is caused by either the user have a missing task name/date/from time/to time, or
 * forgets to use "/from" and "/to" to separate the task name, from time and to time.
 */

public class IllegalEventFormatException extends Exception{
}

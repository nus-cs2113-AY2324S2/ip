package geepee.system;

import geepee.exceptions.EmptyDescriptionException;
import geepee.exceptions.MissingDeadlineException;
import geepee.exceptions.MissingFromException;
import geepee.exceptions.MissingToException;

public abstract class InputParser {

    /** Required padding to extract description of a todo task */
    private static final int TODO_PADDING = 5;

    /** Required padding to extract description of a deadline task */
    private static final int DEADLINE_PADDING = 9;
    /** Required padding to extract the deadline of a deadline task */
    private static final int BY_PADDING = 3;

    /** Required padding to extract the description of an event task */
    private static final int EVENT_PADDING = 6;
    /** Required padding to extract the start of an event task */
    private static final int FROM_PADDING = 5;
    /** Required padding to extract the end of an event task */
    private static final int TO_PADDING = 3;

    /** Index of task in a String array */
    private static final int TASK_INDEX = 1;

    /**
     * Extracts the description of a todo task from a line of user input.
     * 
     * @param line Line of user input.
     * @return Description of todo task.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public static String getTodoDescription(String line) throws EmptyDescriptionException {
        // check for empty todo description
        if (line.equals("todo")) {
            throw new EmptyDescriptionException();
        }
        return line.substring(TODO_PADDING).trim();
    }

    /**
     * Extracts the description of a deadline task from a line of user input.
     * 
     * @param line Line of user input.
     * @param byIndex Index of "/by" keyword.
     * @return Description of deadline task.
     * @throws EmptyDescriptionException If the description is empty.
     * @throws MissingDeadlineException If the "/by" keyword is missing.
     */
    public static String getDeadlineDescription(String line, int byIndex) 
            throws EmptyDescriptionException, MissingDeadlineException {
        // check for empty deadline description and lack of "/by" keyword
        if (line.equals("deadline")) {
            throw new EmptyDescriptionException();
        } else if (byIndex == -1) {
            throw new MissingDeadlineException();
        }
        String deadlineDescription = line.substring(DEADLINE_PADDING, byIndex).trim();
        
        // account for edge case where deadline description is whitespace
        if (deadlineDescription.equals("")) {
            throw new EmptyDescriptionException();
        }
        return deadlineDescription;
    }

    /**
     * Extracts the deadline of a deadline task from a line of user input.
     * 
     * @param line Line of user input.
     * @param byIndex Index of "/by" keyword.
     * @return Deadline of deadline task.
     * @throws MissingDeadlineException If the deadline is empty.
     */
    public static String getDeadlineBy(String line, int byIndex) throws MissingDeadlineException {
        // check for empty deadline
        if (byIndex + BY_PADDING > line.length() - 1) {
            throw new MissingDeadlineException();
        }
        String deadlineBy = line.substring(byIndex + BY_PADDING).trim();
        return deadlineBy;
    }

    /**
     * Extracts the description of an event task from a line of user input.
     * 
     * @param line Line of user input.
     * @param fromIndex Index of "/from" keyword.
     * @return Description of event task.
     * @throws EmptyDescriptionException If the description is empty.
     * @throws MissingFromException If the "/from" keyword is missing.
     */
    public static String getEventDescription(String line, int fromIndex)
            throws EmptyDescriptionException, MissingFromException {
        // check for empty event description and lack of "/from" keyword
        if (line.equals("event")) {
            throw new EmptyDescriptionException();
        } else if (fromIndex == -1) {
            throw new MissingFromException();
        }
        String eventDescription = line.substring(EVENT_PADDING, fromIndex).trim();
        
        // account for edge case where event description is whitespace
        if (eventDescription.equals("")) {
            throw new EmptyDescriptionException();
        }
        return eventDescription;
    }

    /**
     * Extracts the start of an event task from a line of user input.
     * 
     * @param line Line of user input.
     * @param fromIndex Index of "/from" keyword.
     * @param toIndex Index of "/to" keyword.
     * @return Start of the event task.
     * @throws MissingFromException If the start of the event is empty.
     * @throws MissingToException If the "/to" keyword is missing.
     */
    public static String getEventFrom(String line, int fromIndex, int toIndex)
            throws MissingFromException, MissingToException {
        // check if start of event is empty and lack of "/to" keyword
        if (fromIndex + FROM_PADDING > line.length() - 1) {
            throw new MissingFromException();
        } else if (toIndex == -1) {
            throw new MissingToException();
        }
        String eventFrom = line.substring(fromIndex + FROM_PADDING, toIndex).trim();

        // account for edge case where start of event is whitespace
        if (eventFrom.equals("")) {
            throw new MissingFromException();
        }
        return eventFrom;
    }

    /**
     * Extracts the end of an event task from a line of user input.
     * 
     * @param line Line of user input.
     * @param toIndex Index of "/to" keyword.
     * @return End of the event task.
     * @throws MissingToException If the end of the event is empty.
     */
    public static String getEventTo(String line, int toIndex) throws MissingToException {
        // check if end of event is empty
        if (toIndex + TO_PADDING > line.length() - 1) {
            throw new MissingToException();
        }
        String eventTo = line.substring(toIndex + TO_PADDING).trim();
        return eventTo;
    }

    /**
     * Extracts the index of a task from line of user input.
     * 
     * @param line Line of user input.
     * @return Index of the task.
     */
    public static int getTaskIndex(String line) {
        String[] words = line.split(" ");
        return Integer.parseInt(words[TASK_INDEX]) - 1;
    }
}

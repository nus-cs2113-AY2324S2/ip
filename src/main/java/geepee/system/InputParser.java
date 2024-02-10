package geepee.system;

import geepee.exceptions.*;

public class InputParser {

    // constant to determine required padding to extract the name of a Todo object
    private static final int TODO_PADDING = 5;

    // constants to determine required padding to extract the name of a Deadline
    // object, and when it needs to be completed by
    private static final int DEADLINE_PADDING = 9;
    private static final int BY_PADDING = 4;

    public static String getTodoDescription(String line) throws EmptyDescriptionException {
        if (line.equals("todo")) {
            throw new EmptyDescriptionException();
        }
        return line.substring(TODO_PADDING).trim();
    }

    public static String getDeadlineDescription(String line, int byIndex) 
            throws EmptyDescriptionException, MissingDeadlineException {
        if (line.equals("deadline")) {
            throw new EmptyDescriptionException();
        } else if (byIndex == -1) {
            throw new MissingDeadlineException();
        }
        String deadlineDescription = line.substring(DEADLINE_PADDING, byIndex).trim();
        if (deadlineDescription.equals("")) {
            throw new EmptyDescriptionException();
        }
        return deadlineDescription;
    }

    public static String getDeadlineBy(String line, int byIndex) throws MissingDeadlineException {
        if (byIndex + BY_PADDING > line.length() - 1) {
            throw new MissingDeadlineException();
        }
        String deadlineBy = line.substring(byIndex + BY_PADDING).trim();
        return deadlineBy;
    }

    public static void checkValidEventDescription(String line) throws EmptyDescriptionException {
        if (line.equals("event")) {
            throw new EmptyDescriptionException();
        }
    }
}

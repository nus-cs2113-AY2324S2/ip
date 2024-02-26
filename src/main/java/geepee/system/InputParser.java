package geepee.system;

import geepee.exceptions.EmptyDescriptionException;
import geepee.exceptions.MissingDeadlineException;
import geepee.exceptions.MissingFromException;
import geepee.exceptions.MissingToException;
import geepee.exceptions.MissingIndexException;

public abstract class InputParser {

    // constant to determine required padding to extract the name of a Todo object
    private static final int TODO_PADDING = 5;

    // constants to determine required padding to extract the name of a Deadline
    // object, and when it needs to be completed by
    private static final int DEADLINE_PADDING = 9;
    private static final int BY_PADDING = 3;

    // constants to determine required padding to extract the name of a Event
    // object, and when it starts and ends
    private static final int EVENT_PADDING = 6;
    private static final int FROM_PADDING = 5;
    private static final int TO_PADDING = 3;

    private static final int TASK_INDEX_PADDING = 7;

    private static final int KEYWORD_PADDING = 5;

    public static String getTodoDescription(String line) throws EmptyDescriptionException {
        //check for empty todo description
        if (line.equals("todo")) {
            throw new EmptyDescriptionException();
        }
        return line.substring(TODO_PADDING).trim();
    }

    public static String getDeadlineDescription(String line, int byIndex) 
            throws EmptyDescriptionException, MissingDeadlineException {
        //check for empty deadline description and lack of "/by" keyword
        if (line.equals("deadline")) {
            throw new EmptyDescriptionException();
        } else if (byIndex == -1) {
            throw new MissingDeadlineException();
        }
        String deadlineDescription = line.substring(DEADLINE_PADDING, byIndex).trim();
        
        //account for edge case where deadline description is whitespace
        if (deadlineDescription.equals("")) {
            throw new EmptyDescriptionException();
        }
        return deadlineDescription;
    }

    public static String getDeadlineBy(String line, int byIndex) throws MissingDeadlineException {
        //check for empty deadline do by
        if (byIndex + BY_PADDING > line.length() - 1) {
            throw new MissingDeadlineException();
        }
        String deadlineBy = line.substring(byIndex + BY_PADDING).trim();
        return deadlineBy;
    }

    public static String getEventDescription(String line, int fromIndex)
            throws EmptyDescriptionException, MissingFromException {
        //check for empty event description and lack of "/from" keyword
        if (line.equals("event")) {
            throw new EmptyDescriptionException();
        } else if (fromIndex == -1) {
            throw new MissingFromException();
        }
        String eventDescription = line.substring(EVENT_PADDING, fromIndex).trim();
        
        //account for edge case where event description is whitespace
        if (eventDescription.equals("")) {
            throw new EmptyDescriptionException();
        }
        return eventDescription;
    }

    public static String getEventFrom(String line, int fromIndex, int toIndex)
            throws MissingFromException, MissingToException {
        //check for empty event start (from) and lack of "/to" keyword
        if (fromIndex + FROM_PADDING > line.length() - 1) {
            throw new MissingFromException();
        } else if (toIndex == -1) {
            throw new MissingToException();
        }
        String eventFrom = line.substring(fromIndex + FROM_PADDING, toIndex).trim();

        //account for edge case where event start (from) description is whitespace
        if (eventFrom.equals("")) {
            throw new MissingFromException();
        }
        return eventFrom;
    }

    public static String getEventTo(String line, int toIndex) throws MissingToException {
        //check for empty event end (to)
        if (toIndex + TO_PADDING > line.length() - 1) {
            throw new MissingToException();
        }
        String eventTo = line.substring(toIndex + TO_PADDING).trim();
        return eventTo;
    }

    public static int getTaskIndex(String line) throws MissingIndexException {
        if (line.equals("delete") || line.equals("mark") || line.equals("unmark")) {
            throw new MissingIndexException();
        }
        String index = line.substring(TASK_INDEX_PADDING).trim();
        return Integer.parseInt(index) - 1;
    }

    public static String getKeyword(String line) throws EmptyDescriptionException {
        if (line.equals("find")) {
            throw new EmptyDescriptionException();
        }
        String keyword = line.substring(KEYWORD_PADDING).trim();
        return keyword;
    }
}

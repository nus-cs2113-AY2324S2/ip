package daisy.parser;

import daisy.error.IllegalDeadlineFormatException;
import daisy.error.IllegalEventFormatException;
import daisy.error.IllegalTodoFormatException;
import daisy.error.MissingInformationException;

/**
 * The Parser class handles all user input. It classifies information within the user input into suitable segments such
 * that it is understandable by the other classes.
 */

public class Parser {
    private static String action;
    private static String additionalNotes;

    /**
     * Constructs a Parser instance based on the user's input command. It identifies the first word of the user as the
     * operation keyword and the rest as additional information that could be needed by other class operations.
     * @param command the user's input command
     */
    public Parser(String command) {
        String[] separate_commands = command.split(" ",2);
        action = separate_commands[0];
        if (separate_commands.length < 2) {
            additionalNotes = null;
        }
        else {
            additionalNotes = separate_commands[1];
        }
    }

    /**
     * Returns the operation keyword extracted from the user's input command
     * @return the operation keyword of the user's input command
     */
    public String getAction() {
        return action;
    }

    /**
     * Attempts to extract an index variable from the user input's additional information. Is called when the main program
     * confirms that the user wish to execute list operations(delete/mark/unmark).
     * @return the index of the target task in the task list
     * @throws MissingInformationException if there are no additional information. This indicates that the user has missing
     * instructions for the index.
     */
    public int getIndexFromCommand()
        throws MissingInformationException {
        if (additionalNotes == null) {
            throw new MissingInformationException();
        }
        return Integer.parseInt(additionalNotes)-1;
    }

    /**
     * Attempts to extract a task name from the user input's additional information. Is called when the main program confirms
     * that the user wish to create a new todo.
     * @return the task name of the todo
     * @throws IllegalTodoFormatException if there are no additional information. This indicates that the user has missing
     * task name for the todo
     */
    public String getTodoInfo()
        throws IllegalTodoFormatException {
        if (additionalNotes == null) {
            throw new IllegalTodoFormatException();
        }
        return additionalNotes;
    }

    /**
     * Attempts to extract a task name and date from the user input's additional information. Is called when the main program
     * confirms that the user wish to create a new deadline.
     * @return a String array consisting of task name on its 0th index and due date on its 1st index
     * @throws IllegalDeadlineFormatException if there are no additional information or when the "/by" keyword does not
     * separate the task name and due date.
     */
    public String[] getDeadlineInfo()
        throws IllegalDeadlineFormatException {
        if (additionalNotes == null) {
            throw new IllegalDeadlineFormatException();
        }
        String [] taskNameDate = additionalNotes.split(" /by ");
        if (taskNameDate.length < 2) {
            throw new IllegalDeadlineFormatException();
        }
        return taskNameDate;
    }

    /**
     * Attempts to extract a task name, date, from time and to time from the user input's additional information. Is called
     * when the main program confirms that the user wish to create a new event.
     * @return a String array consisting of task name on its 0th index, date on its 1st index, from time on its 2nd index,
     * and to time on its 3rd index.
     * @throws IllegalEventFormatException if there are no additional information or when the "/from" and "/to" does not
     * separate the task name, date, from time and to time.
     */
    public String[] getEventInfo()
        throws IllegalEventFormatException {
        if (additionalNotes == null) {
            throw new IllegalEventFormatException();
        }
        int from = additionalNotes.indexOf(" /from ");
        int to = additionalNotes.indexOf(" /to ");
        if (from == -1 || to == -1 ) {
            throw new IllegalEventFormatException();
        }
        return new String[] {additionalNotes.substring(0, from),additionalNotes.substring(from + " /from ".length(), to), additionalNotes.substring(to+" /to ".length())};
    }

}




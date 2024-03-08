package ui;

import exception.EkudException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

/**
 * This Parser class is used to interpret user input.
 */
public class Parser {

    /**
     * Returns a new Todo which will be added to the list of tasks.
     *
     * @param userInput contains the task description.
     * @return a new Todo task.
     * @throws EkudException if no task description is given.
     */
    public static Todo parseTodo(String userInput) throws EkudException {

        int dividerPosition = userInput.indexOf(" ");
        if(dividerPosition == -1) {
            throw new EkudException();
        }
        int descriptionStart = dividerPosition + 1;
        return new Todo(userInput.substring(descriptionStart).trim());
    }

    /**
     * Returns a new Deadline which will be added to the list of tasks.
     *
     * @param userInput contains the task description and deadline to meet.
     * @return a new Deadline task.
     * @throws IndexOutOfBoundsException if wrong input format is given.
     */
    public static Deadline parseDeadline(String userInput) throws EkudException, IndexOutOfBoundsException {

        int dividerPosition = userInput.indexOf(" ");
        int slashPosition = userInput.indexOf("/by");
        int descriptionStart = dividerPosition + 1;
        int descriptionEnd = slashPosition - 1;
        int byStart = slashPosition + 4;
        return new Deadline(userInput.substring(descriptionStart, descriptionEnd).trim(),
                userInput.substring(byStart).trim());
    }

    /**
     * Returns a new Event task which will be added to the list of tasks.
     *
     * @param userInput contains the task description and its ongoing period.
     * @return a new Event task.
     * @throws IndexOutOfBoundsException if wrong input format is given.
     */
    public static Event parseEvent(String userInput) throws EkudException, IndexOutOfBoundsException {

        int dividerPosition = userInput.indexOf(" ");
        int descriptionStart = dividerPosition + 1;
        int descriptionEnd = userInput.indexOf("/from") - 1;
        int fromStart = userInput.indexOf("/from") + 6;
        int fromEnd = userInput.indexOf("/to") - 1;
        int toStart = userInput.indexOf("/to") + 4;

        return new Event(userInput.substring(descriptionStart, descriptionEnd).trim(),
                userInput.substring(fromStart, fromEnd).trim(),
                userInput.substring(toStart).trim());
    }
}

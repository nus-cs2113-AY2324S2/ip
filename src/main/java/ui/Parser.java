package ui;

import exception.EkudException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

public class Parser {

    public static Todo parseTodo(String userInput) throws EkudException {

        int dividerPosition = userInput.indexOf(" ");
        if(dividerPosition == -1) {
            throw new EkudException();
        }
        int descriptionStart = dividerPosition + 1;
        return new Todo(userInput.substring(descriptionStart));
    }

    public static Deadline parseDeadline(String userInput) throws EkudException, IndexOutOfBoundsException {

        int dividerPosition = userInput.indexOf(" ");
        int slashPosition = userInput.indexOf("/by");
        int descriptionStart = dividerPosition + 1;
        int descriptionEnd = slashPosition - 1;
        int byStart = slashPosition + 4;
        return new Deadline(userInput.substring(descriptionStart, descriptionEnd),
                userInput.substring(byStart));
    }

    public static Event parseEvent(String userInput) throws EkudException, IndexOutOfBoundsException {

        int dividerPosition = userInput.indexOf(" ");
        int descriptionStart = dividerPosition + 1;
        int descriptionEnd = userInput.indexOf("/from") - 1;
        int fromStart = userInput.indexOf("/from") + 6;
        int fromEnd = userInput.indexOf("/to") - 1;
        int toStart = userInput.indexOf("/to") + 4;

        return new Event(userInput.substring(descriptionStart, descriptionEnd),
                userInput.substring(fromStart, fromEnd),
                userInput.substring(toStart));
    }
}

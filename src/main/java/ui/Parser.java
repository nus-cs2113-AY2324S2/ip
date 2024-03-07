package ui;

import exception.EkudException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

public class Parser {

    public static Todo parseTodo(String userInput) throws EkudException {
        int dividerPosition = userInput.indexOf(" ");
        int descriptionStart = dividerPosition + 1;
        return new Todo(userInput.substring(descriptionStart));
    }
}

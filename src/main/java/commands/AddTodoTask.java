package commands;

import Exceptions.ThawException;
import FileManagerPackage.Storage;
import Tasks.*;

import java.util.ArrayList;

/**
 * The AddTodoTask class represents a command to add a todo task to the task list.
 * It extends the Command class and requires the list of tasks and the user's input.
 */
public class AddTodoTask extends Command {

    private final int LEN_OF_TODO = 5;

    /**
     * The name of the command class (used for error messages).
     */
    public final String CLASS_NAME = "todo";

    /**
     * Constructs a new {@code AddTodoTask} command with the specified list of tasks and user's input.
     *
     * @param taskList   The list of tasks to operate on.
     * @param usersInput The user's input for the command.
     * @throws ThawException Thrown if the user's input is empty.
     */
    public AddTodoTask(ArrayList<Task> taskList, String usersInput) throws ThawException {
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + CLASS_NAME);
        } else if (!commandWithoutDescription(usersInput)) {
            String taskDesc = usersInput.substring(LEN_OF_TODO);
            taskList.add(new Todo(taskDesc));
            ui.printAcknowledgementMessage(taskList);
            Storage.saveData(taskList);
        }
    }
}

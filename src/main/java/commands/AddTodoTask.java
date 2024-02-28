package commands;

import Exceptions.ThawException;
import FileManagerPackage.Storage;
import Tasks.*;

import java.util.ArrayList;

public class AddTodoTask extends Command {
    /**
     * The name of the command class (used for error messages).
     */
    public final String className = "todo";
    public AddTodoTask(ArrayList<Task> taskList, String usersInput) throws ThawException {
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + className);
        } else if (!commandWithoutDescription(usersInput)) {
            String taskDesc = usersInput.substring(5);
            taskList.add(new Todo(taskDesc));
            ui.printAcknowledgementMessage(taskList);
            Storage.saveData(taskList);
        }
    }
}

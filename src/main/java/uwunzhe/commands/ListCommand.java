package uwunzhe.commands;

import uwunzhe.util.TaskList;
import uwunzhe.util.Ui;
import uwunzhe.handler.Storage;
import uwunzhe.tasks.Task;
import uwunzhe.exceptions.UwunzheException;
import uwunzhe.exceptions.ExceptionMessages;

public class ListCommand extends Command {
    /**
     * Constructor for ListCommand.
     * 
     * @param commandString The command from the user.
     * @param taskString The string appended to the command to be executed.
     */
    public ListCommand(String commandString, String taskString) {
        super(commandString, taskString);
    }

    /**
     * Prints the list of tasks.
     * 
     * @param taskList The list of tasks of type {@link TaskList}.
     * @param storage The storage handler of tyle {@link Storage}.
     * @throws UwunzheException If the command is invalid or if the list is empty.
     */
    public void execute(TaskList taskList, Storage storage) 
            throws UwunzheException {
        if (this.taskString.length() > 0) {
            throw new UwunzheException(ExceptionMessages.UNEXPECTED_EXTRA_COMMAND);
        }
                
        int size = taskList.getSize();

        if (size == 0) {
            throw new UwunzheException(ExceptionMessages.EMPTY_LIST);
        }

        Ui.println("Yay! List!");
        Task task;
        for (int i = 0; i < size; i++) {
            task = taskList.getTask(i);
            Ui.print(i + 1 + ".");
            Ui.printlnTask(task);
        }
    }
}

package uwunzhe.commands;

import uwunzhe.util.TaskList;
import uwunzhe.handler.Storage;
import uwunzhe.tasks.Task;

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
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     */
    public void execute(TaskList taskList, Storage storage) {
        int size = taskList.getSize();

        if (size == 0) {
            System.out.println("You KAIBAI-ing");
            return;
        }

        System.out.println("Yay! List!");
        Task task;
        for (int i = 0; i < size; i++) {
            task = taskList.getTask(i);
            System.out.print(i + 1 + ".");
            System.out.println(task);
        }
    }
}

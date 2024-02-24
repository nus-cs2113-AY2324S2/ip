package uwunzhe.commands;

import uwunzhe.util.TaskList;
import uwunzhe.util.Ui;
import uwunzhe.handler.Storage;
import uwunzhe.exceptions.UwunzheException;

public class FindCommand extends Command {
    /**
     * Constructor for FindCommand.
     * 
     * @param commandString The command from the user.
     * @param taskString The string appended to the command to be executed.
     */
    public FindCommand(String commandString, String taskString) {
        super(commandString, taskString);
    }

    /**
     * Finds a task from the list.
     * 
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @throws UwunzheException If the task does not exist.
     */
    public void execute(TaskList taskList, Storage storage)
            throws UwunzheException {
        if (this.taskString.length() == 0) {
            throw new UwunzheException("No Value :(");
        }

        int size = taskList.getSize();
        int counter = 0;
        String taskDescription;

        for (int i = 0; i < size; i++) {
            taskDescription = taskList.getTask(i).getName();
            if (taskDescription.contains(this.taskString)) {
                counter++;
                String counterString = Integer.toString(counter) + ".";
                Ui.printlnTask(taskList, i, counterString);
            }
        }

        if (counter == 0) {
            Ui.println("Dun hv...");
        }
    }
}

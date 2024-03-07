package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand() {

    }

    /**
     * Executes command of deleting task to task list based on integer index.
     *
     * @param taskList List of tasks.
     * @param storage Storage handler that writes task lists to file.
     */
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("Noted. I've removed this task:");
        try {
            int index = Integer.parseInt(taskDescription);
            Task deletedTask = taskList.getTask(index);
            String deletedTaskType = deletedTask.getTaskType();
            String deletedDescription = deletedTask.getTaskDescription();
            Command lastCommand = getLastCommand();
            lastCommand.setFirstWord("delete" + deletedTaskType);
            lastCommand.setTaskDescription(deletedDescription);
            taskList.deleteTask(index);
        } catch (NumberFormatException e) {
            System.out.println("Sorry, please delete via an integer index");
        }
        try {
            storage.saveToFile("data/list.txt", taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

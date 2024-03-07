package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import tasks.TaskList;

import java.io.IOException;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    public DeleteCommand() {
    }

    /**
     * Returns index from the line that comes after a keyword. The index
     * represents the index of the task based on the task list.
     *
     * @param line Inputted line prompt by the user.
     * @param keyword Word that is seperated from the index in line.
     * @return Index that represents task index.
     */
    public int parseIndex(String line, String keyword) {
        int len = keyword.length();
        return Integer.parseInt(line.substring(len + 1));
    }

    /**
     * Executes command of deleting task to task list based on integer index.
     * Updates the file by removing the task from the file.
     *
     * @param taskList List of tasks.
     * @param storage Storage handler that writes task lists to file.
     */
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("Noted. I've removed this task:");
        taskList.deleteTask(parseIndex(line, firstWord));
        try {
            storage.saveToFile("data/list.txt", taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
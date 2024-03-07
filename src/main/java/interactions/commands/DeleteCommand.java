package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import tasks.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand() {
    }
    public int parseIndex(String line, String keyword) {
        int len = keyword.length();
        return Integer.parseInt(line.substring(len + 1));
    }

    /**
     * Executes command of deleting task to task list based on integer index.
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
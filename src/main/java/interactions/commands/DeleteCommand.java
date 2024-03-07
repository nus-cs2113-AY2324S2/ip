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

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.deleteTask(parseIndex(line, firstWord));
        try {
            storage.saveToFile("data/list.txt", taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

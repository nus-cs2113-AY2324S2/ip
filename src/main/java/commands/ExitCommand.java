package commands;

import exceptions.KikuException;
import java.io.IOException;
import taskList.TaskList;
import ui.Ui;
import storage.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KikuException {
        try {
            storage.saveTasks(tasks.getTasks());
            ui.exitMessage();
        } catch (IOException e) {
            ui.errorMessage("OOPS! An error occurred while saving tasks: " + e.getMessage());
        }
    }
}

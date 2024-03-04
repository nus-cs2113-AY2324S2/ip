package commands;

import exceptions.KikuException;
import taskList.TaskList;
import ui.Ui;
import storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KikuException;
}

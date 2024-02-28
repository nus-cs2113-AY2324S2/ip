package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.task.Task;
import Tony.utility.Parser;
import Tony.utility.Ui;

import java.util.ArrayList;

public interface Command {
    void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver);
}

package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.TonyException;
import Tony.task.Task;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public interface Command {
    void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) throws IOException, TonyException;

    boolean isExit();
}

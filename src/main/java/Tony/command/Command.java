package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.task.Task;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represent {@code Command} interface that runs execute method and isExit method.
 */
public interface Command {
    void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) throws IOException;

    boolean isExit();
}

package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.task.Task;
import Tony.utility.Ui;

import java.util.ArrayList;

public class ByeCommand implements Command {

    /**
     * Executes the <code>printByeMessage</code> command.
     * @param tasks is the current list of <code>tasks</code> to save from the file.
     * @param ui is the user interface of that prints texts on program.
     * @param fileSaver is object used to save data into the file.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) {
        ui.printByeMessage();
    }

    /**
     * Returns <code>true</code> if command entered <code>bye</code>
     * @return <code>true</code> and exit program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

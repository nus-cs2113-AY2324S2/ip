package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.task.Task;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class ListCommand implements Command {

    /**
     * Executes the <code>printTaskList</code> method to print tasks onto the screen.
     * @param tasks is the current list of <code>tasks</code> to save to the file.
     * @param ui is the user interface of that prints texts on program.
     * @param fileSaver is object used to save data into the file.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) {
        ui.printTaskList();
    }

    /**
     * Returns <code>false</code> if command not entered <code>bye</code>
     * @return <code>false</code> and does not exit program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

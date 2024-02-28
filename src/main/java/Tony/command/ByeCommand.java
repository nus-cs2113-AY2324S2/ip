package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.task.Task;
import Tony.utility.Ui;

import java.util.ArrayList;

public class ByeCommand implements Command {

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) {
        ui.printByeMessage();
    }
}

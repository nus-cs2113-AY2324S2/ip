package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String message = "Here are the tasks in your list:";
        message += (taskList.isEmpty()) ? "" : System.lineSeparator();
        for (int i = 0; i < taskList.size(); i++) {
            message += ("\t " + (i + 1) + "." + taskList.get(i).toString()
                    + ((i == taskList.size() - 1) ? "" : System.lineSeparator()));
        }
        ui.printMessage(message);
    }

    @Override
    public void splitWords() {
        //do nothing
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

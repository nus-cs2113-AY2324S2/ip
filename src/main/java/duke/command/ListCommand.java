package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        message.append((taskList.isEmpty()) ? "" : System.lineSeparator());
        for (int i = 0; i < taskList.size(); i++) {
            message.append("\t ").append(i + 1).append(".").append(taskList.get(i).toString())
                    .append((i == taskList.size() - 1) ? "" : System.lineSeparator());
        }
        ui.printMessage(message.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

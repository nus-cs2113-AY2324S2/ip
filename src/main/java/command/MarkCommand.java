package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.TaskList;
import ui.Ui;

public class MarkCommand implements Command {
    private final int index;
    public MarkCommand(String description) throws JingHaoExceptions {
        try {
            index = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            String invalidIndex = "Invalid index";
            throw new JingHaoExceptions(invalidIndex);
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions {
        if(index < 0 || index > taskList.size()) {
            String invalidIndex = "Selected index is not within the size. PLease input a valid index.";
            throw new JingHaoExceptions(invalidIndex);
        }
        taskList.get(index).check();
        ui.printMessage("Nice! I've marked this task as done: \n" + taskList.get(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

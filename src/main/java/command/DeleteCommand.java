package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.TaskList;
import ui.Ui;

public class DeleteCommand implements Command {
    private static int index;

    public DeleteCommand(String description) throws JingHaoExceptions{
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
        ui.printWithoutDivider("Noted. I have removed this task:\n" +
                taskList.get(index));
        taskList.remove(index);
        ui.printTotalTask(taskList.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

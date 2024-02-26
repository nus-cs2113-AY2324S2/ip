package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.TaskList;
import ui.Ui;

/**
 * Represents the command to delete a specific task from the list of tasks.
 */
public class DeleteCommand implements Command {
    private static int index;

    /**
     * Construct a DeleteCommand with index specified by the user, relative to the index in the list.
     *
     * @param description The index provided by the user.
     * @throws JingHaoExceptions If the index provided by the user is not a number.
     */
    public DeleteCommand(String description) throws JingHaoExceptions{
        try {
            index = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            String invalidIndex = "Invalid index";
            throw new JingHaoExceptions(invalidIndex);
        }
    }

    /**
     * Executes the command by removing the task from the list of tasks.
     * Displays task to be deleted and the corresponding status on the screen after executing the command.
     *
     * @param taskList The list of task in the JingHao chatbot.
     * @param ui The user interface of the JingHao chatbot.
     * @param storage The file storage of the JingHao chatbot.
     * @throws JingHaoExceptions If the index is not within the appropriate range of the list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions {
        if(index < 0 || index >= taskList.size()) {
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

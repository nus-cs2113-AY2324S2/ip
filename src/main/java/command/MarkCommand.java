package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.TaskList;
import ui.Ui;

/**
 * Represents the command to mark a specific task in the list based on the user's input
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Constructs a MarkCommand with index specified by the user, relative to the index in the list.
     *
     * @param description The index provided by the user.
     * @throws JingHaoExceptions If the index provided by the user is not a number.
     */
    public MarkCommand(String description) throws JingHaoExceptions {
        try {
            index = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            String invalidIndex = "Invalid index";
            throw new JingHaoExceptions(invalidIndex);
        }
    }

    /**
     * Executes the command by marking the task as done.
     * Displays the task and the task status on the screen after executing the command.
     *
     * @param taskList The list of task in the JingHao chatbot.
     * @param ui The user interface of the JingHao chatbot.
     * @param storage The file storage of the JingHao chatbot.
     * @throws JingHaoExceptions If the index is not within the appropriate range of the list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions {
        if(index < 0 || index > taskList.size()) {
            String invalidIndex = "Selected index is not within the size. PLease input a valid index.";
            throw new JingHaoExceptions(invalidIndex);
        }
        taskList.get(index).check();
        ui.printMessage("Nice! I've marked this task as done: \n" + taskList.get(index));
    }

    /**
     * Determines whether the command is an exit command.
     *
     * @return Returns false since this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

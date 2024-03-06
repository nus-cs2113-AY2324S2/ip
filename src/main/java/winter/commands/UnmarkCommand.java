package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;

/**
 * Represents the command given by the user to mark a task as incomplete
 */
public class UnmarkCommand extends Command {
    private int unmarkTaskNum;
    public UnmarkCommand(int unmarkTaskNum) {
        this.unmarkTaskNum = unmarkTaskNum;
    }
    public static final String COMMAND_WORD = "unmark";
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.unmarkTask(unmarkTaskNum);
    }
}

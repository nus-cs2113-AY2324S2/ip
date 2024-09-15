package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;

/**
 * Represents the command given by the user to mark a task as completed
 */
public class MarkCommand extends Command {
    private int markTaskNum;
    public static final String COMMAND_WORD = "mark";
    public MarkCommand(int markTaskNum) {
        this.markTaskNum = markTaskNum;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.markTask(markTaskNum);
    }
}

package bob.command;

import bob.utils.TaskManager;

public class FindCommand extends Command {
    private final String keyword;
    public FindCommand(TaskManager taskManager, String keyword) {
        super(taskManager);
        this.keyword = keyword;
    }

    @Override
    public String executeCommand() {
        return taskManager.findTask(keyword);
    }
}

package joe.command;

import joe.JoeException;
import joe.task.TaskManager;
import joe.util.Printer;

public class FindCommand implements Command {
    protected String keyword;

    public FindCommand (String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void executeCommand(TaskManager taskManager) {
        try {
            taskManager.listTasksWithKeyword(keyword);
        } catch (JoeException e) {
            Printer.printFindError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

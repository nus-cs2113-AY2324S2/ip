package joe.command;

import joe.JoeException;
import joe.task.TaskManager;
import joe.util.Printer;

/**
 * Command indicating a find command, implements the Command interface
 */
public class FindCommand implements Command {
    protected String keyword;

    public FindCommand (String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command according to the keyword
     *
     * @param taskManager the TaskManager instance used by Joe
     */
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

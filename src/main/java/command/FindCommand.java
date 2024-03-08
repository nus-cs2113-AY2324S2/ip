package command;

import task.TaskList;
import ui.ResponseManager;

/**
 * The FindCommand class represents a command to find tasks in the task list based on a keyword or time. 
 */
public class FindCommand implements Command {
    private static final String FIND_TIME = "/t";
    private static final String FIND_WORD = "/w";
    private final String keyword;
    private final String type;

    private FindCommand(String keyword, String type) {
        this.keyword = keyword;
        this.type = type;
    }

    public FindCommand(String[] message) {
        this(message[1], message[0]);
    }

    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * Finds tasks in the task list based on the keyword and prints the tasks to the user.
     *
     * @param tasks the task list.
     */
    public void run(TaskList tasks) {
        if (tasks.getSize() == 0) {
            ResponseManager.sendEmptyListMsg();
            return;
        }
        switch (type) {
        case FIND_TIME:
            ResponseManager.printActionOnTasks(CommandType.FIND, tasks.findTime(keyword));
            break;

        case FIND_WORD:
            ResponseManager.printActionOnTasks(CommandType.FIND, tasks.findTask(keyword));
            break;

        default:
            break;
        }
    }
}

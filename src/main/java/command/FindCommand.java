package command;

import task.TaskList;
import tool.ResponseManager;

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
     * Finds tasks in the task list based on the keyword and prints the tasks to the user
     * @param tasks the task list
     */
    public void run(TaskList tasks) {
        switch (type) {
        case FIND_TIME:
            ResponseManager.printActionOnTasks("find", tasks.findTime(keyword));
            break;

        case FIND_WORD:
            ResponseManager.printActionOnTasks("find", tasks.findTask(keyword));
            break;

        default:
            break;
        }
    }
}

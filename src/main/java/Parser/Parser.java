package Parser;

import TaskList.TaskList;

/**
 * A class that deals with making sense of the user command.
 */
public class Parser {
    public void taskManager() {
        Manager.taskListManager(TaskList.tasks);
    }
}

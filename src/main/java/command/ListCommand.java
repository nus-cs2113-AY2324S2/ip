package command;

import exception.AdamException;
import task.TaskList;
import ui.Message;

/**
 * The ListCommand class represents a command to list all the tasks in the task list.
 */
public class ListCommand implements Command {
    
    /**
     * Lists all the tasks in the task list.
     *
     * @param tasks The list of tasks.
     * @return False because the program should continue running.
     * @throws AdamException If the task list is empty.
     */
    @Override
    public boolean execute(TaskList tasks) throws AdamException {
        if (tasks.isEmpty()) {
            throw new AdamException(Message.EMPTY_LIST_ERROR_MESSAGE);
        } else {
            System.out.println(Message.LIST_MESSAGE_FRONT);
            tasks.displayAll();
            System.out.println(Message.LIST_MESSAGE_END);
        }
        return false;
    }
}

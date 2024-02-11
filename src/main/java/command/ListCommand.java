package command;

import exception.AdamException;
import task.TaskList;
import ui.Message;

public class ListCommand implements Command {
    @Override
    public boolean execute(TaskList tasks) throws AdamException {
        if (tasks.isEmpty()) {
            throw new AdamException(Message.LIST_ERROR_MESSAGE);
        } else {
            System.out.println(Message.LIST_MESSAGE_FRONT);
            tasks.displayAll();
            System.out.println(Message.LIST_MESSAGE_END);
        }
        return false;
    }
}

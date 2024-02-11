package command;

import task.TaskList;
import ui.Message;

public class ListCommand implements Command {
    @Override
    public boolean execute(TaskList tasks) {
        if (!tasks.isEmpty()) {
            System.out.println(Message.LIST_MESSAGE_FRONT);
            tasks.displayAll();
            System.out.println(Message.LIST_MESSAGE_END);
        } else {
            System.out.println(Message.LIST_ERROR_MESSAGE);
        }
        return false;
    }
}

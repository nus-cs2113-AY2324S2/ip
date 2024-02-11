package command;

import task.TaskList;
import ui.Message;

public class ExitCommand implements Command {
    @Override
    public boolean execute(TaskList tasks) {
        System.out.print(Message.EXIT_MESSAGE);
        return true;
    }
}

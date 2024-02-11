package command;

import task.TaskList;
import ui.Message;

public class HelpCommand implements Command {
    @Override
    public boolean execute(TaskList tasks) {
        System.out.println(Message.HELP_MESSAGE);
        return false;
    }
}

package command;

import task.TaskList;
import ui.Message;

/**
 * The HelpCommand class represents a command to display the help message.
 */
public class HelpCommand implements Command {

    /**
     * Executes the command to display the help message.
     *
     * @param tasks The list of tasks.
     * @return False because the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks) {
        System.out.println(Message.HELP_MESSAGE);
        return false;
    }
}

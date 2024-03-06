package command;

import task.TaskList;
import ui.Message;

/**
 * The ExitCommand class represents a command to exit the program.
 */
public class ExitCommand implements Command {

    /**
     * Executes the command to exit the program.
     *
     * @param tasks The list of tasks.
     * @return True because the program should terminate.
     */
    @Override
    public boolean execute(TaskList tasks) {
        System.out.print(Message.EXIT_MESSAGE);
        return true;
    }
}

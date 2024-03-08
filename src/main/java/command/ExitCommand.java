package command;

import task.TaskList;
import ui.ResponseManager;

/**
 * The ExitCommand class represents a command to exit the program.
 */
public class ExitCommand implements Command {
    /**
     * {@inheritDoc}
     * 
     * Exits the program and says goodbye to the user.
     *
     * @param tasks the task list.
     */
    @Override
    public void run(TaskList tasks) {
        ResponseManager.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

package command;

import exceptions.MarioErrorExecutingCommand;
import templates.TaskList;

/**
 * Abstract base class and its concrete subclasses representing commands that can be executed within the Mario application.
 * BaseCommand defines the common structure for commands, including execution logic and a flag indicating if the command should terminate the application.
 * Subclasses like ByeCommand, DeleteCommand, UnmarkCommand, etc.,implement specific functionalities tailored to handling user commands in the chat application. Each command class encapsulates the logic required to execute a particular command, including adding tasks, marking tasks as done or not done, finding tasks, and handling task deadlines and events. The design pattern allows for easy extension and management of different types of commands within the system.
 */

public class ListCommand extends BaseCommand {

    public ListCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception{
        try {
            return (taskList.toString());
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}

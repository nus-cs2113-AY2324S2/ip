package joe.command;

import joe.JoeException;
import joe.task.TaskManager;
import joe.task.TaskType;
import joe.util.Printer;

/**
 * Command indicating a new task command, implements the Command interface.
 * New tasks include todo, deadline and event
 */
public class NewTaskCommand implements Command {
    protected String arguments;
    protected TaskType type;

    public NewTaskCommand(String message, TaskType type) {
        this.type = type;
        this.arguments = message;
    }

    /**
     * Executes the add command according to the instance's TaskType
     *
     * @param taskManager the TaskManager instance used by Joe
     */
    @Override
    public void executeCommand(TaskManager taskManager) {
        try {
            taskManager.addTask(type, arguments);
        } catch (JoeException e) {
            switch (type) {
            case TODO:
                Printer.printToDoEmptyError();
                break;
            case DEADLINE:
                Printer.printDeadlineInputError();
                break;
            case EVENT:
                Printer.printEventInputError();
                break;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

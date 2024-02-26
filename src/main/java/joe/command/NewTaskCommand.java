package joe.command;

import joe.JoeException;
import joe.task.TaskManager;
import joe.task.TaskType;
import joe.util.Printer;

public class NewTaskCommand implements Command {
    protected String arguments;
    protected TaskType type;

    public NewTaskCommand(String message, TaskType type) {
        this.type = type;
        this.arguments = message;
    }

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

package joe.command;

import joe.JoeException;
import joe.task.TaskManager;
import joe.util.Printer;

public class ToggleMarkCommand implements Command {
    protected int taskNumber;
    protected boolean isMark;

    public ToggleMarkCommand(int taskNumber, boolean isMark) {
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }

    @Override
    public void executeCommand(TaskManager taskManager) {
        try {
            taskManager.toggleTaskMarkedStatus(taskNumber, isMark);
        } catch (NumberFormatException | JoeException e) {
            Printer.printInvalidMarkError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

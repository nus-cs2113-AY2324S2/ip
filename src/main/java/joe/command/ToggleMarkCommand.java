package joe.command;

import joe.JoeException;
import joe.task.TaskManager;
import joe.util.Printer;

/**
 * Command indicating a mark or unmark command, implements the Command interface
 */
public class ToggleMarkCommand implements Command {
    protected int taskNumber;
    protected boolean isMark;


    public ToggleMarkCommand(int taskNumber, boolean isMark) {
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }

    /**
     * Executes the mark or unmark command according to isMark of the instance.
     * Executes mark if isMark is true, unmark otherwise
     *
     * @param taskManager the TaskManager instance used by Joe
     */
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

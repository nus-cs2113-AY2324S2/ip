package kobot.command;

import kobot.storage.Storage;
import kobot.task.TaskList;
import kobot.ui.Ui;

public class MarkUnmarkCommand extends ModifyTaskCommand {
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private Boolean isMark;
    private String command;
            
    public MarkUnmarkCommand(Boolean isMark, TaskList taskList, String index) {
        super(taskList);
        this.isMark = isMark;
        this.command = isMark ? MARK_COMMAND : UNMARK_COMMAND;
        
        try {
            setIndex(index);
        } catch (NumberFormatException exception) {
            if (this.isMark) {
                Ui.printMarkCommandUsage();
            } else {
                Ui.printUnmarkCommandUsage();
            }
        }
    }
    public void execute() {
        if (!canExecute) {
            return;
        }
        
        try {
            if (this.isMark) {
                taskList.markTask(this.index);
            } else {
                taskList.unmarkTask(this.index);
            }
            Storage.updateFile(taskList);
        } catch (IndexOutOfBoundsException exception) {
            Ui.printIndexOutOfBoundsMessage(this.command);
        }
    }
}

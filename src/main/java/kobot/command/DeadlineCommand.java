package kobot.command;

import kobot.storage.Storage;
import kobot.task.TaskList;
import kobot.ui.Ui;

public class DeadlineCommand extends Command {
    private static final String DEADLINE_BY_DELIMITER = "/by";
    private String description;
    private String by;

    public DeadlineCommand(TaskList taskList, String arguments) {
        super(taskList);
        parseDeadlineArguments(arguments);
    }

    /**
     * Parses arguments (description, by) for Deadline.
     */
    public void parseDeadlineArguments(String arguments) {
        try {
            String[] argList = arguments.split(DEADLINE_BY_DELIMITER, MAX_COMMAND_SPLIT_LIMIT);
            this.description = argList[0].trim();
            this.by = argList[1].trim();
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            Ui.printMissingArgumentErrorMessage();
            Ui.printDeadlineCommandUsage();
            return;
        }

        if (this.description.isEmpty() || this.by.isEmpty()) {
            Ui.printEmptyArgumentErrorMessage();
            Ui.printDeadlineCommandUsage();
            return;
        }

        this.canExecute = true;
    }
    
    public void execute() {
        if (!canExecute) {
            return;
        }
        
        taskList.addDeadline(this.description, this.by);
        Storage.updateFile(taskList);
    }
}

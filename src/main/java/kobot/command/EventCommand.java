package kobot.command;

import kobot.storage.Storage;
import kobot.task.TaskList;
import kobot.ui.Ui;

public class EventCommand extends Command {
    private static final String EVENT_FROM_DELIMITER = "/from";
    private static final String EVENT_TO_DELIMITER = "/to";
    private String description;
    private String from;
    private String to;

    public EventCommand(TaskList taskList, String arguments) {
        super(taskList);
        parseEventArguments(arguments);
    }

    /**
     * Parses arguments (description, from, to) for Event.
     */
    public void parseEventArguments(String arguments) {
        try {
            String[] argList = arguments.split(EVENT_FROM_DELIMITER, MAX_COMMAND_SPLIT_LIMIT);
            this.description = argList[0].trim();

            String[] dateList = argList[1].split(EVENT_TO_DELIMITER, MAX_COMMAND_SPLIT_LIMIT);
            this.from = dateList[0].trim();
            this.to = dateList[1].trim();
        } catch (NullPointerException| IndexOutOfBoundsException exception) {
            Ui.printMissingArgumentErrorMessage();
            Ui.printEventCommandUsage();
            return;
        }
        
        if (this.description.isEmpty() || this.from.isEmpty() || this.to.isEmpty()) {
            Ui.printEmptyArgumentErrorMessage();
            Ui.printEventCommandUsage();
            return;
        }

        this.canExecute = true;
    }

    public void execute() {
        if (!canExecute) {
            return;
        }

        taskList.addEvent(this.description, this.from, this.to);
        Storage.updateFile(taskList);
    }
}

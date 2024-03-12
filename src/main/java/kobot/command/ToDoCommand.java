package kobot.command;

import kobot.storage.Storage;
import kobot.task.TaskList;
import kobot.ui.Ui;

public class ToDoCommand extends Command {
    private String description;
    
    public ToDoCommand(TaskList taskList, String arguments) {
        super(taskList);
        parseToDoArguments(arguments);
    }

    /**
     * Parses arguments (description) for ToDo.
     */
    public void parseToDoArguments(String arguments) {
        try {
            this.description = arguments.trim();
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            Ui.printMissingArgumentErrorMessage();
            Ui.printToDoCommandUsage();
            return;
        }

        if (this.description.isEmpty()) {
            Ui.printEmptyArgumentErrorMessage();
            Ui.printToDoCommandUsage();
            return;
        }
        
        this.canExecute = true;
    }
    public void execute() {
        if (!canExecute) {
            return;
        }
        
        taskList.addToDo(this.description);
        Storage.updateFile(taskList);
    }
}

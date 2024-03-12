package kobot.command;

import kobot.task.TaskList;
import kobot.task.Task;
import kobot.ui.Ui;

public class FindCommand extends Command {
    private String keyword;
    
    public FindCommand(TaskList tasklist, String keyword) {
        super(tasklist);
        parseArguments(keyword);
    }
    
    public void parseArguments(String keyword) {
        try {
            this.keyword = keyword.trim();
        } catch (NullPointerException exception) {
            Ui.printMissingArgumentErrorMessage();
            Ui.printFindCommandUsage();
            return;
        }

        if (this.keyword.isEmpty()) {
            Ui.printEmptyArgumentErrorMessage();
            Ui.printFindCommandUsage();
            return;
        }

        this.canExecute = true;
    }
    public void execute() {
        if (!canExecute) {
            return;
        }
        
        taskList.findTasks(keyword);
    }
}

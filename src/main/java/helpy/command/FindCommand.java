package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Task;
import helpy.task.TaskList;

public class FindCommand extends Command{
    public FindCommand(String body) {
        super(body);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (commandBody.isEmpty()) {
            ui.showIllegalDescriptionErr();
            return;
        }
        TaskList searchResults = new TaskList();
        for (Task task : taskList.getTasks()) {
            if (task.getTaskName().contains(commandBody)) {
                searchResults.addTask(task);
            }
        }
        ui.showFindResult(searchResults, commandBody);
    }
}

package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.Task;
import helpy.task.TaskList;

/**
 * Represents a command to find tasks based on a keyword.
 */
public class FindCommand extends Command{
    /**
     * Constructs a new FindCommand object with the given command body.
     *
     * @param body The body or content of the command.
     */
    public FindCommand(String body) {
        super(body);
    }

    /**
     * Executes the FindCommand by searching for tasks that match the given keyword.
     *
     * @param taskList The task list to search.
     * @param ui       The user interface for displaying output and messages.
     * @param storage  The storage system for managing task data.
     */
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

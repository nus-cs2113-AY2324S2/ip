package commands;

import taskList.TaskList;
import tasks.Task;
import ui.Ui;
import storage.Storage;

import java.util.ArrayList;

/**
 * A command to find and display tasks that contain a specific keyword.
 * This command searches through the task list for tasks matching the given keyword
 * and displays them to the user.
 */
public class FindTaskCommand extends Command {
    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching for tasks that contain the keyword in their description.
     * Matching tasks are then displayed to the user through the UI component.
     *
     * @param tasks The TaskList currently managed by KikuBot, which can be modified by the command.
     * @param ui The Ui component of KikuBot used for interacting with the user.
     * @param storage The Storage component of KikuBot, responsible for tracking task list changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

}

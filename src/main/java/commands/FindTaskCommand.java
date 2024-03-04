package commands;

import taskList.TaskList;
import tasks.Task;
import ui.Ui;
import storage.Storage;

import java.util.ArrayList;

public class FindTaskCommand extends Command {
    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

}

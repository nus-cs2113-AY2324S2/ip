package schmidt.command;

import schmidt.exception.SchmidtException;
import schmidt.storage.Storage;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SchmidtException {
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.printMatchingTasks(matchingTasks);
    }
}

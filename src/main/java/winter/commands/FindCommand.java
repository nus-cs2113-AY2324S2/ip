package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Deadline;
import winter.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand (String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task task : tasks.getTaskArrayList()) {
            if (task.getTaskName().contains(keyword)) {
                foundList.add(task);
            }
        }
        //tasks.increaseLastTaskIndex();
        ui.showTaskFound(foundList);

    }
}

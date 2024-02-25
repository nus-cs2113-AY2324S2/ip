package quill.command;

import quill.storage.Save;
import quill.task.Task;
import quill.task.TaskList;
import quill.ui.TextUi;

import java.util.ArrayList;

public class FindCommand extends Command{
    public FindCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Save save) {
        ArrayList<Task> matchingTask = new ArrayList<Task>();
        for (int i = 0; i < TaskList.getTotalTasks(); i++) {
            if (tasks.getTask(i).getDescription().contains(parameter)) {
                matchingTask.add(tasks.getTask(i));
            }
        }
        TextUi.showFindList(matchingTask);
    }
}

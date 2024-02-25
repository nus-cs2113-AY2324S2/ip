package quill.command;

import quill.task.Task;
import quill.task.TaskList;
import quill.ui.TextUi;

import java.util.ArrayList;

/**
 * The FindCommand Class represents a command for finding a task from the task list.
 * It extends the Command class and includes specific behavior for finding different tasks.
 */
public class FindCommand extends Command{

    /**
     * Constructs the FindCommand object with the specified commandWord and parameter.
     *
     * @param commandWord The command word.
     * @param parameter The user input excluding the command word.
     */
    public FindCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    /**
     * Executes the FindCommand to find tasks.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        ArrayList<Task> matchingTask = new ArrayList<>();
        for (int i = 0; i < TaskList.getTotalTasks(); i++) {
            if (tasks.getTask(i).getDescription().contains(parameter)) {
                matchingTask.add(tasks.getTask(i));
            }
        }
        TextUi.showFindList(matchingTask);
    }

}

package interactions.commands;

import interactions.Storage;
import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand() {

    }
    public void findKeyword(String keyword, TaskList taskList) {
        TaskList matchedTasks = new TaskList();
        ArrayList<Task> list = taskList.getList();
        for (Task task : list) {
            String taskDescription = task.getTaskDescription();
            if (taskDescription.contains(keyword)) {
                matchedTasks.addToList(task);
            }
        }
        matchedTasks.printList();
    }
    @Override
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("Here are the matching tasks in your list:");
        findKeyword(taskDescription, taskList);
    }
}

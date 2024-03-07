package interactions.commands;

import interactions.Storage;
import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;

/** Finds for tasks in a task list whose description contains a keyword. */
public class FindCommand extends Command {
    public FindCommand() {

    }

    /**
     * Searches for and prints out all the tasks containing a certain keyword in the task list.
     * Prints the compiled list of matched tasks.
     *
     * @param keyword The keyword to be searched for in the task list.
     * @param taskList List of tasks containing ToDo's, Events and Deadlines.
     */
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

    /**
     * Finds for occurences of tasks in a task list, given the keyword.
     *
     * @param taskList List of tasks containing ToDo's, Events and Deadlines.
     * @param storage Storage handler that saves to file.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("Here are the matching tasks in your list:");
        findKeyword(taskDescription, taskList);
    }
}
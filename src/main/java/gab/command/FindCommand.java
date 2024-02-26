package gab.command;

import gab.task.Task;
import gab.task.TaskList;
import gab.ui.Ui;

/**
 * Command to look for existence of keywords in task within taskList
 */
public class FindCommand extends Command {
    public String taskToFind;

    /**
     * Initialise keyword to find
     *
     * @param taskToFind keyword to find in tasks
     */
    public FindCommand(String taskToFind) {
        this.taskToFind = taskToFind;
    }

    /**
     * If there is a match, prints out task list that matches the keywords
     * Else, tell the user that no match is found
     *
     * @param taskList arraylist of task
     */
    @Override
    public void execute (TaskList taskList) {
        int taskCount = 0;
        boolean isFound;

        Ui.printLine();
        for (Task task : taskList.taskList) {
            String[] words = task.getDescription().split("\\s+");
            for (String word : words) {
                if (word.trim().equalsIgnoreCase(taskToFind.trim())) {
                    taskCount++;
                    isFound = true;
                    Ui.printFoundTask(task, taskCount, isFound);
                    break;
                }
            }
        }
        if (taskCount == 0) {
            Ui.printFoundTask(null, taskCount, false);
        }
        Ui.printLine();
    }
}

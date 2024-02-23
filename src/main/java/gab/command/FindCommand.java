package gab.command;

import gab.task.Task;
import gab.task.TaskList;
import gab.ui.Ui;

public class FindCommand extends Command {
    public String taskToFind;
    public FindCommand(String taskToFind) {
        this.taskToFind = taskToFind;
    }

    @Override
    public void execute (String taskName, TaskList taskList) {
        int taskCount = 0;
        boolean isFound;

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
    }
}

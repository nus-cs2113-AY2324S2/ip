package taskList;

import java.util.ArrayList;

import BobBot.parser.Parser;
import BobBot.tasks.Task;
import BobBot.ui.Ui;

public class TaskList {
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static int numberOfTasks = 0;

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static ArrayList<Task> getTaskList() {
        return allTasks;
    }

    public static void addTask(String line, boolean isLoad) {

        Task newTask = null;
        newTask = Parser.parseTaskCommands(line, newTask);
        
        if (newTask == null) {
            return;
        }

        allTasks.add(newTask);
        numberOfTasks += 1;

        if (!isLoad) {
            Ui.echoCommand(line, newTask);
        }
    }

    public enum TaskStatus {
        MARK, UNMARK, DELETE
    }

    public static void performTaskOperation(String line, TaskStatus status) {
        int taskNumber = Integer.parseInt(line.replaceAll("\\D", "").trim()) - 1;
        
        try {
            Task task = allTasks.get(taskNumber);   
            if (status == TaskStatus.MARK) {
                task.markAsDone();
                Ui.printTaskOperationMessage(task, "Marking this task as done:");
            } else if (status == TaskStatus.UNMARK) {
                task.markAsUndone();
                Ui.printTaskOperationMessage(task, "Unmarking this task:");
            } else if (status == TaskStatus.DELETE) {
                allTasks.remove(taskNumber);
                Ui.printTaskOperationMessage(task, "Deleting this task:");
                numberOfTasks -= 1;
            } else {
                System.out.println("Oh no!");
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printNonExistentTaskErrorMessage(taskNumber);
        }
    }

    public static ArrayList<Task> findTasksFromKeyword(String line) {
        ArrayList<Task> tasksOfInterest = new ArrayList<>();
        String keywordString = line.substring("find".length()).trim();

        for (int taskIndex = 0; taskIndex < numberOfTasks; taskIndex += 1) {
            Task task = allTasks.get(taskIndex);
            String taskDescription = task.getDescription();

            if (taskDescription.contains(keywordString)) {
                tasksOfInterest.add(task);
            }
        }

        return tasksOfInterest;
    }

}

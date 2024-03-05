package taskList;

import java.util.ArrayList;

import BobBot.BobBot;
import BobBot.exceptions.InvalidDeadlineException;
import BobBot.exceptions.InvalidEventException;
import BobBot.exceptions.InvalidTodoException;
import BobBot.tasks.Deadline;
import BobBot.tasks.Event;
import BobBot.tasks.Task;
import BobBot.tasks.Todo;

public class TaskList {
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static int numberOfTasks = 0;

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static ArrayList<Task> getTaskList() {
        return allTasks;
    }

    public static void displayList() {
        BobBot.drawLine(true);
        printTaskList();
        BobBot.drawLine(true);
    }

    private static void printTaskList() {
        
        System.out.printf("\tYour task list currently has %d items!\n\n", numberOfTasks);
        int taskNumberToDisplay;

        for (int taskIndex = 0; taskIndex < numberOfTasks; taskIndex += 1) {
            taskNumberToDisplay = taskIndex + 1;
            System.out.printf("\t%d. %s\n", taskNumberToDisplay, allTasks.get(taskIndex).toString());
        }
    }

    public static void addTask(String line, boolean isLoad) {

        Task newTask = null;

        try {
            if (line.startsWith("todo")) {
                newTask = new Todo(line);
            } else if (line.startsWith("deadline")) {
                newTask = new Deadline(line);
            } else if (line.startsWith("event")) {
                newTask = new Event(line);
            } else {
                BobBot.handleInvalidCommand();
                return;
            }
        } catch (InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
            BobBot.printCustomExceptionMessage(e);
            return;
        }

        allTasks.add(newTask);
        numberOfTasks += 1;

        if (!isLoad) {
            BobBot.echoCommand(line, newTask);
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
                BobBot.printTaskOperationMessage(task, "Marking this task as done:");
            } else if (status == TaskStatus.UNMARK) {
                task.markAsUndone();
                BobBot.printTaskOperationMessage(task, "Unmarking this task:");
            } else if (status == TaskStatus.DELETE) {
                allTasks.remove(taskNumber);
                BobBot.printTaskOperationMessage(task, "Deleting this task:");
                numberOfTasks -= 1;
            } else {
                System.out.println("Oh no!");
            }
        } catch (IndexOutOfBoundsException e) {
            BobBot.printNonExistentTaskErrorMessage(taskNumber);
        }
    }

}

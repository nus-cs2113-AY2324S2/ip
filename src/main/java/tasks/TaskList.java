package tasks;

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
}

package logic;

import java.util.ArrayList;
import exceptions.DeadlineNoByDateTimeException;
import exceptions.EventNoFromDateTimeException;
import exceptions.EventNoToDateTimeException;
import exceptions.EventToBeforeFromException;
import exceptions.InputIndexOutOfBoundsException;
import exceptions.InvalidInputException;
import exceptions.TaskNoNameException;
import tasks.Task;

public class TaskManager {

    protected int currIndex;
    protected ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
        this.currIndex = 0;
    }

    public void addTask(String input) throws Exception {
        String[] inputAsArray = input.split(" ");
        if (inputAsArray.length == 1) {
            throw new TaskNoNameException();
        }
        String taskType = inputAsArray[0];
        switch (taskType) {
        case "todo":
            tasks.add(Parser.processToDo(input));
            break;
        case "deadline":
            try {
                tasks.add(Parser.processDeadline(input));
            } catch (DeadlineNoByDateTimeException e) {
                throw new DeadlineNoByDateTimeException();
            }
            break;
        case "event":
            try {
                tasks.add(Parser.processEvent(input));
            } catch (EventNoFromDateTimeException e) {
                throw new EventNoFromDateTimeException();
            } catch (EventNoToDateTimeException e) {
                throw new EventNoToDateTimeException();
            } catch (EventToBeforeFromException e) {
                throw new EventToBeforeFromException();
            }
            break;
        default:
            throw new InvalidInputException();
        }
        printAndIncrementAfterAddTask();
    }

    private void printAndIncrementAfterAddTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(currIndex));
        System.out.println("Now you have " + (currIndex+1) + " tasks in the list");
        currIndex++;
    }
    
    public void markTask(int taskIndex, boolean isDone) throws Exception {
        if (taskIndex < 0 || taskIndex >= currIndex) {
            throw new InputIndexOutOfBoundsException();
        }
        Task targetTask = tasks.get(taskIndex);
        if (isDone) {
            targetTask.markDone();
            System.out.println("Nice! I've marked this Task as done:");
            System.out.println(targetTask);
        } else {
            targetTask.markNotDone();
            System.out.println("Ok, I've marked this Task as not done yet:");
            System.out.println(targetTask);
        }
    }

    public void listTasks() {
        for (int i = 0; i < currIndex; i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
    }

    public void deleteTask(int deleteIndex) throws Exception {
        if (deleteIndex < 0 || deleteIndex >= currIndex) {
            throw new InputIndexOutOfBoundsException();
        }
        System.out.println("Okay. I've removed this task:");
        System.out.println(tasks.get(deleteIndex));
        tasks.remove(deleteIndex);
        System.out.println("Now you have " + (currIndex - 1) + " tasks in the list");
        currIndex--;
    }
}

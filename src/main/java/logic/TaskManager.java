package logic;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import exceptions.DeadlineNoByDateTimeException;
import exceptions.EventNoFromDateTimeException;
import exceptions.EventNoToDateTimeException;
import exceptions.EventToBeforeFromException;
import exceptions.InputIndexOutOfBoundsException;
import exceptions.InvalidInputException;
import exceptions.TaskNoNameException;
import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

public class TaskManager {
    public static final int TODO_LENGTH= 5;
    public static final int DEADLINE_LENGTH = 9;
    public static final int EVENT_LENGTH = 6;
    public static final int BY_LENGTH = 4;
    public static final int FROM_LENGTH = 6;
    public static final int TO_LENGTH = 4;
    protected int currIndex;
    protected ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
        this.currIndex = 0;
    }

    public void addTask(String taskToAdd, boolean taskDoneStatus) throws Exception {
        String[] taskAsArray = taskToAdd.split(" ");
        if (taskAsArray.length == 1) {
            throw new TaskNoNameException();
        }
        String taskType = taskAsArray[0];
        switch (taskType) {
        case "todo":
            processToDo(taskToAdd, taskDoneStatus);
            break;
        case "deadline":
            try {
                processDeadline(taskToAdd, taskDoneStatus);
            } catch (DeadlineNoByDateTimeException e) {
                throw new DeadlineNoByDateTimeException();
            }
            break;
        case "event":
            try {
                processEvent(taskToAdd, taskDoneStatus);
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
    
    private void processToDo(String taskToAdd, boolean taskDoneStatus) {
        String taskName;
        taskName = taskToAdd.substring(TODO_LENGTH);
        tasks.add(new ToDo(taskName));
    }

    private void processDeadline(String taskToAdd, boolean taskDoneStatus) throws Exception {
        if (!(taskToAdd.contains("/by "))) {
            throw new DeadlineNoByDateTimeException();
        }
        String taskName;
        int firstBackslashIndex = taskToAdd.indexOf("/");
        if (firstBackslashIndex == DEADLINE_LENGTH) {
            // occurs when task is not given a name
            throw new TaskNoNameException();
        }
        taskName = taskToAdd.substring(DEADLINE_LENGTH, firstBackslashIndex - 1);
        int byWhenIndex = firstBackslashIndex + BY_LENGTH;
        String byWhen = taskToAdd.substring(byWhenIndex);
        tasks.add(new Deadline(taskName, byWhen));
    }

    private void processEvent(String taskToAdd, boolean taskDoneStatus) throws Exception {
        if (!(taskToAdd.contains("/from "))) {
            throw new EventNoFromDateTimeException();
        }
        if (!(taskToAdd.contains("/to "))) {
            throw new EventNoToDateTimeException();
        }
        if (taskToAdd.indexOf("/to ") < taskToAdd.indexOf("/from ")) {
            throw new EventToBeforeFromException();
        }
        String taskName;
        int firstBackslashIndex = taskToAdd.indexOf("/");
        if (firstBackslashIndex == EVENT_LENGTH) {
            // occurs when task is not given a name
            throw new TaskNoNameException();
        }
        taskName = taskToAdd.substring(EVENT_LENGTH, firstBackslashIndex - 1);
        int fromWhenIndex = firstBackslashIndex + FROM_LENGTH;
        int secondBackslashIndex = taskToAdd.indexOf("/", fromWhenIndex + 1);
        int toWhenIndex = secondBackslashIndex + TO_LENGTH;
        String fromWhen = taskToAdd.substring(fromWhenIndex, secondBackslashIndex - 1);
        String toWhen = taskToAdd.substring(toWhenIndex);
        tasks.add(new Event(taskName, fromWhen, toWhen));
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

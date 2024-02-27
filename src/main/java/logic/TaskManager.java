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

/**
 * Class used to manage tasks. Stores tasks in an ArrayList. Performs task addition, marking, listing,
 * and deletion
 */
public class TaskManager {

    protected int numOfTasks;
    protected ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for TaskManager. Initialises number of tasks stored to 0
     */
    public TaskManager() {
        this.numOfTasks = 0;
    }

    /**
     * Adds a task to be tracked
     *
     * @param input User input from which the task type and name is to be extracted
     * @throws TaskNoNameException If task lacks a name
     * @throws DeadlineNoByDateTimeException If Deadline-type task lacks due date/time
     * @throws EventNoFromDateTimeException If Event-type task lacks start date/time
     * @throws EventNoToDateTimeException If Event-type task lacks end date/time
     * @throws EventToBeforeFromException If Event-type task has end date/time specified before
     * start date/time
     * @throws InvalidInputException If input is invalid
     */
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

    /**
     * Updates number of tasks tracked by Dor and prints newly-added task and updated number of tasks
     */
    private void printAndIncrementAfterAddTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(numOfTasks));
        numOfTasks++;
        System.out.println("Now you have " + numOfTasks + " tasks in the list");
    }

    /**
     * Marks the task at specified index as done or not done
     *
     * @param taskIndex Index of the task to be marked done or not done
     * @param isDone Task is marked as done if true, not done if false
     * @throws InputIndexOutOfBoundsException If user-specified index is out-of-bounds
     */
    public void markTask(int taskIndex, boolean isDone) throws InputIndexOutOfBoundsException {
        if (taskIndex < 0 || taskIndex >= numOfTasks) {
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

    /**
     * Lists all tasks currently tracked
     */
    public void listTasks() {
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
    }

    /**
     * Deletes the task at specified index
     *
     * @param deleteIndex Index of the task to be deleted
     * @throws InputIndexOutOfBoundsException If user-specified index is out-of-bounds
     */
    public void deleteTask(int deleteIndex) throws InputIndexOutOfBoundsException {
        if (deleteIndex < 0 || deleteIndex >= numOfTasks) {
            throw new InputIndexOutOfBoundsException();
        }
        System.out.println("Okay. I've removed this task:");
        System.out.println(tasks.get(deleteIndex));
        tasks.remove(deleteIndex);
        numOfTasks--;
        System.out.println("Now you have " + numOfTasks + " tasks in the list");
    }
}

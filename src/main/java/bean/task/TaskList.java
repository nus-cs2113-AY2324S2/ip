package bean.task;

import bean.command.exception.NoValueException;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> tasks;
    private int numTasks;
    private int numTasksDone;

    public int getNumTasks() {
        return numTasks;
    }

    public int getNumTasksDone() {
        return numTasksDone;
    }

    /**
     * Adds a Todo to the TaskList
     *
     * @param description Description of Todo
     * @throws NoValueException if <code>description</code> is null
     */
    public Task addTask(String description) throws NoValueException {
        Task newTask = new ToDo(description);
        numTasks += 1;
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds a Deadline to the TaskList
     *
     * @param description Description of Deadline
     * @param by Description of time it is due
     * @throws NoValueException if <code>description</code> or <code>by</code> is null
     */
    public Task addTask(String description, String by) throws NoValueException {
        Task newTask = new Deadline(description, by);
        numTasks += 1;
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds an Event to the TaskList
     *
     * @param description Description of Deadline
     * @param start Description of start time
     * @param end Description of end time
     * @throws NoValueException if <code>description</code>, <code>start</code> or <code>end</code> is null
     */
    public Task addTask(String description, String start, String end) throws NoValueException {
        Task newTask = new Event(description, start, end);
        numTasks += 1;
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Removes a Task from the TaskList
     *
     * @param index Index of the task in the TaskList to be removed
     */
    public Task removeTask(int index) {
        Task deletedTask = tasks.remove(index);
        if (deletedTask.checkDone()) {
            numTasksDone -= 1;
        }
        numTasks -= 1;
        return deletedTask;
    }

    /**
     * Marks a Task in the TaskList as done/not done
     *
     * @param index Index of the task to be marked
     * @param isDone Whether task is to be marked done or undone
     * @return Task that was just marked done/not done
     */
    public Task markTask(int index, boolean isDone) {
        if (isDone) {
            tasks.get(index).setDone();
            numTasksDone += 1;
        } else {
            tasks.get(index).setUndone();
            numTasksDone -= 1;
        }
        return tasks.get(index);
    }

    public TaskList() {
        tasks = new ArrayList<>();
        numTasks = 0;
        numTasksDone = 0;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (Task task : tasks) {
            result.append("    ").append(i).append('.').append(task.toString());
            if (i != numTasks) {
                result.append(System.lineSeparator());
            }
            i += 1;
        }
        return result.toString();
    }

    /**
     * Returns a String of commands that can recreate the entire TaskList when passed through
     * bean.Bean.processAndExecute()
     *
     * @return multiple line-separated commands that can recreate the TaskList
     */
    public String toCommand() {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task.toCommand()).append(System.lineSeparator());
        }
        return result.toString();
    }
}

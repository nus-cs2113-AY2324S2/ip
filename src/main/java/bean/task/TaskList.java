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

    public Task addTask(String description) throws NoValueException {
        Task newTask = new ToDo(description);
        numTasks += 1;
        tasks.add(newTask);
        return newTask;
    }

    public Task addTask(String description, String by) throws NoValueException {
        Task newTask = new Deadline(description, by);
        numTasks += 1;
        tasks.add(newTask);
        return newTask;
    }

    public Task addTask(String description, String start, String end) throws NoValueException {
        Task newTask = new Event(description, start, end);
        numTasks += 1;
        tasks.add(newTask);
        return newTask;
    }

    public Task removeTask(int index) {
        numTasks -= 1;
        Task deletedTask = tasks.remove(index);
        if (deletedTask.checkDone()) {
            numTasksDone -= 1;
        }
        return deletedTask;
    }

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

    public String toCommand() {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task.toCommand()).append(System.lineSeparator());
        }
        return result.toString();
    }
}

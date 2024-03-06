package task;

import ui.Parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a list of tasks.
 */
public class TaskList implements Serializable {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Checks if the list of tasks is empty.
     *
     * @return True if the list of tasks is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param token The token of the task to be added.
     * @param parsedInput The parsed input of the task to be added.
     */
    public void addTask(Parser token, String[] parsedInput) {
        if (token == Parser.TODO) {
            tasks.add(new ToDo(parsedInput[0]));
        } else if (token == Parser.DEADLINE) {
            tasks.add(new Deadline(parsedInput[0], parsedInput[1]));
        } else {
            tasks.add(new Event(parsedInput[0], parsedInput[1], parsedInput[2]));
        }
    }

    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Print all the tasks in the list.
     */
    public void displayAll() {
        for (int index = 1; index <= this.size(); index++) {
            System.out.println(index + "." + getTask(index));
        }
    }

    /**
     * Search for tasks that contain the given keyword.
     * 
     * @param keyword The keyword to be searched for.
     * @return The tasks that contain the given keyword.
     */
    public String findTasks(String keyword) {
        StringBuilder tasksFound = new StringBuilder();
        for (int index = 1; index <= this.size(); index++) {
            if (getTask(index).getDescription().contains(keyword)) {
                tasksFound.append(index).append(".").append(getTask(index)).append("\n");
            }
        }
        return tasksFound.toString();
    }
}

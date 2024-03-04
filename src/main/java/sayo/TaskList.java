package sayo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks in the Sayo application.
 * It is capable of adding, removing, and retrieving tasks.
 */
public class TaskList {
    private ArrayList<Task> items;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        items = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from a list of strings.
     * Each string is formatted as a task to be parsed and added to the list.
     *
     * @param lines The list of string representations of tasks.
     * @throws SayoException If an unknown task type is encountered.
     */
    public TaskList(List<String> lines) throws SayoException {
        items = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            Task task;
            switch (parts[0]) {
                case "T":
                    task = ToDo.fromFileFormat(line);
                    break;
                case "D":
                    task = Deadline.fromFileFormat(line);
                    break;
                case "E":
                    task = Event.fromFileFormat(line);
                    break;
                default:
                    throw new SayoException("Unknown task type in file: " + parts[0]);
            }
            items.add(task);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        items.add(task);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The index of the task to be removed.
     * @return The removed Task object.
     */
    public Task removeTask(int index) {
        return items.remove(index);
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return items.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the list as an integer.
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return The ArrayList of all tasks.
     */
    public ArrayList<Task> getTasks() {
        return items;
    }

    /**
     * Searches for tasks that contain the given keyword in their description.
     *
     * @param keyword The string to search for within task descriptions.
     * @return An ArrayList of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: this.items) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
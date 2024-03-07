package helpy.task;

import helpy.Ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param list The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> list) {
        taskList = list;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Displays all the tasks in the task list
     */
    public void printTasks() {
        int label = 1;
        for (Task task : taskList) {
            System.out.print(label + ".");
            System.out.println(task);
            label++;
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Retrieves the length of the task list.
     *
     * @return The length of the task list.
     */
    public int getListLength() {
        return taskList.toArray().length;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks or unmarks a task in the task list.
     *
     * @param commandStart The command start ("mark" or "unmark").
     * @param index        The index of the task to be marked/unmarked.
     * @param ui           The user interface object for printing messages.
     * @return true if the task was successfully marked/unmarked, false otherwise.
     */
    public boolean markTask(String commandStart, int index, Ui ui) {
        Task taskToMark = taskList.get(index);
        if (commandStart.equals("mark") && taskToMark.isDone()) {
            ui.printMessage("You've already completed the selected task o.o");
            return false;
        } else if (commandStart.equals("unmark") && !taskToMark.isDone()) {
            ui.printMessage("The selected task isn't completed so you can't unmark it -.-\"");
            return false;
        }
        taskToMark.setDone(!taskToMark.isDone());
        return true;
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        return taskList.remove(index);
    }
}

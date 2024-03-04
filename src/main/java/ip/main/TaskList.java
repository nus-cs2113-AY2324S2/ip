package ip.main;

import ip.task.Task;

import java.util.ArrayList;

/**
 * Contains the list of tasks and provides methods to obtain information of the list.
 * Also provides methods to add and remove tasks from the list
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructor to initialise the tasks and ui attributes
     *
     * @param ui the user interface interacting with the user
     */
    public TaskList(Ui ui) {
        tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Returns the size of the task list
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new task to the task list
     *
     * @param task the new task to be added to the list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Prints the entire list of tasks, including details of
     * the task type, whether it is done, and the description
     */
    public void printTaskList() {
        ui.print("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.print((i + 1) + "." + tasks.get(i).getDetails());
        }
    }

    /**
     * Processes the user's input to identify the keyword
     * and lists the tasks containing the keyword
     *
     * @param line the user's input
     */
    public void find(String line) {
        String keyword;
        try {
            keyword = line.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            ui.print("Please provide a keyword");
            return;
        }
        int matchingCount = 0;
        ui.print("Here are the tasks containing '" + keyword + "':");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDetails().contains(keyword)) {
                matchingCount++;
                ui.print(matchingCount + "." + tasks.get(i).getDetails());
            }
        }
        if (matchingCount == 0) {
            ui.print("There are no matching tasks");
        }
    }
}

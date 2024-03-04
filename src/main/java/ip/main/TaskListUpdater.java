package ip.main;

import ip.task.Deadline;
import ip.task.Event;
import ip.task.Todo;

/**
 * Provides methods to update the list of tasks according to user's input
 */
public class TaskListUpdater {
    private Ui ui;

    public TaskListUpdater(Ui ui) {
        this.ui = ui;
    }

    /**
     * Processes the user's input to identify the index that
     * needs to be marked as completed and marks the task
     *
     * @param tasks the list of tasks
     * @param line the user's input
     * @return whether a task has been successfully marked
     */
    public boolean markTask(TaskList tasks, String line) {
        int indexMarked;
        try {
            indexMarked = Integer.parseInt(line.substring(5)) - 1;
            tasks.get(indexMarked).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            ui.print("Please provide a number from 1 to " + tasks.size());
            return false;
        } catch (NumberFormatException e) {
            ui.print("Please input an integer");
            return false;
        }
        ui.print("Nice! I've marked this task as done:");
        ui.print(tasks.get(indexMarked).getDetails());
        return true;
    }

    /**
     * Processes the user's input to identify the index that
     * needs to be unmarked as not completed and unmarks the task
     *
     * @param tasks the list of tasks
     * @param line the user's input
     * @return whether a task has been successfully unmarked
     */
    public boolean unmarkTask(TaskList tasks, String line) {
        int indexUnmarked;
        try {
            indexUnmarked = Integer.parseInt(line.substring(7)) - 1;
            tasks.get(indexUnmarked).setDone(false);
        } catch (IndexOutOfBoundsException e) {
            ui.print("Please provide a number from 1 to " + tasks.size());
            return false;
        } catch (NumberFormatException e) {
            ui.print("Please input an integer");
            return false;
        }
        ui.print("OK, I've marked this task as not done yet:");
        ui.print(tasks.get(indexUnmarked).getDetails());
        return true;
    }

    /**
     * Processes the user's input to identify the index that
     * needs to be deleted and removes the task
     *
     * @param tasks the list of tasks
     * @param line the user's input
     * @return whether the task has been successfully deleted
     */
    public boolean deleteTask(TaskList tasks, String line) {
        int indexDeleted;
        try {
            indexDeleted = Integer.parseInt((line.substring(7))) - 1;
            ui.print("Task removed: " + tasks.get(indexDeleted).getDetails());
            tasks.remove(indexDeleted);
        } catch (IndexOutOfBoundsException e) {
            ui.print("Please provide a number from 1 to " + tasks.size());
            return false;
        } catch (NumberFormatException e) {
            ui.print("Please input an integer");
            return false;
        }
        ui.print("Current number of tasks: " + tasks.size());
        return true;
    }

    /**
     * Adds a Todo to the list of tasks
     *
     * @param tasks the list of tasks
     * @param line the user's input
     * @return whether the Todo has been successfully added
     */
    public boolean addTodo(TaskList tasks, String line) {
        try {
            tasks.add(new Todo(line));
        } catch (StringIndexOutOfBoundsException e) {
            ui.print("Please input in the form 'todo <description>'");
            return false;
        }
        ui.print("New task added: " + tasks.get(tasks.size() - 1).getDetails());
        ui.print("Current number of tasks: " + tasks.size());
        return true;
    }

    /**
     * Adds a Deadline to the list of tasks
     *
     * @param tasks the list of tasks
     * @param line the user's input
     * @return whether the Deadline has been successfully added
     */
    public boolean addDeadline(TaskList tasks, String line) {
        try {
            tasks.add(new Deadline(line));
        } catch (StringIndexOutOfBoundsException e) {
            ui.print("Please input in the form 'deadline <description> /by <when>'");
            return false;
        }
        ui.print("New task added: " + tasks.get(tasks.size() - 1).getDetails());
        ui.print("Current number of tasks: " + tasks.size());
        return true;
    }

    /**
     * Adds an Event to the list of tasks
     *
     * @param tasks the list of tasks
     * @param line the user's input
     * @return whether the Event has been successfully added
     */
    public boolean addEvent(TaskList tasks, String line) {
        try {
            tasks.add(new Event(line));
        } catch (StringIndexOutOfBoundsException e) {
            ui.print("Please input in the form 'event <description> /from <when> /to <when>'");
            return false;
        }
        ui.print("New task added: " + tasks.get(tasks.size() - 1).getDetails());
        ui.print("Current number of tasks: " + tasks.size());
        return true;
    }
}

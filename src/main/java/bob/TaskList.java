package bob;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the list of tasks in the program.
 */
public class TaskList {

    List<Task> list;
    Ui ui;

    /**
     * Constructor for the TaskList class.
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        list = tasks;
        ui = new Ui();
    }

    /**
     * Deletes a task from the list.
     * @param line The command to delete a task.
     */
    public void deleteTask(String line) {
        String content;
        content = line.split(" ", 2)[1];

        displayDeleteTaskMessage(content);

        list.remove(Integer.parseInt(content) - 1);


    }

    /**
     * Displays the message when a task is deleted.
     * @param content The content of the command.
     */
    private void displayDeleteTaskMessage(String content) {
        ui.displayHorizontalLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(list.get(Integer.parseInt(content) - 1).getListItem());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        ui.displayHorizontalLine();
    }

    /**
     * Unmarks a task from the list.
     * @param line The command to unmark a task.
     */
    public void unmarkTask(String line) {
        String content;
        content = line.split(" ", 2)[1];
        list.get(Integer.parseInt(content) - 1).setDone(false);

        displayMarkTaskMessage("OK, I've marked this task as not done yet:", content);
    }

    /**
     * Displays the message when a task is marked.
     * @param x The message to display.
     * @param content The content of the command.
     */
    private void displayMarkTaskMessage(String x, String content) {
        ui.displayHorizontalLine();
        System.out.println(x);
        System.out.println(list.get(Integer.parseInt(content) - 1).getListItem());
        ui.displayHorizontalLine();
    }

    /**
     * Marks a task from the list.
     * @param line The command to mark a task.
     */
    public void markTask(String line) {
        String content;
        content = line.split(" ", 2)[1];
        list.get(Integer.parseInt(content) - 1).setDone(true);

        displayMarkTaskMessage("Nice! I've marked this task as done: ", content);
    }

    /**
     * Adds an event to the list.
     * @param line The command to add an event.
     */
    public void addEvent(String line) throws BobException {
        String content, description, start, by;

        try {
            content = line.split(" ", 2)[1];

            description = content.split(" /from ")[0];
            start = content.split(" /from ")[1].split(" /to ")[0];
            by = content.split(" /to ")[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BobException("An event must have a description, a start time, and an end time.");
        }

        Event e = new Event(description, start, by, false);
        list.add(e);

        displayAddTaskMessage("Got it. I've added this event: ", e);
    }

    /**
     * Adds a deadline to the list.
     * @param line The command to add a deadline.
     */
    public void addDeadline(String line) throws BobException {
        String content;
        try {
            content = line.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BobException("A deadline must have a description and a deadline.");
        }

        Deadline d = new Deadline(content.split(" /by ")[0], content.split(" /by ")[1], false);
        list.add(d);

        displayAddTaskMessage("Got it. I've added this deadline: ", d);
    }

    /**
     * Adds a todo to the list.
     * @param line The command to add a todo.
     */
    public void addTodo(String line) throws BobException {
        String content;
        try {
            content = line.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BobException("The description of a todo cannot be empty.");
        }

        Task t = new Task(content, false);
        list.add(t);

        displayAddTaskMessage("Got it. I've added this todo: ", t);
    }

    /**
     * Displays the message when a task is added.
     * @param x The message to display.
     * @param t The task to be added.
     */
    private void displayAddTaskMessage(String x, Task t) {
        ui.displayHorizontalLine();
        System.out.println(x);
        System.out.println(t);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        ui.displayHorizontalLine();
    }

    /**
     * Finds a task from the list.
     * @param line The command to find a task.
     */
    public void findTask(String line) {
        String content;
        content = line.split(" ", 2)[1];

        List<Task> foundList = list.stream()
                .filter(task -> task.getDescription().contains(content))
                .collect(Collectors.toList());

        ui.displayList(foundList);
    }
}
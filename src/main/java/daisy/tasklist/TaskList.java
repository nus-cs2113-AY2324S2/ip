package daisy.tasklist;

import daisy.task.Deadline;
import daisy.task.Event;
import daisy.task.Task;
import daisy.task.Todo;
import daisy.ui.Ui;

import java.util.ArrayList;

/**
 * The TaskList class handles all task list related operations according to user commands.
 */
public class TaskList {

    private static ArrayList<Task> tasks;

    /**
     * Creates a new task list
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * List all tasks within the task list. It goes through all task entries and calls the corresponding toString() method
     * to convert them into String representations suitable for printing.
     * @see Task
     */
    public void listTasks() {
        if (getSize() == 0) {
            System.out.println("There are current no tasks in your list!");
        }
        else {
            for (Task task : tasks) {
                System.out.println((tasks.indexOf(task) + 1) + "." + task);
            }
        }
    }

    /**
     * Returns the task object of the specified index in the task list
     * @param taskNo index of the task object to be returned
     * @return the task object
     */
    public Task getTask(int taskNo) {
        return tasks.get(taskNo);
    }

    /**
     * Changes the done status of the task object of the specified index to done. It then alerts the user by with a success
     * message by calling the corresponding method from ui.
     * @param taskNo index of the targeted task object
     * @param ui the current ui instance the program is working on
     * @see Ui
     */
    public void markDone(int taskNo, Ui ui) {
        getTask(taskNo).setDone();
        ui.printMarked(getTask(taskNo));
    }

    /**
     * Changes the done status of the task object of the specified index to undone. It then alerts the user by with a success
     * message by calling the corresponding method from ui.
     * @param taskNo index of the targeted task object
     * @param ui the current ui instance the program is working on
     * @see Ui
     */
    public void markUndone(int taskNo, Ui ui) {
        tasks.get(taskNo).setUndone();
        ui.printUnmarked(tasks.get(taskNo));
    }

    /**
     * Deletes the task object of the specified index from the task list.
     * @param taskNo index of the targeted task object
     */
    public void deleteTask(int taskNo) {
        System.out.println("I see. The following task will be removed from your list:\n" + tasks.get(taskNo));
        tasks.remove(taskNo);
        outputSize();
    }

    /**
     * Returns the current number of tasks on the task list.
     * @return the current number of tasks on the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Prints the number of tasks current existing on the task list.
     */
    public void outputSize() {
        System.out.println(String.format("Now you have %d tasks in your todo list.", getSize()));
    }

    /**
     * Adds a new task object to the task list. The method will only output the success message of the operation when the
     * printOut input is true. It should only output the success message when the user is adding new tasks during program
     * runtime, and ignore it when the program is loading task data.
     * @param task the task object to be added
     * @param sendPrintOut boolean to indicate whether a success message should be sent upon method completion
     */
    public void addTask(Task task, boolean sendPrintOut) {
        tasks.add(task);

        if (sendPrintOut) {
            System.out.println("Task received! The following has been added to your list of todos:\n" + task);
            outputSize();
        }
    }

    /**
     * Creates a todo task and adds it to the task list.
     * @param taskName task name of the todo
     * @param sendPrintOut boolean to indicate whether a success message should be sent upon method completion
     * @param setDone boolean to indicate whether to set the status of the todo object to done upon creation
     */
    public void createTodo(String taskName, boolean sendPrintOut, boolean setDone) {
        Todo newTodo = new Todo(taskName);
        if (setDone) {
            newTodo.setDone();
        }
        addTask(newTodo, sendPrintOut);
    }

    /**
     * Creates a deadline task and adds it to the task list.
     * @param taskName task name of the deadline
     * @param taskDeadline due date of the deadline
     * @param sendPrintOut boolean to indicate whether a success message should be sent upon method completion
     * @param setDone boolean to indicate whether to set the status of the deadline object to done upon creation
     */
    public void createDeadline(String taskName, String taskDeadline, boolean sendPrintOut, boolean setDone) {
        Deadline newDeadline = new Deadline(taskName,taskDeadline);
        if (setDone) {
            newDeadline.setDone();
        }
        addTask(newDeadline, sendPrintOut);
    }

    /**
     * Creates an event task and adds it to the task list.
     * @param taskName task name of the event
     * @param fromDate from date of the event
     * @param toDate to date of the event
     * @param sendPrintOut boolean to indicate whether a success message should be sent upon method completion
     * @param setDone boolean to indicate whether to set the status of the deadline object to done upon creation
     */
    public void createEvent(String taskName, String fromDate, String toDate, boolean sendPrintOut, boolean setDone) {
        Event newEvent = new Event(taskName, fromDate, toDate);
        if (setDone) {
            newEvent.setDone();
        }
        addTask(newEvent, sendPrintOut);
    }

    /**
     * Returns a list of tasks with their task names containing the keyword.
     * @param keyWord search keyword used to find the task names
     */
    public void findTasks(String keyWord) {
        int count = 1;
        for (Task task : tasks) {
            if (task.contains(keyWord)) {
                System.out.println(count + "." + task);
                count++;
            }
        }
        if (count == 1) {
            System.out.println("No task matching your keyword found!");
        }
    }

}

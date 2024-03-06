import java.util.ArrayList;

/**
 * Represents a collection of tasks in the Jane task management application.
 * It provides methods to add, remove, and retrieve tasks, as well as print the list of tasks.
 */
public class TaskList {
    /** The list containing tasks. */
    protected ArrayList<Task> list;
    
    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Retrieves the count of tasks in the TaskList.
     *
     * @return The count of tasks in the list.
     */
    public int getCount() {
        return list.size();
    }

    /**
     * Prints the list of tasks with their respective sequence numbers.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < getCount(); i++) {
            System.out.println(i+1 + "." + this.list.get(i));
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return list;
    }

    /**
     * Removes a task from the TaskList based on the specified sequence number.
     *
     * @param sequence The sequence number of the task to be removed.
     */
    public void removeTask(int sequence) {
        Task task = this.list.get(sequence);
        this.list.remove(sequence);
        System.out.println("Noted. I've removed this task:\n"
                + task
                + "\n"
                + "Now you have "
                + getCount()
                + " tasks in the list.");
    }
}
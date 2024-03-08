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
     * Retrieves the count of tasks in the TaskList.
     *
     * @return The count of tasks in the list.
     */
    public int getCount() {
        return list.size();
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
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Removes a task from the TaskList based on the specified sequence number.
     *
     * @param input The sequence number of the task to be removed in String.
     * @throws JaneException If input cannot be converted to Integer or sequence number out of bounds.
     */
    public void removeTask(String input) throws JaneException {
        try {
            int sequence = Integer.parseInt(input) - 1;
            Task task = this.list.get(sequence);
            this.list.remove(sequence);
            System.out.println(Message.TASK_REMOVED + task);
            System.out.println(Message.numberOfTasks(getCount()));
        } catch (NumberFormatException e) {
            throw new JaneException(Message.INTEGER_NUMBER_REQUIRED);
        } catch (IndexOutOfBoundsException e) {
            throw new JaneException(Message.INDEX_OUT_OF_BOUNDS);
        }

    }

    /**
     * Prints the list of tasks with their respective sequence numbers.
     */
    public void printList() {
        System.out.println(Message.LIST_HEADER);
        for (int i = 0; i < getCount(); i++) {
            System.out.println(i+1 + "." + this.list.get(i));
        }
    }
}
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        indicateNewTask(tasks.get(tasks.size() - 1), tasks.size());
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param indexTask The index of the task to be deleted.
     */
    public void deleteTask(int indexTask) {
        Task removedTask = tasks.get(indexTask - 1);
        tasks.remove(removedTask);
        System.out.println("Command received. I've removed this task:");
        System.out.println((indexTask) + ". " + "[" + removedTask.getStatusIcon() + "]" + removedTask.description);
        System.out.println("Currently you have " + tasks.size() + " tasks in the list below.");
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param indexTask The index of the task to be marked as completed.
     */
    public void markTaskAsCompleted(int indexTask) {
        tasks.get(indexTask - 1).markAsCompleted();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println((indexTask) + ". " + "[" + tasks.get(indexTask - 1).getStatusIcon() + "]" + tasks.get(indexTask - 1).description);
        System.out.println("____________________________________________________________");
    }

    /**
     * Lists all the tasks in the task list.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + " " + "[" + tasks.get(i).typeOfTask + "]" + "[" + tasks.get(i).getStatusIcon() + "]" + tasks.get(i).description);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param indexTask The index of the task to be marked as not completed.
     */
    public void markTaskAsNotCompleted(int indexTask) {
        tasks.get(indexTask - 1).markAsNotCompleted();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println((indexTask) + ". " + "[" + tasks.get(indexTask - 1).getStatusIcon() + "]" + tasks.get(indexTask - 1).description);
        System.out.println("____________________________________________________________");
    }

    /**
     * Indicates the addition of a new task.
     *
     * @param newTask              The new task added.
     * @param currentNumberOfTasks The current number of tasks after addition.
     */
    public void indicateNewTask(Task newTask, int currentNumberOfTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Well done, you've added a new task: ");
        System.out.println("[" + newTask.typeOfTask + "]" + "[" + newTask.getStatusIcon() + "]" + newTask.description);
        System.out.println("Currently you have " + currentNumberOfTasks + " task(s) in your list!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }
}
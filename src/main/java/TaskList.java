import java.util.ArrayList;

/**
 * TaskList class to manipulate tasks
 * Consists of methods to add, delete, mark and find tasks
 */
public class TaskList {
    private static ArrayList<Task> tasks; //the list of tasks
    /**
     * Constructs a new TaskList with a specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks(){
        return this.tasks;
    }
    /**
     * Displays the list of tasks to the user.
     */
    public void displayTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String taskInfo = currentTask.toString();
            System.out.println((i + 1) + ". " + taskInfo);
        }
    }
    /**
     * Adds a todo task to the task list.
     *
     * @param task The task description.
     * @throws LoopyExceptions If the task description is not provided or is too short.
     */
    public static void addTodo(String task) throws LoopyExceptions {
        if (task.length() <= 5) {
            throw new LoopyExceptions("Todo cannot be empty!");
        }
        if (task.length() > 5) {
            String description = task.substring(5, task.length());
            tasks.add(new TodoTask(description));
            Storage.saveFile(tasks);
            System.out.println("Got it. I've added this task: ");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new LoopyExceptions("Please add a task!");
        }
    }
    /**
     * Adds a deadline task to the task list.
     *
     * @param task The task description including the deadline.
     */
    public static void addDeadline(String task) {
        //separate the input into 2 substrings: description and deadline
        int splitPosition = task.indexOf("/");
        String description = task.substring(9, splitPosition);
        String deadline = task.substring(splitPosition + 4, task.length());

        tasks.add(new DeadlineTask(description, deadline));
        Storage.saveFile(tasks);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    /**
     * Adds an event task to the task list.
     *
     * @param task The task description including the start and end time.
     */
    public static void addEvent(String task) {
        int fromPosition = task.indexOf("from");
        int toPosition = task.indexOf("to");
        String description = task.substring(6, fromPosition - 1);
        String fromDate = task.substring(fromPosition + 5, toPosition - 1);
        String toDate = task.substring(toPosition + 3, task.length());

        tasks.add(new EventTask(description, fromDate, toDate));
        Storage.saveFile(tasks);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    /**
     * Marks a specified task as done.
     *
     * @param task The index of the task to mark as done, as a String to be later parsed as Integer.
     * @throws LoopyExceptions If the specified index is invalid or out of range.
     */
    public static void markTaskAsDone(String task) throws LoopyExceptions {
        if (task.length() <= 5) {
            throw new LoopyExceptions("Please specify which task to mark.");
        }
        int taskIndex = Integer.parseInt(task.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task currentTask = tasks.get(taskIndex);
            currentTask.markAsDone();
            Storage.saveFile(tasks);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + currentTask);
        } else {
            throw new LoopyExceptions("Please specify which task to mark.");
        }
    }
    /**
     * Marks a specified task as undone.
     *
     * @param task The index of the task to mark as undone, as a String to be later parsed as Integer.
     * @throws LoopyExceptions If the specified index is invalid or out of range.
     */
    public static void markTaskAsUndone(String task) throws LoopyExceptions {
        int taskIndex = Integer.parseInt(task.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task currentTask = tasks.get(taskIndex); //retrieve this current task from tasks
            currentTask.markAsNotDone();
            Storage.saveFile(tasks);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + currentTask);
        } else {
            throw new LoopyExceptions("Please specify which task to unmark.");
        }
    }
    /**
     * Deletes a specified task.
     *
     * @param task The index of the task to delete, as a String to be later parsed as Integer.
     */
    public static void deleteTask(String task) {
        int taskIndex = Integer.parseInt(task.substring(7)) - 1;
        Task currentTask = tasks.get(taskIndex); //retrieve this current task from tasks
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            System.out.println("I have deleted the task: ");
            System.out.println(currentTask);
            tasks.remove(taskIndex);
            Storage.saveFile(tasks);
            System.out.println("You now have " + tasks.size() + " tasks left");
        }
    }
    /**
     * Finds a task in the existing task list.
     *
     * @param task The task description to be found.
     */
    public static void findTask(String task){
        System.out.println("Here are the matching tasks in your list:");
        String description= task.substring(6, task.length());
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.getDescription().toLowerCase().contains(description.toLowerCase())) {
                count++;
                System.out.println(count + ". " + currentTask);
            }
        }
        if (count == 0) {
            System.out.println("Sorry! I did not find matching tasks.");
        }
    }
}

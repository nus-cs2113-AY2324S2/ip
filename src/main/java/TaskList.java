import java.util.ArrayList;
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String userInput) throws HandleException {
        // Similar to addTask method in TaskManager, but refactored for this class
        Task task = TaskFactory.createTask(userInput);
        if (task != null) {
            tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    public void deleteTask(int taskIndex) throws HandleException {
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new HandleException("OOPS!!! The task number is invalid.");
        }
        Task removedTask = tasks.remove(taskIndex - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}

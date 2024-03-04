import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(String userInput) throws HandleException {
        Task task = TaskFactory.createTask(userInput);
        if (task != null) {
            tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
    public void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + task);
        } else {
            System.out.println("Task with given index does not exist.");
        }
    }

    public void markTaskAsNotDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n  " + task);
        } else {
            System.out.println("Task with given index does not exist.");
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

    public void findTask(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                System.out.println((i + 1) + "." + task);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No matching tasks found.");
        }
    }
}


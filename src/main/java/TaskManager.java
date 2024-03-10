import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTodoTask(String description) {
        tasks.add(new Todo(description));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addDeadlineTask(String description, String by) {
        tasks.add(new Deadline(description, by));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addEventTask(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks in the list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index));
    }

    public void deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(index));
        tasks.remove(index);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void findTasks(String keyword) {
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                if (count == 0) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println((count + 1) + ". " + tasks.get(i));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No matching tasks found.");
        }
    }
}

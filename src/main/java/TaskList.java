import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String input) {
        Task task = new Task(input);
        tasks.add(task);
        System.out.printf("Added: %s\n", input);
    }

    public void markTask(String input) {
        int number = extractInt(input);
        if (number > 0 && number <= tasks.size()) {
            Task task = tasks.get(number - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            printTask(task, -1);
        }
    }

    public void unmarkTask(String input) {
        int number = extractInt(input);
        if (number > 0 && number <= tasks.size()) {
            Task task = tasks.get(number - 1);
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            printTask(task, -1);
        }
    }

    public void printTasks() {
        int taskIndex = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks){
            printTask(task, taskIndex);
            taskIndex++;
        }
    }

    public void printTask(Task task, int index) {
        if (index < 0) {
            System.out.printf("[%s] %s\n", task.getStatusIcon(), task.description);
        } else {
            System.out.printf("%d.[%s] %s\n", index, task.getStatusIcon(), task.description);
        }
    }

    public int extractInt(String input) {
        String number = input.replaceAll("[^0-9]", "");
        return Integer.parseInt(number);
    }
}

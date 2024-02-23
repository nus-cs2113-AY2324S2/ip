// TaskManager.java
public class TaskManager {
    private Task[] tasks;
    private int taskCount;

    public TaskManager(int capacity) {
        tasks = new Task[capacity];
        taskCount = 0;
    }

    public void addTask(String inputLine) {
        String[] parts = inputLine.split(" ", 2);
        String taskType = parts[0];
        Task newTask = null;

        try {
            switch (taskType) {
            case "todo":
                newTask = new Todo(parts[1]);
                break;
            case "deadline":
                String[] deadlineParts = parts[1].split("/by", 2);
                newTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                break;
            case "event":
                String[] eventParts = parts[1].split("/at", 2);
                newTask = new Event(eventParts[0].trim(), eventParts[1].trim(), taskType);
                break;
            default: 
                System.out.println("Invalid task type.");
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid task format.");
            return;
        }

        if (newTask != null) {
            tasks[taskCount++] = newTask;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newTask);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
    }

    public void displayTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    public void markTask(int taskNumber, boolean isDone) {
        if (taskNumber < 1 || taskNumber > taskCount) {
            System.out.println("Task number is invalid.");
            return;
        }
        Task task = tasks[taskNumber - 1];
        if (isDone) {
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + task);
    }
}

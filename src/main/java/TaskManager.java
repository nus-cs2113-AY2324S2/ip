// TaskManager.java
public class TaskManager {
    private Task[] tasks;
    private int taskCount;

    public TaskManager(int capacity) {
        tasks = new Task[capacity];
        taskCount = 0;
    }

    public void addTask(String inputLine) throws KapwaException {
        String[] parts = inputLine.split(" ", 2);
        if (parts.length < 2) {
            throw new KapwaException("Invalid task format.");
        }
        String taskType = parts[0];
        Task newTask = null;

        try {
            switch (taskType) {
            case "todo":
                newTask = new Todo(parts[1]);
                if (parts[1].trim().isEmpty()) {
                    throw new KapwaException("The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                String[] deadlineParts = parts[1].split("/by", 2);
                newTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                if (deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                    throw new KapwaException("The description and by of a deadline cannot be empty.");
                }
                break;
            case "event":
                String[] eventDetails = parts[1].split("/from", 2);
                String[] fromTo = eventDetails[1].split("/to", 2);
                newTask = new Event(eventDetails[0].trim(), fromTo[0].trim(), fromTo[1].trim());
                if (eventDetails[0].trim().isEmpty() || fromTo[0].trim().isEmpty() || fromTo[1].trim().isEmpty()) {
                    throw new KapwaException("The description, from and to of an event cannot be empty.");
                }
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

    public void displayTaskList() throws KapwaException{
        if (taskCount == 0) {
            throw new KapwaException("There are no tasks in the list.");
        }
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

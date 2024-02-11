import java.util.Scanner;

public class TaskManager {
    private Task[] tasks;
    private int taskCount;
    private static final String BORDER = "______________________________________________________________\n";

    private void printList() {
        System.out.print(BORDER + " Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". [" + tasks[i].getTaskType() + "] "
                    + "[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getDescription());
        }
        System.out.print(BORDER);
    }

    private void markTask(int index) {
        if (index < 0 || index >= taskCount) {
            System.out.print("Invalid task number\n");
        } else {
            System.out.print(BORDER + " Good job! I've marked this task as done:\n"
                    + " [" + tasks[index].getStatusIcon() + "] " + tasks[index].getDescription() + "\n"
                    + BORDER);
        }
    }

    private void handleMarking(String input) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        boolean isDone = input.startsWith("mark");
        tasks[index].markTask(isDone);
        markTask(index);
    }

    // Adds a task based on the input provided.
    private void addTask(String input) {
        Task task = createTaskFromInput(input);
        if (task != null) {
            tasks[taskCount] = task;
            taskCount++;
            printTask(task);
        } else {
            System.out.print("Unknown task type. Please enter a todo, deadline or event task\n");
        }
    }

    private Task createTaskFromInput(String input) {
        if (input.startsWith("todo")) {
            return createTodoTask(input);
        } else if (input.startsWith("deadline")) {
            return createDeadlineTask(input);
        } else if (input.startsWith("event")) {
            return createEventTask(input);
        }
        return null; // Return null if the task type is unknown.
    }

    // Creates a Todo task from the input.
     private Todo createTodoTask(String input) {
        String description = input.substring(5).trim(); // Removing 'todo ' prefix.
        return new Todo(description);
    }

    // Creates a Deadline task from the input.
    private Deadline createDeadlineTask(String input) {
        String[] deadlineDetails = input.split(" /by ", 2);
        String description = deadlineDetails[0].substring(9).trim(); // Removing 'deadline ' prefix.
        String by = deadlineDetails.length > 1 ? deadlineDetails[1] : "No deadline specified";
        return new Deadline(description, by);
    }

    // Creates an Event task from the input.
    private Event createEventTask(String input) {
        String[] eventDetails = input.split(" /from | /to ");
        String description = eventDetails[0].substring(6).trim(); // Removing 'event ' prefix.
        String from = (eventDetails.length > 1) ? eventDetails[1] : "No start time specified";
        String to = (eventDetails.length > 2) ? eventDetails[2] : "No end time specified";
        return new Event(description, from, to);
    }


    private void printTask(Task task) {
        System.out.print(BORDER + " Got it. I've added this task:\n"
                + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n"
                + BORDER);
    }

    public TaskManager() {
        tasks = new Task[100];
        taskCount = 0;
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();

            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                handleMarking(input);

            } else {
                addTask(input);
            }
            input = in.nextLine();
        }
    }
}

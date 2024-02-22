import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final String name;
    private final List<Task> tasks;

    public Duke(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public void greet() {
        System.out.println("=========================");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    public void addTask(String description) throws DukeException{
        Scanner scanner = new Scanner(description);
        scanner.useDelimiter(" ");
        String taskType = scanner.next().toLowerCase();
//        System.out.println("taskType: " + taskType);

        String taskDescription;
        if (scanner.hasNext()) {
            taskDescription = scanner.nextLine().trim();
        } else {
            taskDescription = "";
        }
//        System.out.println("taskDescription: " + taskDescription);

        Task task;
        switch (taskType) {
            case "todo":
                if (taskDescription.isEmpty()) {
                    throw new DukeException("NO!!! The description of a todo task cannot be empty!");
                }
                task = new Todo(taskDescription);
                break;
            case "deadline":
                String[] deadlineParts = taskDescription.split(" /by ", 2);
                String deadlineDescription = deadlineParts[0];
                String by = deadlineParts.length > 1 ? deadlineParts[1] : "";
                task = new Deadline(deadlineDescription, by);
                break;
            case "event":
                String[] eventParts = taskDescription.split(" /from ", 3);
                String eventDescription = eventParts[0];
                if (eventParts.length < 2) {
                    System.out.println("Invalid event task format. Please include the start and end times.");
                    return;
                }
                String[] timeParts = eventParts[1].split(" /to ", 2);
                String start = timeParts[0];
                String end = timeParts.length > 1 ? timeParts[1] : "";
                task = new Event(eventDescription, start, end);
                break;
            default:
                System.out.println("I dont understand what do you mean, please try again.");
                return;
        }
        tasks.add(task);
        System.out.println("Added: " + task);
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void markTaskAsDone(String description) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                if (!task.isDone()) {
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                } else {
                    System.out.println("Task already marked as done.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Task not found.");
        }
    }

    public void unmarkTaskAsDone(String description) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                if (task.isDone()) {
                    task.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                } else {
                    System.out.println("Task is already marked as not done.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Task not found.");
        }
    }

    public void echoCommands() {
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            command = scanner.nextLine();
            if (command.equals("list") || command.startsWith("list ")) {
                listTasks();
            } else if (command.startsWith("mark ")) {
                markTaskAsDone(command.substring(5));
            } else if (command.startsWith("unmark ")) {
                unmarkTaskAsDone(command.substring(7));
            } else if (!command.startsWith("bye")) {
                try {
                    addTask(command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
//                addTask(command);
            }
        } while (!command.equals("bye"));

        scanner.close();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("=========================");
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke("aoliba");

        chatbot.greet();
        chatbot.echoCommands();
        chatbot.exit();
    }

}
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

    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("added: " + description);
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
//                System.out.println((i + 1) + ".[" + (tasks.get(i).isDone ? "X] " : " ] ") + tasks.get(i));
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
            if (command.startsWith("list")) {
                listTasks();
            } else if (command.startsWith("mark ")) {
                markTaskAsDone(command.substring(5));
            } else if (command.startsWith("unmark ")) {
                unmarkTaskAsDone(command.substring(7));
            } else if (!command.startsWith("bye")) {
                addTask(command);
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

    private static class Task {
        private final String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String markAsDone() {
            this.isDone = true;
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String markAsNotDone() {
            this.isDone = false;
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String getDescription() {
            return description;
        }

        public boolean isDone() {
            return isDone;
        }

        @Override
        public String toString() {
            String status = isDone ? "[X]" : "[ ]";
            return status + " " + description;
        }
    }
}

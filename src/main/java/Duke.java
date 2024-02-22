import java.io.*;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/tasks.txt";
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm LeoDas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String userInput = scanner.nextLine();

                if ("bye".equalsIgnoreCase(userInput)) {
                    saveTasksToFile();
                    break;

                }

                if ("list".equalsIgnoreCase(userInput)) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        Task task = tasks[i];
                        System.out.println((i + 1) + "." + task);
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark")) {
                    processTask(userInput, tasks, true, taskCount);
                } else if (userInput.startsWith("unmark")) {
                    processTask(userInput, tasks, false, taskCount);
                } else if (userInput.startsWith("todo")) {
                    if (taskCount >= 100) {
                        throw new DukeException("You have reached the task limit. Cannot add more tasks!");
                    }

                    String description = userInput.substring(4).trim();

                    if (description.isEmpty()) {
                        throw new DukeException("OOPSSSSSSS!!! The task's description is empty! Please do specify!");
                    }

                    Todo newTodo = new Todo(description);
                    tasks[taskCount] = newTodo;
                    taskCount++;

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newTodo);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("deadline")) {
                    if (taskCount >= 100) {
                        throw new DukeException("You have reached the task limit. Cannot add more tasks!");
                    }

                    String content = userInput.substring(8).trim();
                    int index = content.indexOf("/by");
                    if (index == -1) {
                        throw new DukeException("Please use '/by' to specify the deadline time.");
                    } else {
                        String description = content.substring(0, index).trim();
                        String by = content.substring(index + 4).trim();
                        Deadline newDeadline = new Deadline(description, by);
                        tasks[taskCount] = newDeadline;
                        taskCount++;
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newDeadline);
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                } else if (userInput.startsWith("event")) {
                    if (taskCount >= 100) {
                        throw new DukeException("You have reached the task limit. Cannot add more tasks!");
                    }

                    String content = userInput.substring(5).trim();

                    String[] parts = content.split("/from | /to ");

                    if (parts.length < 3) {
                        throw new DukeException("Please use the format: event [description] /from [start time] /to [end time]");
                    }

                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();

                    Event newEvent = new Event(description, from, to);
                    tasks[taskCount] = newEvent;
                    taskCount++;

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newEvent);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new DukeException("OOPSSSSSSS!!! What is that? Please type in ? :(");
                }
            } catch (DukeException de) {
                System.out.println("____________________________________________________________");
                System.out.println(de.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public static void processTask(String userInput, Task[] tasks, boolean mark, int taskCount) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber <= 0 || taskNumber > taskCount) {
                throw new IndexOutOfBoundsException();
            }
            Task task = tasks[taskNumber - 1];
            if (mark) {
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  [" + task.getStatusIcon() + "] " + task.description);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That task doesn't exist!");
        }
    }
    public static void saveTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // Create directories if they don't exist
            }
            file.createNewFile(); // Create the file if it doesn't exist
            try (FileWriter writer = new FileWriter(file)) {
                for (int i = 0; i < taskCount; i++) {
                    writer.write(tasks[i].toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    // Method to load tasks from a file
    // Method to load tasks from a file
    public static void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                if (task != null) {
                    tasks[taskCount++] = task;
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file doesn't exist
            System.err.println("File cannot be found. I shall create a new file...");
            File file = new File(FILE_PATH);
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                System.err.println("Error creating a new file: " + ioException.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}


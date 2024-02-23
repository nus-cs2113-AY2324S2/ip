import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String FILE_PATH = "./data/tasks.txt";
//    public static Task[] tasks = new Task[100];
//    public static int taskCount = 0;
static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm LeoDas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        load();

        Scanner scanner = new Scanner(System.in);


        while (true) {
            try {
                String userInput = scanner.nextLine();

                if ("bye".equalsIgnoreCase(userInput)) {
                    break;

                }

                if ("list".equalsIgnoreCase(userInput)) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println((i + 1) + "." + task);
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark")) {
                    processTask(userInput, tasks, true);
                } else if (userInput.startsWith("unmark")) {
                    processTask(userInput, tasks, false);
                } else if (userInput.startsWith("todo")) {

                    String description = userInput.substring(4).trim();

                    if (description.isEmpty()) {
                        throw new DukeException("OOPSSSSSSS!!! The task's description is empty! Please do specify!");
                    }

                    Todo newTodo = new Todo(description);
                    tasks.add(newTodo);

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newTodo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("deadline")) {

                    String content = userInput.substring(8).trim();
                    int index = content.indexOf("/by");
                    if (index == -1) {
                        throw new DukeException("Please use '/by' to specify the deadline time.");
                    } else {
                        String description = content.substring(0, index).trim();
                        String by = content.substring(index + 4).trim();
                        Deadline newDeadline = new Deadline(description, by);
                        tasks.add(newDeadline);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newDeadline);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                } else if (userInput.startsWith("event")) {
                    String content = userInput.substring(5).trim();

                    String[] parts = content.split("/from | /to ");

                    if (parts.length < 3) {
                        throw new DukeException("Please use the format: event [description] /from [start time] /to [end time]");
                    }

                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();

                    Event newEvent = new Event(description, from, to);
                    tasks.add(newEvent);

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newEvent);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("delete")) {
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                        if (taskNumber <= 0 || taskNumber > tasks.size()) {
                            throw new DukeException("Invalid task number!");
                        }

                        Task deletedTask = tasks.get(taskNumber - 1);

                        tasks.remove(taskNumber - 1);

                        System.out.println("____________________________________________________________");
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println("  " + deletedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please provide a valid task number.");
                        System.out.println("____________________________________________________________");
                    } catch (DukeException de) {
                        System.out.println("____________________________________________________________");
                        System.out.println(de.getMessage());
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    throw new DukeException("OOPSSSSSSS!!! What is that? Please type in ? :(");
                }
            } catch (DukeException de) {
                System.out.println("____________________________________________________________");
                System.out.println(de.getMessage());
                System.out.println("____________________________________________________________");
            }
            save();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public static void processTask(String userInput, ArrayList<Task> tasks, boolean mark) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            Task task = tasks.get(taskNumber - 1);
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
    public static void save() {
        try {
            File file = new File(FILE_PATH);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            try (FileWriter writer = new FileWriter(file)) {
                for (int i = 0; i < tasks.size(); i++) {
                    writer.write(tasks.get(i).toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
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


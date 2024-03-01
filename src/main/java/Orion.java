import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Orion {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String DATA_PATH = "./data/";
    private static final String DATA_FILE = DATA_PATH + "orion.txt";

    public static void main(String[] args) {
        createDataFileIfNotExist();
        loadTasks();
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine().trim();
            if ("bye".equalsIgnoreCase(input)) {
                saveTasks();
                printGoodbyeMessage();
                break;
            }
            try {
                processInput(input);
                saveTasks();
            } catch (OrionException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printWelcomeMessage() {
        System.out.println(Constants.LOGO);
        System.out.println("Hello! I'm Orion\nWhat can I do for you?");
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void processInput(String input) throws OrionException {
        if (input.startsWith("todo")) {
            handleTodo(input);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input);
        } else if (input.startsWith("event")) {
            handleEvent(input);
        } else if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("delete")) {
            deleteTask(input);
        } else {
            throw new OrionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void handleTodo(String input) throws OrionException {
        if (input.trim().equals("todo")) {
            throw new OrionException("OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDescription = input.substring(5);
        Todo newTodo = new Todo(taskDescription);
        tasks.add(newTodo);
        System.out.println("Got it. I've added this task:\n  " + newTodo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadline(String input) throws OrionException {
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new OrionException("OOPS!!! The deadline time cannot be empty.");
        }
        Deadline newDeadline = new Deadline(parts[0], parts[1]);
        tasks.add(newDeadline);
        System.out.println("Got it. I've added this task:\n  " + newDeadline);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleEvent(String input) throws OrionException {
        String[] parts = input.substring(6).split(" /at ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new OrionException("OOPS!!! The event time cannot be empty.");
        }
        Event newEvent = new Event(parts[0], parts[1]);
        tasks.add(newEvent);
        System.out.println("Got it. I've added this task:\n  " + newEvent);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void deleteTask(String input) throws OrionException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removedTask = tasks.remove(index);
            System.out.println("Noted. I've removed this task:\n  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new OrionException("Sorry, but there's no task with that number.");
        } catch (NumberFormatException e) {
            throw new OrionException("I need a number to know which task to delete.");
        }
    }

    private static void createDataFileIfNotExist() {
        try {
            Files.createDirectories(Paths.get(DATA_PATH));
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Could not create data file.");
        }
    }

    private static void loadTasks() {
        try {
            Files.lines(Paths.get(DATA_FILE)).forEach(line -> {
                // Assume the line format is 'TYPE | STATUS | DESCRIPTION | TIME'
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        Todo todo = new Todo(parts[2]);
                        if ("1".equals(parts[1])) {
                            todo.setDone(true);
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(parts[2], parts[3]);
                        if ("1".equals(parts[1])) {
                            deadline.setDone(true);
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(parts[2], parts[3]);
                        if ("1".equals(parts[1])) {
                            event.setDone(true);
                        }
                        tasks.add(event);
                        break;
                    default:
                        System.out.println("Unrecognized task type in data file.");
                        break;
                }
            });
        } catch (IOException e) {
            System.out.println("Could not load tasks from file.");
        }
    }

    private static void saveTasks() {
            try (FileWriter writer = new FileWriter(DATA_FILE)) {
                for (Task task : tasks) {
                    writer.write(task.toDataString() + System.lineSeparator());
                }
            } catch (IOException e) {
                System.out.println("Could not save tasks to file.");
            }
        }
    }

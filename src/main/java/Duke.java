import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "tasks.txt";

    public static void main(String[] args) {
        loadTasksFromFile();

        // Greet the user and initialise Jonas Chatbot
        String name = "Jonas";
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Da Gei Ho, Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Read user input
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Kamxia. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                saveTasksToFile();
                break;
            } else {
                try {
                    processInput(userInput);
                } catch (DukeException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println("OOPS!!! " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            }
        }

        scanner.close();
    }

    // Process user input
    private static void processInput(String userInput) throws DukeException {
        if (userInput.startsWith("todo ")) {
            addTodoTask(userInput.substring(5));
        } else if (userInput.startsWith("deadline ")) {
            String[] split = userInput.substring(9).split(" /by ");
            if (split.length != 2) {
                throw new DukeException("The description of a deadline task should be followed by '/by <deadline>'.");
            }
            addDeadlineTask(split[0], split[1]);
        } else if (userInput.startsWith("event ")) {
            String[] split = userInput.substring(6).split(" /from | /to ");
            if (split.length != 3) {
                throw new DukeException("The description of an event task should be followed by '/from <start> /to <end>'.");
            }
            addEventTask(split[0], split[1], split[2]);
        } else if (userInput.equalsIgnoreCase("list")) {
            displayTasks();
        } else if (userInput.startsWith("mark ")) {
            markTask(userInput);
        } else if (userInput.startsWith("unmark ")) {
            unmarkTask(userInput);
        } else if (userInput.startsWith("delete ")) {
            deleteTask(userInput);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :(");
        }
    }

    // Add a todo task to the list
    public static void addTodoTask(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo task cannot be empty.");
        }
        tasks.add(new Todo(description));
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    // Add a deadline task to the list
    public static void addDeadlineTask(String description, String by) throws DukeException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new DukeException("The description and deadline of a deadline task cannot be empty.");
        }
        tasks.add(new Deadline(description, by));
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    // Add an event task to the list
    public static void addEventTask(String description, String start, String end) throws DukeException {
        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new DukeException("The description and timing of an event task cannot be empty.");
        }
        tasks.add(new Event(description, start, end));
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    // Display tasks in the list
    private static void displayTasks() throws DukeException {
        if (tasks.isEmpty()) {
            System.out.println("____________________________________________________________");
            System.out.println("Ehhh, you got no tasks to do leh. Try adding some! :(");
            System.out.println("____________________________________________________________");
            return;
        }
        System.out.println("____________________________________________________________");
        System.out.println("LaiLaiLai, Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    // Mark a task as done according to number provided
    private static void markTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice one lah! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskIndex));
                System.out.println("____________________________________________________________");
            } else {
                throw new DukeException("Invalid task number provided.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    // Mark a task as not done according to number provided
    private static void unmarkTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK can, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(taskIndex));
                System.out.println("____________________________________________________________");
            } else {
                throw new DukeException("Invalid task number provided.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    // Delete a task from the list
    private static void deleteTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                System.out.println("____________________________________________________________");
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + tasks.get(taskIndex));
                tasks.remove(taskIndex);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                saveTasksToFile();
            } else {
                throw new DukeException("Invalid task number provided.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    // Load tasks from file
    private static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] taskInfo = line.split("\\|");
                    String taskType = taskInfo[0].trim();
                    boolean isDone = taskInfo[1].trim().equals("1");
                    String taskDescription = taskInfo[2].trim();
                    switch (taskType) {
                        case "T":
                            tasks.add(new Todo(taskDescription, isDone));
                            break;
                        case "D":
                            tasks.add(new Deadline(taskDescription, taskInfo[3].trim(), isDone));
                            break;
                        case "E":
                            tasks.add(new Event(taskDescription, taskInfo[3].trim(), taskInfo[4].trim(), isDone));
                            break;
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    // Save tasks to file
    private static void saveTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}

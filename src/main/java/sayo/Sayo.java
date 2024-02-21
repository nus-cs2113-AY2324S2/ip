package sayo;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Sayo {

    private static ArrayList<Task> items = new ArrayList<>();

    private static String dataFilePath = "./data/sayo.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        loadTasksFromFile();

        System.out.println("Hello! I'm Sayo and it's great to see you! \n" + "What can I do for you today? \n");

        String input = " ";

        do {
            try {
                input = scanner.nextLine().trim();
                if (input.equals("list")) {
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println((i + 1) + ". " + items.get(i));
                    }
                } else if (input.startsWith("mark")) {
                    System.out.println("test");
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index >= 0 && index < items.size()) {
                        items.get(index).markAsDone();
                        System.out.println("Awesome! I've marked this task as done: ");
                        System.out.println(items.get(index));
                    }
                    saveTasksToFile();
                } else if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= 0 && index < items.size()) {
                        items.get(index).unmarkAsDone();
                        System.out.println("Awesome! I've unmarked this task: ");
                        System.out.println(items.get(index));
                    }
                    saveTasksToFile();
                } else if (input.startsWith("todo")) {
                    addToDo(input, items);
                } else if (input.startsWith("deadline")) {
                    int byIndex = input.indexOf("/by");
                    if (byIndex == -1 || byIndex + 4 >= input.length()) {
                        System.out.println(
                                "Please enter the deadline in the format: deadline <task description> /by <due date>");
                    } else {
                        String description = input.substring(9, byIndex).trim();
                        String by = input.substring(byIndex + 4).trim();
                        Deadline newDeadline = new Deadline(description, by);
                        items.add(newDeadline); // Add directly to the ArrayList
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newDeadline);
                        System.out.println("Now you have " + items.size() + " tasks in the list.");
                    }
                    saveTasksToFile();
                } else if (input.startsWith("event")) {
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");

                    // Check if the format of the input is correct
                    if (fromIndex == -1 || toIndex == -1 || fromIndex + 6 >= input.length()
                            || toIndex + 4 >= input.length()) {
                        System.out.println(
                                "Please enter the event in the format: event <event description> /from <start time> /to <end time>");
                    } else {
                        String description = input.substring(6, fromIndex).trim();
                        String start = input.substring(fromIndex + 6, toIndex).trim();
                        String end = input.substring(toIndex + 4).trim();

                        // Create a new Event object and add it to the list
                        Event newEvent = new Event(description, start, end);
                        items.add(newEvent);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newEvent);
                        System.out.println("Now you have " + items.size() + " tasks in the list.");
                    }
                    saveTasksToFile();
                } else if (input.startsWith("delete")) {
                    try {
                        int index = Integer.parseInt(input.substring(7).trim()) - 1; 
                        if (index >= 0 && index < items.size()) {
                            Task removedTask = items.remove(index); // Remove the task and retrieve it for printing
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println(removedTask);
                            System.out.println("Now you have " + items.size() + " tasks in the list.");
                            saveTasksToFile(); 
                        } else {
                            System.out.println("Invalid task number. Please enter a valid task number to delete.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number after 'delete'.");
                    }
                } else if (!input.equals("bye")) {
                    throw new SayoException("Oh no! Apologies, but I don't know what that means :-( Please retry.");
                }
            } catch (SayoException e) {
                System.out.println(e.getMessage());
            }

        } while (!input.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void addToDo(String input, ArrayList<Task> items) throws SayoException {
        if (input.length() <= 5) {
            throw new SayoException("Please focus! The description of a todo cannot be empty. Please retry...");
        }
        String description = input.substring(5).trim(); // Use trim() to remove any leading/trailing whitespace
        ToDo newTodo = new ToDo(description);
        items.add(newTodo); // Add the new ToDo object to the ArrayList
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTodo); // Assuming ToDo's toString() method formats it correctly
        System.out.println("Now you have " + items.size() + " tasks in the list.");
    }

    private static void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(dataFilePath)) {
            for (Task task : items) {
                writer.println(task.toFileFormat());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while saving tasks to file.");
            e.printStackTrace();
        }
    }

    private static void loadTasksFromFile() {
        try {
            Path path = Paths.get(dataFilePath);
            if (!Files.exists(path)) {
                // Create the directory and/or file if it does not exist
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                    case "T":
                        task = ToDo.fromFileFormat(line);
                        break;
                    case "D":
                        task = Deadline.fromFileFormat(line);
                        break;
                    case "E":
                        task = Event.fromFileFormat(line);
                        break;
                    default:
                        throw new SayoException("Unknown task type in file: " + parts[0]);
                }
                items.add(task);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while loading tasks from file.");
            e.printStackTrace();
        }
    }

}
import jason.tasks.Task;
import jason.tasks.Todo;
import jason.tasks.Deadline;
import jason.tasks.Events;

import jason.errorhandling.JasonException;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * Represents the main class for the application Duke.
 */
public class Jason {
    private static ArrayList<Task> list = new ArrayList<>();
    private static final String LIST_PATH = "jasonTaskList.txt";

    private static void markTask(String[] input) throws JasonException {
        try {
            int taskNumber = Integer.parseInt(input[1]) - 1;
            if (taskNumber < 0 || taskNumber >= list.size()) {
                throw new JasonException("That task number is not valid. The list only has " + list.size() + " task(s) currently");
            }
            list.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(taskNumber));
        } catch (NumberFormatException e) {
            throw new JasonException("Please enter a valid task number.");
        }
    }

    private static void unmarkTask(String[] input) throws JasonException {
        try {
            int taskNumber = Integer.parseInt(input[1]) - 1;
            if (taskNumber < 0 || taskNumber >= list.size()) {
                throw new JasonException("That task number is not valid. The list only has " + list.size() + " task(s) currently");
            }

            list.get(taskNumber).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list.get(taskNumber));
        } catch (NumberFormatException e) {
            throw new JasonException("Please enter a valid task number.");
        }
    }


    private static void showTaskNumber() {
        if (list.size() == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.println("Now you have " + list.size() + " tasks in the list");
        }

    }

    private static void showList() throws JasonException {
        if (list.isEmpty()) {
            throw new JasonException("The task list is currently empty. Add some tasks!");
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ":" + list.get(i));
        }
    }

    private static void todoTasks(String task) throws JasonException {
        String taskDescription = task.substring(5).trim(); // trim() removes leading and trailing spaces
        if (taskDescription.isEmpty()) {
            throw new JasonException("The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(taskDescription);
        list.add(newTodo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo);
        showTaskNumber();
    }

    private static void deleteTask(String[] input) throws JasonException {
        try {
            int taskNumber = Integer.parseInt(input[1]) - 1;
            if (!(taskNumber > 0) || !(taskNumber <= list.size())) {
                throw new JasonException("That task number is not valid. The list only has " + list.size() + " task(s) currently");
            }
            Task removedTask = list.remove(taskNumber);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            showTaskNumber();
        } catch (NumberFormatException e) {
            throw new JasonException("Please enter a valid task number.");
        }
    }
    private static void deadlineTasks(String task) throws JasonException {
        if (!task.contains("/by")) {
            throw new JasonException("Invalid task. Please type in the format: description /by (date/time)");
        }
        String[] parts = task.split("/by", 2);
        String taskDescription = parts[0].substring(9).trim();
        String taskDeadlineBy = parts[1].trim();

        if (taskDescription.isEmpty() || taskDeadlineBy.isEmpty()) {
            throw new JasonException("Invalid task. Description and date/time cannot be empty.");
        }
        Deadline newDeadline = new Deadline(taskDescription, taskDeadlineBy);
        list.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        showTaskNumber();
    }



    private static void eventTasks(String task) throws JasonException {
        if (!task.contains("/from") || !task.contains("/to")) {
            throw new JasonException("Invalid task. Please type in the format: Description /from (date/time) /to (date/time).");
        }

        String[] parts = task.split("/from", 2);
        String taskDescription = parts[0].substring(6).trim();
        if (taskDescription.isEmpty()) {
            throw new JasonException("The description of an event cannot be empty.");
        }
        String[] eventTimeline = parts[1].trim().split("/to", 2);
        if (eventTimeline.length < 2 || eventTimeline[0].trim().isEmpty() || eventTimeline[1].trim().isEmpty()) {
            throw new JasonException("Invalid event times. Please type in the format: description /from (date/time) /to (date/time).");
        }
        String eventStartFrom = eventTimeline[0].trim();
        String eventTill = eventTimeline[1].trim();
        Events newEvent = new Events(taskDescription, eventStartFrom, eventTill);
        list.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        showTaskNumber();
    }





    private static void addTasks(String input) throws JasonException {

        try {
            if (input.startsWith("todo")) {
                todoTasks(input);
            } else if (input.startsWith("deadline")) {
                deadlineTasks(input);
            } else if (input.startsWith("event")) {
                eventTasks(input);
            } else {
                throw new JasonException("Invalid input. Please enter a valid command");
            }
        } catch (JasonException e) {
            System.out.println(e.getMessage());
        }

    }


    private static void saveTasks() {
        try (FileWriter writer = new FileWriter(LIST_PATH)) {
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        File file = new File(LIST_PATH);
        if (!file.exists()) {
            System.out.println("No existing data file found. Starting with an empty task list.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = null;
                if (line.startsWith("[T]")) {
                    task = Todo.parseFromFile(line);
                } else if (line.startsWith("[D]")) {
                    task = Deadline.parseFromFile(line);
                } else if (line.startsWith("[E]")) {
                    task = Events.parseFromFile(line);
                }



            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found: " + e.getMessage());
        } catch (Exception e) { // Catch any other parsing errors
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Main entry point of the Chatbot.
     * Initializes the application and starts the interaction with the user.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        loadTasks();
        String logo =
                "   J     A     SSSS    OOO   N   N \n"
                        + "   J    A A    S      O   O  NN  N \n"
                        + "   J   A A A    SSS   O   O  N N N \n"
                        + "J  J  A     A      S  O   O  N  NN \n"
                        + " JJJ A       A  SSSS   OOO   N   N \n" +
                        "                 \n";
        System.out.println(logo + " Eyy wassup I'm Jason\r\n"
                + " What can I do for you?\r\n"
        );

        Scanner scanner = new Scanner(System.in);
        String input;
        

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                // User wants to exit
                saveTasks();
                System.out.println("Bye. See ya laterr!\r\n"
                        + "____________________________________________________________\r\n"
                        + "\n" + logo);
                break;
            }

            try {
                // Split the input and check for commands
                String[] parts = input.split(" ");
                if (parts[0].equalsIgnoreCase("mark")) {
                    // Mark task as done
                    markTask(parts);
                } else if (parts[0].equalsIgnoreCase("unmark")) {
                    // Mark task as not done
                    unmarkTask(parts);
                } else if (parts[0].equalsIgnoreCase("list")) {
                    // List all tasks
                    showList();
                } else if (parts[0].equalsIgnoreCase("delete")) {
                    //delete task
                    deleteTask(parts);
                } else {
                    // Add new task
                    addTasks(input);
                }
                saveTasks();
            } catch (JasonException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}

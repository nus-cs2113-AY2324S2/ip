package cody;

import cody.tasks.Deadline;
import cody.tasks.Event;
import cody.tasks.Task;
import cody.tasks.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class TaskManager {
    ArrayList<Task> tasks;
    private static final String BORDER = "______________________________________________________________\n";

    private void printList() {
        System.out.print(BORDER + " Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(" " + (i + 1) + ". [" + task.getTaskType() + "] "
                    + "[" + task.getStatusIcon() + "] "
                    + task.getDescription());
        }
        System.out.print(BORDER);
    }

    private void markTask(int index) {
        System.out.print(BORDER + " Good job! I've marked this task as done:\n"
                + " [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getDescription() + "\n"
                + BORDER);
    }

    private void handleMarking(String input) {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            boolean isDone = input.startsWith("mark");
            tasks.get(index).markTask(isDone);
            markTask(index);
        } catch (NumberFormatException e) {
            System.out.print(BORDER + " Task number is invalid\n"
                    + " Please enter a valid number\n" + BORDER);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(BORDER + " Task number is out of range\n"
                    + " Please enter a number between 1 and " + tasks.size() + "\n" + BORDER);
        }
    }

    private void addTask(String input) {
        try {
            Task task = createTaskFromInput(input);
            tasks.add(task);
            printTask(task);
        } catch (CodyException e) {
            System.out.print(BORDER + e.getMessage() + BORDER);
        }
    }

    private Task createTaskFromInput(String input) throws CodyException {
        if (input.startsWith("todo")) {
            return createTodoTask(input);
        } else if (input.startsWith("deadline")) {
            return createDeadlineTask(input);
        } else if (input.startsWith("event")) {
            return createEventTask(input);
        } else {
            // If the task type is unknown, throw a CodyException
            throw new CodyException(" I'm not sure what task this is\n"
                    + " Please start with 'todo', 'event' or 'deadline'\n");
        }
    }


    // Creates a cody.tasks.Todo task from the input.
    private Todo createTodoTask(String input) throws CodyException {
        if (input.length() <= 5) {
            throw new CodyException(" The description of a todo cannot be empty\n"
                    + " Please use 'todo <description>'\n");
        }

        String description = input.substring(5).trim(); // Removing 'todo ' prefix.
        return new Todo(description);
    }

    // Creates a cody.tasks.Deadline task from the input.
    private Deadline createDeadlineTask(String input) throws CodyException {
        if (input.length() <= 9) {
            throw new CodyException(" The description of a deadline cannot be empty\n"
                    + " Please use 'deadline <description> /by <deadline>'\n");
        }

        String[] deadlineDetails = input.split(" /by ", 2);
        String description = deadlineDetails[0].substring(9).trim(); // Removing 'deadline ' prefix.
        String by = deadlineDetails.length > 1 ? deadlineDetails[1] : "No deadline specified";
        return new Deadline(description, by);
    }

    // Creates an cody.tasks.Event task from the input.
    private Event createEventTask(String input) throws CodyException {
        if (input.length() <= 6) {
            throw new CodyException(" The description of an event cannot be empty\n"
                    + " Please use 'event <description> /from <start time> /to <end time>'\n");
        }

        String[] eventDetails = input.split(" /from | /to ");
        String description = eventDetails[0].substring(6).trim(); // Removing 'event ' prefix.
        String from = (eventDetails.length > 1) ? eventDetails[1] : "No start time specified";
        String to = (eventDetails.length > 2) ? eventDetails[2] : "No end time specified";
        return new Event(description, from, to);
    }


    private void printTask(Task task) {
        System.out.print(BORDER + " Got it. I've added this task:\n"
                + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n"
                + BORDER);
    }

    private void deleteTask(String input) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        try {
            Task task = tasks.get(index);
            tasks.remove(index);
            printDeleteTask(task);
        } catch (NumberFormatException e) {
            System.out.print(" Task number is invalid\n"
                    + " Please enter a valid number\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.print(" Task number is out of range\n"
                    + " Please enter a number between 1 and " + tasks.size() + "\n");
        }
    }

    private void printDeleteTask(Task task) {
        System.out.print(BORDER + " Noted. I've removed this task:\n"
                + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n"
                + BORDER);
    }

    public void loadTasksFromFile(String filePath) {
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                Task task = null;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    String[] deadlineParts = description.split(" \\(by: ");
                    String deadlineDescription = deadlineParts[0].trim();
                    String by = deadlineParts.length > 1 ? deadlineParts[1].replace(")", "").trim() : "No deadline specified";
                    task = new Deadline(deadlineDescription, by);
                    break;
                case "E":
                    String[] eventParts = description.split(" \\(from: | to: ");
                    String eventDescription = eventParts[0].trim();
                    String from = eventParts.length > 1 ? eventParts[1].trim() : "No start time specified";
                    String to = eventParts.length > 2 ? eventParts[2].replace(")", "").trim() : "No end time specified";
                    task = new Event(eventDescription, from, to);
                    break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markTask(true);
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(" No saved tasks found");
        }
    }

    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasksFromFile("./data/tasks.txt");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                handleMarking(input);
            } else {
                addTask(input);
            }
            input = in.nextLine();
        }
        Save.saveTasks(tasks);
    }
}
    
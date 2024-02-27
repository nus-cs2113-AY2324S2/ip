package cody;

import cody.tasks.Deadline;
import cody.tasks.Event;
import cody.tasks.Task;
import cody.tasks.Todo;

import java.util.Scanner;
import java.util.ArrayList;


public class TaskManager {
    ArrayList<Task> tasks;

    private void printList() {
        String listString = " Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            listString += " " + (i + 1) + ". [" + task.getTaskType() + "] "
                    + "[" + task.getStatusIcon() + "] "
                    + task.getDescription() + "\n";
        }
        Ui.printMessage(listString);
    }

    private void markTask(int index) {
        Ui.printMessage(" Good job! I've marked this task as done:\n"
                + " [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getDescription() + "\n");
    }

    private void handleMarking(String input) {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            boolean isDone = input.startsWith("mark");
            tasks.get(index).markTask(isDone);
            markTask(index);
        } catch (NumberFormatException e) {
            System.err.println(" Task number is invalid. Please enter a valid number");
        } catch (IndexOutOfBoundsException e) {
            System.err.println(" Task number is out of range. Please enter a number between 1 and " + tasks.size());
        }
    }

    private void addTask(String input) {
        try {
            Task task = createTaskFromInput(input);
            tasks.add(task);
            printTask(task);
        } catch (CodyException e) {
            System.err.println(e.getMessage());
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
            throw new CodyException(" Invalid command");
        }
    }


    // Creates a cody.tasks.Todo task from the input.
    private Todo createTodoTask(String input) throws CodyException {
        if (input.length() <= 5) {
            throw new CodyException(" The description of a todo cannot be empty\n"
                    + " Please use 'todo <description>'");
        }

        String description = input.substring(5).trim(); // Removing 'todo ' prefix.
        return new Todo(description);
    }

    // Creates a cody.tasks.Deadline task from the input.
    private Deadline createDeadlineTask(String input) throws CodyException {
        if (input.length() <= 9) {
            throw new CodyException(" The description of a deadline cannot be empty\n"
                    + " Please use 'deadline <description> /by <deadline>'");
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
                    + " Please use 'event <description> /from <start time> /to <end time>'");
        }

        String[] eventDetails = input.split(" /from | /to ");
        String description = eventDetails[0].substring(6).trim(); // Removing 'event ' prefix.
        String from = (eventDetails.length > 1) ? eventDetails[1] : "No start time specified";
        String to = (eventDetails.length > 2) ? eventDetails[2] : "No end time specified";
        return new Event(description, from, to);
    }


    private void printTask(Task task) {
        Ui.printMessage(" Got it. I've added this task:\n"
                + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n");
    }

    private void deleteTask(String input) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        try {
            Task task = tasks.get(index);
            tasks.remove(index);
            printDeleteTask(task);
        } catch (NumberFormatException e) {
            System.err.println(" Task number is invalid. Please enter a valid number");
        } catch (IndexOutOfBoundsException e) {
            System.err.println(" Task number is out of range. Please enter a number between 1 and " + tasks.size());
        }
    }

    private void printDeleteTask(Task task) {
        Ui.printMessage(" Noted. I've removed this task:\n"
                + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n");
    }



    public TaskManager() {
        tasks = new ArrayList<>();
        Storage.loadTasksFromFile(tasks);
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
        Storage.saveTasks(tasks);
    }
}
    
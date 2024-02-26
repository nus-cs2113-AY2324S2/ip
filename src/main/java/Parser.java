import task.*;

import java.util.ArrayList;

public class Parser {

    static int taskIndex;

    public static void parseCommand(String userInput, TaskList tasks) throws DukeException {
        if (userInput.equalsIgnoreCase("list")) {
            listTasks(tasks);
        } else if (userInput.startsWith("mark")) {
            markTask(userInput, tasks);
        } else if (userInput.startsWith("unmark")) {
            unmarkTask(userInput, tasks);
        } else if (userInput.startsWith("todo")) {
            addTodoTask(userInput, tasks);
        } else if (userInput.startsWith("deadline")) {
            addDeadlineTask(userInput, tasks);
        } else if (userInput.startsWith("event")) {
            addEventTask(userInput, tasks);
        } else if (userInput.startsWith("delete")) {
            deleteTask(userInput, tasks);
        } else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :(");
            throw new DukeException();
        }
    }

    private static void listTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void markTask(String userInput, TaskList tasks) throws DukeException {
        taskIndex = DukeException.getTaskIndex(userInput.split(" ")[1], tasks);
        tasks.get(taskIndex).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskIndex));
    }

    private static void unmarkTask(String userInput, ArrayList<Task> tasks) throws DukeException {
        taskIndex = DukeException.getTaskIndex(userInput.split(" ")[1], tasks);
        tasks.get(taskIndex).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(taskIndex));
    }

    private static void addTodoTask(String userInput, ArrayList<Task> tasks) throws DukeException {
        DukeException.checkDescription(userInput);
        String description = userInput.substring(5).trim();
        tasks.add(new ToDo(description));

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(String userInput, ArrayList<Task> tasks) throws DukeException {
        DukeException.checkDescription(userInput);
        String[] parts = userInput.substring(9).split("/by ");
        if (parts.length != 2) {
            System.out.println("OOPS!!! Please enter a valid deadline format.");
            throw new DukeException();
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        tasks.add(new Deadline(description, by));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEventTask(String userInput, ArrayList<Task> tasks) throws DukeException {
        DukeException.checkDescription(userInput);
        String[] parts = userInput.substring(6).split("/from |/to ");
        if (parts.length != 3) {
            System.out.println("OOPS!!! Please enter a valid event format.");
            throw new DukeException();
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        tasks.add(new Event(description, from, to));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void deleteTask(String userInput, ArrayList<Task> tasks) throws DukeException {
        taskIndex = DukeException.getTaskIndex(userInput.split(" ")[1], tasks);
        Task removedTask = tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}

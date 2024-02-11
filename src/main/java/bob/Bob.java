package bob;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {

        displayWelcomeMessage();
        List<Task> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        while (true) {

            String line = in.nextLine();
            String command = line.split(" ")[0];

            try {
                boolean endLoop = processCommand(command, line, list);

                if (endLoop) {
                    break;
                }
            } catch (DukeException e) {
                displayHorizontalLine();
                System.out.println(e.getMessage());
                displayHorizontalLine();
            }

        }
    }

    private static boolean processCommand(String command, String line, List<Task> list) throws DukeException {
        switch (command) {
        case "todo":
            addTodo(line, list);
            break;
        case "deadline":
            addDeadline(line, list);
            break;
        case "event":
            addEvent(line, list);
            break;
        case "mark":
            markTask(line, list);
            break;
        case "unmark":
            unmarkTask(line, list);
            break;
        case "list":
            displayList(list);
            break;
        case "bye":
            displayExitMessage();
            return true;
        default:
            throw new DukeException("sI'm sorry, but I don't know what that means :-(");
        }
        return false;
    }

    private static void unmarkTask(String line, List<Task> list) {
        String content;
        content = line.split(" ", 2)[1];
        list.get(Integer.parseInt(content) - 1).setDone(false);
        displayHorizontalLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(Integer.parseInt(content) - 1).getListItem());
        displayHorizontalLine();
    }

    private static void markTask(String line, List<Task> list) {
        String content;
        content = line.split(" ", 2)[1];
        list.get(Integer.parseInt(content) - 1).setDone(true);
        displayHorizontalLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(list.get(Integer.parseInt(content) - 1).getListItem());
        displayHorizontalLine();
    }

    private static void addEvent(String line, List<Task> list) throws DukeException {
        String content, description, start, by;

        try {
            content = line.split(" ", 2)[1];

            description = content.split( " /from ")[0];
            start = content.split( " /from ")[1].split(" /to ")[0];
            by = content.split(" /to ")[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("An event must have a description, a start time, and an end time.");
        }

        Event e = new Event(description, start, by);
        list.add(e);

        displayHorizontalLine();
        System.out.println("Got it. I've added this event: ");
        System.out.println(e);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        displayHorizontalLine();
    }

    private static void addDeadline(String line, List<Task> list) throws DukeException {
        String content;
        try {
            content = line.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("A deadline must have a description and a deadline.");
        }

        Deadline d = new Deadline(content.split( " /by ")[0], content.split( " /by ")[1]);
        list.add(d);

        displayHorizontalLine();
        System.out.println("Got it. I've added this deadline: ");
        System.out.println(d);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        displayHorizontalLine();
    }

    private static void addTodo(String line, List<Task> list) throws DukeException {
        String content;
        try {
            content = line.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Task t = new Task(content);
        list.add(t);

        displayHorizontalLine();
        System.out.println("Got it. I've added this todo: ");
        System.out.println(t);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        displayHorizontalLine();
    }

    private static void displayList(List<Task> list) {
        displayHorizontalLine();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + "." + list.get(i).toString());
        }

        displayHorizontalLine();
    }

    private static void displayWelcomeMessage() {
        String logo = " ____       _        \n"
                    + "|  _ \\_____|_|__ _\n"
                    + "| |_| | /\\ | |    \\ \n"
                    + "| |_| | \\/ | |  O /\n"
                    + "|____/ \\__,|_|\\__/\n";
        System.out.println("Hello from\n" + logo);

        displayHorizontalLine();
        System.out.println("Hello! I'm bob.Bob");
        System.out.println("What can I do for you?");
        displayHorizontalLine();
    }

    public static void displayExitMessage() {
        displayHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayHorizontalLine();
    }

    private static void displayHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
}

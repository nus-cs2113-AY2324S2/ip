package bob;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bob {

    public static final String logo = " ____       _        \n"
                                    + "|  _ \\_____|_|__ _\n"
                                    + "| |_| | /\\ | |    \\ \n"
                                    + "| |_| | \\/ | |  O /\n"
                                    + "|____/ \\__,|_|\\__/\n";
    public static String FILENAME = "bob.txt";
    public static void main(String[] args) {



        displayWelcomeMessage();
        List<Task> list = generateListOnStartup(FILENAME);

        Scanner in = new Scanner(System.in);

        while (true) {

            String line = in.nextLine();
            String command = line.split(" ")[0];

            try {
                boolean endLoop = processCommand(command, line, list, FILENAME);

                if (endLoop) {
                    break;
                }
            } catch (BobException e) {
                displayHorizontalLine();
                System.out.println(e.getMessage());
                displayHorizontalLine();
            }

        }
    }

    public static final String TODO_ICON = "[T]";
    public static final String DEADLINE_ICON = "[D]";
    public static final String EVENT_ICON = "[E]";

    private static List<Task> generateListOnStartup(String filename) {
        List<String> stringList;
        try {
            stringList = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            /* if there is any problem with the file or the file does
               not exist, return an empty list. this means if the file is
               corrupted it will not be used, and overwritten later */
            return new ArrayList<>();
        }

        List<Task> list = new ArrayList<>();
        for (String s : stringList) {
            switch (s.substring(0, 3)) {
            case TODO_ICON:
                list.add(new Task(s));
                break;
            case DEADLINE_ICON:
                list.add(new Deadline(s));
                break;
            case EVENT_ICON:
                list.add(new Event(s));
                break;
            }
        }

        return list;
    }

    public enum Command {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, SAVE, BYE
    }
    private static boolean processCommand(String command, String line, List<Task> list, String filename) throws BobException {
        switch (Command.valueOf(command.toUpperCase())) {
        case TODO:
            addTodo(line, list);
            break;
        case DEADLINE:
            addDeadline(line, list);
            break;
        case EVENT:
            addEvent(line, list);
            break;
        case MARK:
            markTask(line, list);
            break;
        case UNMARK:
            unmarkTask(line, list);
            break;
        case LIST:
            displayList(list);
            break;
        case DELETE:
            deleteTask(line, list);
            break;
        case SAVE:
            saveList(filename, list);
            break;
        case BYE:
            displayExitMessage();
            return true;
        default:
            throw new BobException("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }

    private static void deleteTask(String line, List<Task> list) {
        String content;
        content = line.split(" ", 2)[1];
        displayHorizontalLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(list.get(Integer.parseInt(content) - 1).getListItem());
        list.remove(Integer.parseInt(content) - 1);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        displayHorizontalLine();
    }

    private static void saveList(String filename, List<Task> list) throws BobException {

        // convert list of tasks objects to list of strings
        List<String> stringList = list.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        Path file = Paths.get(filename);

        try {
            Files.write(file, stringList, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new BobException("Some problem with your computer, could not save list.");
        }

        displayHorizontalLine();
        System.out.println("Your list has been saved to " + filename + ".");
        displayHorizontalLine();
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

    private static void addEvent(String line, List<Task> list) throws BobException {
        String content, description, start, by;

        try {
            content = line.split(" ", 2)[1];

            description = content.split(" /from ")[0];
            start = content.split(" /from ")[1].split(" /to ")[0];
            by = content.split(" /to ")[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BobException("An event must have a description, a start time, and an end time.");
        }

        Event e = new Event(description, start, by, false);
        list.add(e);

        displayHorizontalLine();
        System.out.println("Got it. I've added this event: ");
        System.out.println(e);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        displayHorizontalLine();
    }

    private static void addDeadline(String line, List<Task> list) throws BobException {
        String content;
        try {
            content = line.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BobException("A deadline must have a description and a deadline.");
        }

        Deadline d = new Deadline(content.split(" /by ")[0], content.split(" /by ")[1], false);
        list.add(d);

        displayHorizontalLine();
        System.out.println("Got it. I've added this deadline: ");
        System.out.println(d);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        displayHorizontalLine();
    }

    private static void addTodo(String line, List<Task> list) throws BobException {
        String content;
        try {
            content = line.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BobException("The description of a todo cannot be empty.");
        }

        Task t = new Task(content, false);
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
            System.out.println((i + 1) + "." + list.get(i).toString());
        }

        displayHorizontalLine();
    }

    private static void displayWelcomeMessage() {

        System.out.println("Hello from\n" + logo);

        displayHorizontalLine();
        System.out.println("Hello! I'm Bob. Your personal task manager.");
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

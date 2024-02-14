package ip.main;

import java.util.Scanner;
import java.util.ArrayList;

import ip.task.Task;
import ip.task.Todo;
import ip.task.Deadline;
import ip.task.Event;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = "  ____   _   _      __     ______    _       _  _____\n" +
                " / ___| | | | |    /  \\    |  _  \\  | |     | ||  ___|\n" +
                "| |     | |_| |   / /\\ \\   | |_| /  | |     | || |___\n" +
                "| |     |  _  |  / /__\\ \\  |  __ \\  | |     | ||  ___|\n" +
                "| |___  | | | | / ______ \\ | |  \\ \\ | |____ | || |___\n" +
                " \\____| |_| |_|/_/      \\_\\|_|   \\_\\|______||_||_____|\n";

        System.out.println("Hello! I'm Charlie!\n" + logo);
        System.out.println("What can I do for you?");

        readInputAndExecute();
    }

    private static void readInputAndExecute() {
        Scanner in = new Scanner(System.in);
        String line;
        while (true) {
            line = in.nextLine();
            if (line.trim().equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            }

            if (line.trim().equals("list")) {
                printTaskList();
                continue;
            }

            try {
                if (line.startsWith("mark")) {
                    markTask(line);
                    continue;
                }

                if (line.startsWith("unmark")) {
                    unmarkTask(line);
                    continue;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("     Please provide a number from 1 to " + tasks.size());
                continue;
            } catch (NumberFormatException e) {
                System.out.println("     Please input the command in the form 'mark/unmark <integer>'");
                continue;
            }

            addTask(line);
        }
    }

    private static void addTask(String line) {
        if (line.startsWith("todo")) {
            try {
                tasks.add(new Todo(line));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("     Please input in the form 'todo <description>'");
                return;
            }
        } else if (line.startsWith("deadline")) {
            try {
                tasks.add(new Deadline(line));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("     Please input in the form 'deadline <description> /by <when>'");
                return;
            }
        } else if (line.startsWith("event")) {
            try {
                tasks.add(new Event(line));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("     Please input in the form 'event <description> /from <when> /to <when>'");
                return;
            }
        } else {
            System.out.println("     Possible commands: bye, list, mark, unmark, todo, deadline, event");
            return;
        }

        System.out.println("     New task added: " + tasks.get(tasks.size() - 1).getDetails());
        System.out.println("     Current number of tasks: " + tasks.size());
    }

    private static void printTaskList() {
        System.out.println("     Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i).getDetails());
        }
    }

    private static void markTask(String line) {
        int indexMarked = Integer.parseInt(line.substring(5)) - 1;
        tasks.get(indexMarked).setDone(true);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks.get(indexMarked).getDetails());
    }

    private static void unmarkTask(String line) {
        int indexUnmarked = Integer.parseInt(line.substring(7)) - 1;
        tasks.get(indexUnmarked).setDone(false);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + tasks.get(indexUnmarked).getDetails());
    }
}

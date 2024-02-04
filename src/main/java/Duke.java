import java.util.Scanner;

public class Duke {
    private static final int MAX_SIZE = 100;
    private static Task[] tasks = new Task[MAX_SIZE];
    private static int numberOfTasks = 0;

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
            if (line.contains("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            }

            if (line.startsWith("list")) {
                printTaskList();
                continue;
            }

            if (line.startsWith("mark")) {
                markTask(line);
                continue;
            }

            if (line.startsWith("unmark")) {
                unmarkTask(line);
                continue;
            }

            addTask(line);
        }
    }

    private static void addTask(String line) {
        if (line.startsWith("todo")) {
            tasks[numberOfTasks] = new Todo(line);
        } else if (line.startsWith("deadline")) {
            tasks[numberOfTasks] = new Deadline(line);
        } else if (line.startsWith("event")) {
            tasks[numberOfTasks] = new Event(line);
        }

        try {
            System.out.println("     New task added: " + tasks[numberOfTasks].getDetails());
            numberOfTasks++;
            System.out.println("     Current number of tasks: " + numberOfTasks);
        } catch (NullPointerException e) {
            System.out.println("     Possible commands: bye, list, mark, unmark, todo, deadline, event");
        }
    }

    private static void printTaskList() {
        System.out.println("     Here is your list of tasks:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i].getDetails());
        }
    }

    private static void markTask(String line) {
        int indexMarked = Integer.parseInt(line.substring(5)) - 1;
        tasks[indexMarked].setDone(true);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks[indexMarked].getDetails());
    }

    private static void unmarkTask(String line) {
        int indexUnmarked = Integer.parseInt(line.substring(7)) - 1;
        tasks[indexUnmarked].setDone(false);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + tasks[indexUnmarked].getDetails());
    }
}

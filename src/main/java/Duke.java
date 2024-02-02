import java.util.Scanner;

public class Duke {
    private static void printTaskList(Task[] tasks, int numberOfTasks) {
        System.out.println("     Here is your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i].getDetails());
        }
    }

    private static void modifyTaskList(Task[] tasks, String line) {
        if (line.startsWith("mark")) {
            int indexMarked = Integer.parseInt(line.substring(5)) - 1;
            tasks[indexMarked].isDone = true;
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + tasks[indexMarked].getDetails());
        } else if (line.startsWith("unmark")) {
            int indexUnmarked = Integer.parseInt(line.substring(7)) - 1;
            tasks[indexUnmarked].isDone = false;
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + tasks[indexUnmarked].getDetails());
        }
    }

    public static void main(String[] args) {
        String logo = "  ____   _   _      __     ______    _       _  _____\n" +
                " / ___| | | | |    /  \\    |  _  \\  | |     | ||  ___|\n" +
                "| |     | |_| |   / /\\ \\   | |_| /  | |     | || |___\n" +
                "| |     |  _  |  / /__\\ \\  |  __ \\  | |     | ||  ___|\n" +
                "| |___  | | | | / ______ \\ | |  \\ \\ | |____ | || |___\n" +
                " \\____| |_| |_|/_/      \\_\\|_|   \\_\\|______||_||_____|\n";

        System.out.println("Hello! I'm Charlie!\n" + logo);
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String line;

        final int MAX_SIZE = 100;
        Task[] tasks = new Task[MAX_SIZE];
        int numberOfTasks = 0;

        while (true) {
            line = in.nextLine();
            if (line.contains("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            }

            if (line.startsWith("list")) {
                printTaskList(tasks, numberOfTasks);
                continue;
            }

            if (line.startsWith("mark") || line.startsWith("unmark")) {
                modifyTaskList(tasks, line);
                continue;
            }

            tasks[numberOfTasks] = new Task(line);
            numberOfTasks++;
            System.out.println("     added: " + line);
        }
    }
}

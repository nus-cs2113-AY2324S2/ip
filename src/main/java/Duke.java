import java.util.Scanner;

public class Duke {
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
        Task[] tasks = new Task[100];
        int numberOfTasks = 0;

        while (true) {
            line = in.nextLine();
            if (line.contains("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            } else if (line.startsWith("list")) {
                System.out.println("     Here is your list:");
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println("     " + (i + 1) + ".[" +
                            tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
            } else if (line.startsWith("mark")) {
                int indexMarked = Integer.parseInt(line.substring(5)) - 1;
                tasks[indexMarked].isDone = true;
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [X] " + tasks[indexMarked].description);
            } else if (line.startsWith("unmark")) {
                int indexUnmarked = Integer.parseInt(line.substring(7)) - 1;
                tasks[indexUnmarked].isDone = false;
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       [ ] " + tasks[indexUnmarked].description);
            } else {
                tasks[numberOfTasks] = new Task(line);
                numberOfTasks++;
                System.out.println("     added: " + line);
            }
        }
    }
}

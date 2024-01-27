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
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i].description);
                }
            } else {
                tasks[numberOfTasks] = new Task(line);
                numberOfTasks++;
                System.out.println("     added: " + line);
            }
        }
    }
}

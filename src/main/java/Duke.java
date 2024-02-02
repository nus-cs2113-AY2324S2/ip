import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "George";
        System.out.println("Hello! I'm " + name);
//        System.out.println("What can I do for you?");
        String[] list = new String[100];
        int taskCounter = 0;

        Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("I can keep track of a to-do list for you! Just type what you want to add to the list.\nType 'list' to see what is in the list so far.\nType 'bye' to exit.");
        while (!"bye".equals((line = scanner.nextLine()))) {
            if ("list".equals(line)) {
                System.out.println("List so far: ");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
            } else {
                list[taskCounter] = line;
                taskCounter++;
                System.out.println("Added: " + line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

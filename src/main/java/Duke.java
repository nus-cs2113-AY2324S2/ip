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

        Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("Say anything and I shall repeat it. Type 'bye' to exit.");
        while (!"bye".equals((line = scanner.nextLine()))) {
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

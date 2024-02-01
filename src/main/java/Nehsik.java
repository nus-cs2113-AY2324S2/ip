import java.util.Scanner;
public class Nehsik {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printLine();
        System.out.println("Hello! I'm Nehsik");
        System.out.println("What can I do for you?");
        printLine();
        String command;
        Scanner in = new Scanner(System.in);
        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            printLine();
            System.out.println(command);
            printLine();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

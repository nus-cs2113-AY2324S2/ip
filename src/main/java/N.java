import java.util.Scanner;
public class N {

    public static void printLine() {
        System.out.println("    ____________________________________________________________ \n");
    }

    public static void printMessage(String message) {
        printLine();
        System.out.println("    " +message);
        printLine();
    }

    public static void echo() {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();
        if (message.equalsIgnoreCase("bye")) {
            printMessage("Bye. Hope to see you again soon!");
        } else {
            printMessage(message);
            echo();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();
        System.out.println("    Hello! I'm N :) \n" + "    What can I do for you? \n");
        printLine();

        echo();
    }
}

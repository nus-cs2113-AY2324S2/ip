/*
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}

 */

import java.util.Scanner;
public class Duke {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        String line = " ";
        Scanner in = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Doraemon!");
        System.out.println("What can I do for you?");
        printLine();
        while (!line.equals("bye")) {
            line = in.nextLine();
            System.out.println(line);
            printLine();
        }
        System.out.println("Bye. Have a great day!");
        printLine();
    }
}

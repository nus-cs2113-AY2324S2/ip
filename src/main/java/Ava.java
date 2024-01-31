import java.util.Scanner;

public class Ava {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        greet();
        echo();
        exit();
    }

    public static void echo() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        if (command.equals("bye")) {
            return;
        }
        while (!command.equals("bye")) {
            printLine();
            System.out.println(command);
            printLine();
            command = in.nextLine();
        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        printLine();
        System.out.println(" Hello!!! AvavaAVA!!! Here is Ava!!!");
        System.out.println(" Let's have a relaxing and happy chat together!!!");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println(" Bye!!! Hope to see you again soon!!!");
        printLine();
    }
}

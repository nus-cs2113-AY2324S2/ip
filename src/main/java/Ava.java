import java.util.Scanner;

public class Ava {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        greet();
        addCommand();
        exit();
    }

    public static void addCommand() {
        String[] commands = new String[100];
        Scanner in = new Scanner(System.in);
        int commandCount = 0;
        commands[commandCount] = in.nextLine();
        if (commands[commandCount].equals("bye")) {
            return;
        }
        while (!commands[commandCount].equals("bye")) {
            if (commands[commandCount].equals("list")) {
                commandCount += 1;
                printLine();
                listCommand(commands, commandCount);
                printLine();
            } else {
                printLine();
                System.out.println("added: " + commands[commandCount]);
                printLine();
                commandCount += 1;
            }
            commands[commandCount] = in.nextLine();
        }
    }

    public static void listCommand(String[] commands, int commandCount) {
        int noOfCommand = 1;
        int listedCommands = 0;
        while (noOfCommand < commandCount) {
            if (!commands[noOfCommand - 1].equals("list")) {
                System.out.println((listedCommands + 1) + ". " + commands[noOfCommand - 1]);
                listedCommands += 1;
            }
            noOfCommand += 1;
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

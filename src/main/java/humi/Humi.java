package humi;

import java.util.Scanner;

public class Humi {
    public static final String LINE = "    ____________________________________________________________";
    //    String logo = " __        _        \n"
    //                + "|  _ \\ _   | | __ \n"
    //                + "| | | | | | | |/ / _ \\\n"
    //                + "| || | || |   <  __/\n"
    //                + "|_/ \\,||\\\\_|\n";
    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("     Hello! I'm humi.Humi");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);

        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            taskManager.handleCommand(input);
            input = in.nextLine();
        }

        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}

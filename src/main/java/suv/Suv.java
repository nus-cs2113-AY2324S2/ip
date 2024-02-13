package suv;

import java.util.Scanner;


public class Suv {
    final static String LINE = "____________________________________________________________\n";

    public static void main(String[] args) {
        String name = "suv.Suv";
        Scanner in = new Scanner(System.in);

        System.out.println(LINE +
                " Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                LINE
        );

        TaskManager taskManager = new TaskManager();

        String input = in.nextLine();
        while(!input.equals("bye")) {
            taskManager.handleInput(input);
            input = in.nextLine();
        }
    }
}
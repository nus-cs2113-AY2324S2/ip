import java.util.Scanner;

public class Humi {
    public static final String LINE = "____________________________________________________________";
        //    String logo = " ____        _        \n"
        //                + "|  _ \\ _   _| | _____ \n"
        //                + "| | | | | | | |/ / _ \\\n"
        //                + "| |_| | |_| |   <  __/\n"
        //                + "|____/ \\__,_|_|\\_\\___|\n";
    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello! I'm Humi");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        String[] tasks = new String[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(tasks[i]);
                }
                System.out.println(LINE);
            }
            else {
                System.out.println(LINE);
                System.out.println("added: " + input);
                tasks[taskCount++] = input;
                System.out.println(LINE);
            }
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

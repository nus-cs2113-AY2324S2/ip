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
        Boolean[] isDone = new Boolean[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < taskCount; i++) {
                    String mark = (isDone[i]) ? "[X] " : "[ ] ";
                    System.out.println(mark + tasks[i]);
                }
                System.out.println(LINE);
            }
            else if (input.startsWith("mark")) {
                int taskNum = Integer.parseInt(input.substring(5));
                isDone[taskNum - 1] = true;
                System.out.println(LINE);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + tasks[taskNum - 1]);
                System.out.println(LINE);
            }
            else if (input.startsWith("unmark")) {
                int taskNum = Integer.parseInt(input.substring(7));
                isDone[taskNum - 1] = false;
                System.out.println(LINE);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[taskNum - 1]);
                System.out.println(LINE);
            }
            else {
                System.out.println(LINE);
                System.out.println("added: " + input);
                isDone[taskCount] = false;
                tasks[taskCount++] = input;
                System.out.println(LINE);
            }
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

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

        Task[] taskList = new Task[100];
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < Task.taskCount; i++) {
                    System.out.print(i+1 + ".");
                    taskList[i].print();
                }
                System.out.println(LINE);
            }
            else if (input.startsWith("mark")) {
                int taskNum = Integer.parseInt(input.substring(5));
                taskList[taskNum - 1].mark();
            }
            else if (input.startsWith("unmark")) {
                int taskNum = Integer.parseInt(input.substring(7));
                taskList[taskNum - 1].unmark();
            }
            else {
                taskList[Task.taskCount] = new Task(input);
            }
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

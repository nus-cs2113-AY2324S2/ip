import java.util.Scanner;
public class Duke {
    static String LINE = "____________________________________________________________";
    static String[] tasks = new String[100];
    static int taskCount = 0;
    public static void main(String[] args) {
        greetUser();
        addTask();
        byeUser();
    }
    private static void greetUser() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Bartholomew, but you can call me Bart for short :)");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
    private static void addTask() {
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            command = in.nextLine();
            if (command.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + "."  + tasks[i]);
                }
                System.out.println(LINE);
            } else {
                tasks[taskCount] = command;
                taskCount++;
                System.out.println(LINE);
                System.out.println("added: " + command);
                System.out.println(LINE);
            }
        }
    }

    private static void byeUser() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}

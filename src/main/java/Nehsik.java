import java.util.Scanner;
public class Nehsik {
    public static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        displayGreetings();
        
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[MAX_TASKS];
        int taskIndex = 0;

        while (true) {
            String command = in.nextLine();
            if (command.equals("list")) {
                printLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskIndex; i++) {
                    System.out.println((i + 1) + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
                }
                printLine();
            } else if (command.equals("bye")) {
                break;
            } else if (command.startsWith("mark")){
                printLine();
                System.out.println("Nice! I've marked this task as done:");
                int taskNum = Integer.parseInt(command.substring(5)) - 1;
                taskList[taskNum].markAsDone();
                System.out.println("[X] " + taskList[taskNum].getDescription());
                printLine();
            } else if (command.startsWith("unmark")) {
                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                int taskNum = Integer.parseInt(command.substring(7)) - 1;
                taskList[taskNum].markAsUndone();
                System.out.println("[ ] " + taskList[taskNum].getDescription());
                printLine();
            }  else {
                taskList[taskIndex] = new Task(command);
                printLine();
                System.out.println("added: " + command);
                printLine();
                taskIndex++;
            }
        }

        displayExitMessage();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void displayGreetings() {
        printLine();
        System.out.println("Hello! I'm Nehsik");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void displayExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}

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
                    System.out.println((i + 1) + "." + taskList[i].toString());
                }
                printLine();
            } else if (command.equals("bye")) {
                break;
            } else if (command.startsWith("mark")){
                printLine();
                System.out.println("Nice! I've marked this task as done:");
                int taskNum = Integer.parseInt(command.substring(5)) - 1;
                taskList[taskNum].markAsDone();
                System.out.println(taskList[taskNum].toString());
                printLine();
            } else if (command.startsWith("unmark")) {
                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                int taskNum = Integer.parseInt(command.substring(7)) - 1;
                taskList[taskNum].markAsUndone();
                System.out.println(taskList[taskNum].toString());
                printLine();
            }  else if (command.startsWith("todo")) {
                taskList[taskIndex] = new Todo(command);

                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + taskList[taskIndex].toString());
                System.out.println("Now you have " + (taskIndex + 1) + " tasks in the list");
                taskIndex++;
                printLine();
            } else if (command.startsWith("deadline")) {
                String taskDescription = command.substring(command.indexOf("deadline ") + 9, command.indexOf("/by ") - 1);
                String by = command.substring(command.indexOf("/by ") + 4);
                taskList[taskIndex] = new Deadline(taskDescription, by);

                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + taskList[taskIndex].toString());
                System.out.println("Now you have " + (taskIndex + 1) + " tasks in the list");
                taskIndex++;
                printLine();
            } else if (command.startsWith("event")) {
                String taskDescription = command.substring(command.indexOf("event ") + 6, command.indexOf("/from ") - 1);
                String from = command.substring(command.indexOf("/from ") + 6, command.indexOf("/to ") - 1);
                String to = command.substring(command.indexOf("/to ") + 4);
                taskList[taskIndex] = new Event(taskDescription, from, to);

                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + taskList[taskIndex].toString());
                System.out.println("Now you have " + (taskIndex + 1) + " tasks in the list");
                taskIndex++;
                printLine();
            } else {
                break;
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

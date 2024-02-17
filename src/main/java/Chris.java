import java.util.Scanner;

public class Chris {
    protected static int taskCount = 0;
    protected static Task[] taskList = new Task[100];
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello, I am Chris");
        printLine();
        menu();
    }

    public static void printLine() {
        System.out.println("------------------------------------------------");
    }

    public static void printList() {
        if (taskCount == 0) {
            System.out.println("Sorry, there are no tasks currently.");
            printLine();
        } else {
            System.out.println("Here are you current tasks!");
            for (int i = 0; i < taskCount; i++) {
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(taskList[i]);
            }
        }
    }

    public static void menu() {
        boolean done = false;
        Scanner in = new Scanner(System.in);
        while (!done) {
            System.out.println("What can I do for you?");
            printLine();
            String line = in.nextLine();
            if (line.startsWith("todo")) {
                taskList[taskCount] = new ToDo(line.substring(5));
                taskCount++;
                System.out.println("ToDo added!");
                printLine();
            } else if (line.startsWith("deadline")) {
                taskList[taskCount] = new Deadline(line.substring(9).split("/by"));
                taskCount++;
                System.out.println("Deadline added!");
                printLine();
            } else if (line.startsWith("event")) {
                taskList[taskCount] = new Event(line.substring(6).split("/from|/to"));
                taskCount++;
                System.out.println("Event added!");
                printLine();
            } else if (line.startsWith("mark")) {
                String taskNumber = line.substring(5);
                taskList[Integer.parseInt(taskNumber) - 1].markTask();
            } else if (line.startsWith("list")) {
                printList();
            } else if (line.startsWith("quit")) {
                System.out.println("Goodbye, have a nice day!");
                printLine();
                done = true;
            } else {
                System.out.println("Sorry, I don't recognise that command.");
                printLine();
            }
        }
    }
}

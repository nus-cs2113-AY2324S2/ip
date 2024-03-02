import java.util.Scanner;

public class Chris {
    protected static taskList tasks = new taskList();
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello, I am Chris");
        menu();
        printLine();
    }

    public static void printLine() {
        System.out.println("------------------------------------------------");
    }

    public static void printList() {
        if (tasks.getTaskCount() == 0) {
            System.out.println("Sorry, there are no tasks currently.");
            printLine();
        } else {
            System.out.println("Here are you current tasks!");
            tasks.printTaskList();
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
                try {
                    tasks.addTask(new ToDo(line.substring(5).trim()));
                    System.out.println("ToDo added!");
                    printLine();
                } catch (illegalToDoInput e) {
                    System.out.println("You've entered an illegal ToDo input, it should be in the form [todo] [description]");
                }
            } else if (line.startsWith("deadline")) {
                try {
                    tasks.addTask(new Deadline(line.substring(9).split("/by")));
                    System.out.println("Deadline added!");
                    printLine();
                } catch (illegalDeadlineInput e) {
                    System.out.println("You've entered an illegal Deadline input, it should be in the form [deadline] [description] [/by] [time]");
                }

            } else if (line.startsWith("event")) {
                try {
                    tasks.addTask(new Event(line.substring(6).split("/from|/to")));
                    System.out.println("Event added!");
                    printLine();
                } catch (illegalEventInput e) {
                    System.out.println("You've entered an illegal event input, it should be in the form [event] [description] [/from] [time] [/to] [time]");
                }
            } else if (line.startsWith("mark")) {
                String taskNumber = line.substring(5);
                try {
                    tasks.markTask(taskNumber);
                } catch (illegalTaskNumberInput e) {
                    System.out.println("You've entered an illegal task number, it should be a number within the size of the list.");
                }
            } else if (line.startsWith("list")) {
                printList();
            } else if (line.startsWith("quit")) {
                System.out.println("Goodbye, have a nice day!");
                done = true;
            } else {
                System.out.println("Sorry, I don't recognise that command.");
                printLine();
            }
        }
    }
}

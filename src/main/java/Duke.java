import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    static boolean ifExit = false; //exits program if true
    static Task[] tasks = new Task[100];
    static int listIndex = 0;


    public static void addList(String line) {
        System.out.println("--------------------------------------");
        System.out.println("added: " + line);
        System.out.println("--------------------------------------");
        listIndex ++;
    }

    public static void printBye() {
        System.out.println("--------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------");
        ifExit = true;
    }

    public static void printList() {
        int printCounter = 1;
        System.out.println("--------------------------------------");
        System.out.println("Here are the tasks in your lists:");
        for (Task item : Arrays.copyOf(tasks, listIndex)) {
            System.out.print(printCounter + ".");
            System.out.println(item);
            printCounter++;
        }
    }

    public static void introStart() {
        String logo
                = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------");
        System.out.println("Hello! I'm Steffy");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------");
    }

    public static void markTask(Task[] tasks, String line) {
        tasks[Integer.parseInt(line.substring(5)) - 1].markAsDone();
        System.out.println("--------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println
                (tasks[Integer.parseInt(line.substring(5)) - 1].getStatusIcon() + " "
                        + tasks[Integer.parseInt(line.substring(5)) - 1].description);
        System.out.println("--------------------------------------");
    }

    public static void unmarkTask(Task[] tasks, String line) {
        tasks[Integer.parseInt(line.substring(7)) - 1].unmarkDone();
        System.out.println("--------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println
                (tasks[Integer.parseInt(line.substring(7)) - 1].getStatusIcon() + " "
                        + tasks[Integer.parseInt(line.substring(7)) - 1].description);
        System.out.println("--------------------------------------");
    }

    public static void performAction(Task[] tasks, String line, int listIndex) {
        int eventDividerPositionTo = line.indexOf("/to");
        int eventDividerPositionFrom = line.indexOf("/from");
        int deadlineDividerPositionBy = line.indexOf("/by");

        switch(line.split(" ")[0].toLowerCase()) {
        case "bye":
            printBye();
            break;
        case "list":
            printList();
            break;
        case "unmark":
            unmarkTask(tasks, line);
            break;
        case "mark":
            markTask(tasks, line);
            break;
        case "todo":
            addList(line);
            tasks[listIndex] = new Todo(line);
            break;
        case "event":
            addList(line);
            tasks[listIndex] = new Event(line.substring(0, eventDividerPositionFrom).trim(), line.substring(eventDividerPositionFrom + 5,eventDividerPositionTo).trim(), line.substring(eventDividerPositionTo + 3).trim());
            break;
        case "deadline":
            addList(line);
            tasks[listIndex] = new Deadline(line.substring(0, deadlineDividerPositionBy).trim(), line.substring(deadlineDividerPositionBy + 3).trim());
            break;
        }

    }

    public static void main(String[] args) {
        introStart(); //Prints starting screen

        while (!ifExit) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            performAction(tasks, line, listIndex);
        }
    }
}

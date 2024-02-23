import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    static boolean ifExit = false; //exits program if true
    static Task[] tasks = new Task[100]; //List of tasks
    static int listIndex = 0; //To index through Task[] tasks
    static int remainingTasksCounter = 0;

    public static void echoTask() {
        System.out.println("--------------------------------------");
        System.out.println("Got it! I've added this task:");
        System.out.println(tasks[listIndex]);
        System.out.println("--------------------------------------");
        listIndex ++;
    }

    public static void printBye() {
        System.out.println("--------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------");
        ifExit = true;
    }

    public static void printList() throws IllegalShapeException {
        {
            int printCounter = 1;
            if (listIndex == 0) {
                throw new IllegalShapeException(); //Throws exception for empty list
            }
            System.out.println("--------------------------------------");
            System.out.println("Here are the tasks in your lists:");
            for (Task item : Arrays.copyOf(tasks, listIndex)) {
                System.out.print(printCounter + ".");
                System.out.println(item);
                printCounter++;
            }
            System.out.println("--------------------------------------");
        }
    }

    public static void introStart() {
        String logo
                = " _______    ___           _____   _____ ___    ___\n"
                + "|   ____\\___\\  \\___    __/   __| /   __|\\  \\__/  /\n"
                + " \\   \\   \\___  ____\\__|__    ___|    ___|\\_   __/\n"
                + "  \\   \\     |  | /  _  \\|   |    |  |     /  / \n"
                + " __\\   \\    |  ||    __/|   |    |  |  __/  / \n"
                + "/_______|   |__| \\_____||___|    |__| |____/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------");
        System.out.println("Hello! I'm Steffy");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------");
    }

    public static void markTask(Task[] tasks, String line) throws IllegalShapeException {
        if (Integer.parseInt(line.substring(5)) > listIndex || Integer.parseInt(line.substring(5)) <= 0) {
            throw new IllegalShapeException(); //Throws exception for marking out of bounds
        }
        tasks[Integer.parseInt(line.substring(5)) - 1].markAsDone();
        System.out.println("--------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println
                (tasks[Integer.parseInt(line.substring(5)) - 1].getStatusIcon() + " "
                        + tasks[Integer.parseInt(line.substring(5)) - 1].description);
        System.out.println("--------------------------------------");
    }

    public static void unmarkTask(Task[] tasks, String line) throws IllegalShapeException {
        if (Integer.parseInt(line.substring(7)) > listIndex || Integer.parseInt(line.substring(7)) <= 0) {
            throw new IllegalShapeException(); //Throws exception for unmarking out of bounds
        }
        tasks[Integer.parseInt(line.substring(7)) - 1].unmarkDone();
        System.out.println("--------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println
                (tasks[Integer.parseInt(line.substring(7)) - 1].getStatusIcon() + " "
                        + tasks[Integer.parseInt(line.substring(7)) - 1].description);
        System.out.println("--------------------------------------");
    }

    public static void deleteTask(int i) {
        remainingTasksCounter ++;
        System.out.println("--------------------------------------");
        System.out.println("Noted, I have removed this task:");
        System.out.println(tasks[i-1]);
        System.out.println("Now you have " + (listIndex - remainingTasksCounter) + " tasks left");
        System.out.println("--------------------------------------");
    }

    public static void performAction(Task[] tasks, String line, int listIndex) throws IllegalShapeException{
        int eventDividerPositionTo = line.indexOf("/to");
        int eventDividerPositionFrom = line.indexOf("/from");
        int deadlineDividerPositionBy = line.indexOf("/by");

        if(line.isEmpty()) {
            throw new IllegalShapeException(); //Throws error for empty inputs
        }

        switch(line.split(" ")[0].toLowerCase()) {
        case "bye": //Exits program with farewell dialogue
            printBye();
            break;
        case "list": //Shows entire list of tasks
            try {
                printList();
            } catch (IllegalShapeException e) {
                System.out.println("--------------------------------------");
                System.out.println("Your list is empty!");
                System.out.println("--------------------------------------");
            }
            break;
        case "unmark": //unmark a task
            try {
                unmarkTask(tasks, line);
            } catch (IllegalShapeException e) {
                System.out.println("--------------------------------------");
                System.out.println("There is no such Task! :( ");
                System.out.println("--------------------------------------");
            }
            break;
        case "mark": //marks a task as done
            try {
                markTask(tasks, line);
            } catch (IllegalShapeException e) {
                System.out.println("--------------------------------------");
                System.out.println("There is no such Task! :( ");
                System.out.println("--------------------------------------");
            }
            break;
        case "todo": //add a new task
            tasks[listIndex] = new Todo(line);
            echoTask();
            break;
        case "event": //add a new event task
            tasks[listIndex] = new Event(line.substring(0, eventDividerPositionFrom).trim(), line.substring(eventDividerPositionFrom + 5,eventDividerPositionTo).trim(), line.substring(eventDividerPositionTo + 3).trim());
            echoTask();
            break;
        case "deadline": //add a new deadline task
            tasks[listIndex] = new Deadline(line.substring(0, deadlineDividerPositionBy).trim(), line.substring(deadlineDividerPositionBy + 3).trim());
            echoTask();
            break;
        case "delete":
            deleteTask(Integer.parseInt(line.substring(7)));
            break;
        default:
            System.out.println("--------------------------------------");
            System.out.println("Sorry! I don't know what that means!");
            System.out.println("--------------------------------------");
        }

    }

    public static void main(String[] args) {
        introStart(); //Prints starting screen

        while (!ifExit) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            try {
                performAction(tasks, line, listIndex); //Executes an action based on first word of command in String line
            } catch (IllegalShapeException e) {
                System.out.println("--------------------------------------");
                System.out.println("Your input is empty!");
                System.out.println("--------------------------------------");
            }
        }
    }
}

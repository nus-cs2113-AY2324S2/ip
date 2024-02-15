import java.util.Scanner;

public class BobBot {

    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static Task[] allTasks = new Task[MAX_NUMBER_OF_TASKS];
    private static int numTasks = 0;

    private static void mark(String line) {
        int taskNumberToMark = Integer.parseInt(line.substring("mark".length()).trim()) - 1;
        allTasks[taskNumberToMark].markAsDone();

        printMessageMarkAsDone(taskNumberToMark);
    }

    private static void printMessageMarkAsDone(int taskNumberToMark) {
        drawLine(true);
        System.out.println("\tGot it! Marking this task as done:");
        System.out.println("\t  " + allTasks[taskNumberToMark].toString());
        drawLine(true);
    }

    private static void unmark(String line) {
        int taskNumberToUnmark = Integer.parseInt(line.substring("unmark".length()).trim()) - 1;
        allTasks[taskNumberToUnmark].markAsUndone();

        printMessageUnmarkAsDone(taskNumberToUnmark);
    }

    private static void printMessageUnmarkAsDone(int taskNumberToUnmark) {
        drawLine(true);
        System.out.println("\tAlright! Unmarking this task:");
        System.out.println("\t  " + allTasks[taskNumberToUnmark].toString());
        drawLine(true);
    }

    private static void printTaskList() {
        int taskNumberToDisplay;

        for (int taskIndex = 0; taskIndex < numTasks; taskIndex += 1) {
            taskNumberToDisplay = taskIndex + 1;
            System.out.printf("\t%d. %s\n", taskNumberToDisplay, allTasks[taskIndex].toString());
        }
    }

    private static void displayList() {
        drawLine(true);
        printTaskList();
        drawLine(true);
    }

    public static void addTask(String line) {

        Task newTask = null;
        if (line.startsWith("todo")) {
            try {
                newTask = new Todo(line);
            } catch (InvalidTodoException e) {
                drawErrorLine();
                System.out.println("\tIt seems that you have not entered the task information. "
                        + "\n\tUsage: todo {description of task}"
                        + "\n\tPlease try again, or enter /help if you need it");
                drawErrorLine();
                return;
            }
        } else if (line.startsWith("deadline")) {
            try {
                newTask = new Deadline(line);
            } catch (InvalidDeadlineException e) {
                drawErrorLine();
                System.out.println("\tIt seems that you have missed out on certain fields for deadline. "
                        + "\n\tUsage: deadline {description of task} /by {due date}"
                        + "\n\tPlease try again, or enter /help if you need it");
                drawErrorLine();
                return;
            }
        } else if (line.startsWith("event")) {
            try {
                newTask = new Event(line);
            } catch (InvalidEventException e) {
                drawErrorLine();
                System.out.println("\tIt seems that you have missed out on certain fields for event. "
                        + "\n\tUsage: event {description of task} /from {start date/time} /to {end date/time}"
                        + "\n\tPlease try again, or enter /help if you need it");
                drawErrorLine();
                return;
            }
        } else {
            handleInvalidCommand();
            return;
        }

        allTasks[numTasks] = newTask;
        numTasks += 1;

        echoCommand(line, newTask);
    }

    private static void handleInvalidCommand() {
        drawErrorLine();
        System.out.println(
                "\tI did not understand that. Refer to the help manual for information on \n\t keying in the right commands!");
        printHelpMessage();
        drawErrorLine();
    }

    public static void echoCommand(String lineString, Task newTask) {
        drawLine(true);
        System.out.println("\tGot it! I've added this task:\n\t  " + newTask.toString());
        System.out.printf("\tNow you have %d tasks in the list\n", numTasks);
        drawLine(true);
        System.out.println();
    }

    public static void drawErrorLine() {
        System.out.println("\t*******************************ERROR******************************************");
    }

    public static void drawLine(Boolean isIncludeIndentation) {
        if (isIncludeIndentation) {
            System.out.print("\t");
        } else {
            System.out.print("________");
        }
        System.out.println("______________________________________________________________________________");
    }

    public static void greet() {
        drawLine(false);
        System.out.println("Hello! I'm BobBot, your TODO list creator");
        System.out.println("Simply type in any task and I will store them for you!");
        drawLine(false);
    }

    private static void printHelpMessage() {
        drawLine(true);
        System.out.println("\tI see you require some help. Fear not, I shall come to your assistance:\n");
        System.out.println("\tHere are the options available to you:");
        System.out.println("\t\t/help - Display this help menu");
        System.out.println("\t\ttodo ... - State something you want to add to the TODO list");
        System.out.println("\t\tdeadline ... - Tell me about an important deadline you need to meet");
        System.out.println("\t\tevent ... - Let me know what event you have coming up");
        System.out.println("\t\tbye - We bid our farewells (sob)");
        drawLine(true);
    }

    private static void bidFarewell() {
        drawLine(true);
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine(true);
    }

    private static void runTaskManager() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {

            if (line.equalsIgnoreCase("/help")) {
                printHelpMessage();
            } else if (line.equalsIgnoreCase("list")) {
                displayList();
            } else if (line.startsWith("mark")) {
                mark(line);
            } else if (line.startsWith("unmark")) {
                unmark(line);
            } else {
                addTask(line);
            }

            line = in.nextLine();
        }
    }

    public static void main(String[] args) {
        greet();
        runTaskManager();
        bidFarewell();
    }

}

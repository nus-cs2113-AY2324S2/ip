import java.util.Scanner;

public class BobBot {

    private static Task[] allTasks = new Task[100];
    private static int numTasks = 0;

    private static void mark(String line) {
        int taskNumToMark = Integer.parseInt(line.substring(4).trim()) - 1;
        allTasks[taskNumToMark].markAsDone();
        drawLine(true);
        System.out.println("\tGot it! Marking this task as done:");
        System.out.println("\t  " + allTasks[taskNumToMark].toString());
        drawLine(true);
    }

    private static void unmark(String line) {
        int taskNumToUnmark = Integer.parseInt(line.substring(6).trim()) - 1;
        allTasks[taskNumToUnmark].markAsUndone();
        drawLine(true);
        System.out.println("\tAlright! Unmarking this task:");
        System.out.println("\t  " + allTasks[taskNumToUnmark].toString());
        drawLine(true);
    }

    private static void listItems() {
        drawLine(true);
        for (int taskIndex = 0; taskIndex < numTasks; taskIndex += 1) {
            System.out.printf("\t%d. %s\n", taskIndex + 1, allTasks[taskIndex].toString());
        }
        drawLine(true);
    }

    public static void addTask(String line) {

        Task newTask = null;
        if (line.startsWith("todo")) {
            newTask = new Todo(line);
        } else if (line.startsWith("deadline")) {
            newTask = new Deadline(line);
        } else if (line.startsWith("event")) {
            newTask = new Event(line);
        }
        
        allTasks[numTasks] = newTask;
        numTasks += 1;

        echoCommand(line, newTask);
    }

    public static void echoCommand(String lineString, Task newTask) {
        drawLine(true);
        System.out.println("\tGot it! I've added this task:\n\t  " + newTask.toString());
        System.out.printf("\tNow you have %d tasks in the list\n", numTasks);
        drawLine(true);
        System.out.println();
    }

    public static void drawLine(Boolean includeIndentation) {
        if (includeIndentation) {
            System.out.print("\t");
        } else {
            System.out.print("________");
        }
        System.out.println("__________________________________");
    }

    public static void greet() {
        drawLine(false);
        System.out.println("Hello! I'm BobBot, your TODO list creator");
        System.out.println("Simply type in any task and I will store them for you!");
        drawLine(false);
    }

    public static void main(String[] args) {
        greet();

        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {

            if (line.equalsIgnoreCase("list")) {
                listItems();
            } else if (line.startsWith("mark")) {
                mark(line);
            } else if (line.startsWith("unmark")) {
                unmark(line);
            } else {
                addTask(line);
            }

            line = in.nextLine();
        }

        drawLine(true);
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine(true);
    }
}

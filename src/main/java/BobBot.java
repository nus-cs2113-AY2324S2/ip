import java.util.Scanner;

public class BobBot {
    
    private static Task[] allTasks = new Task[100];
    private static int numTasks = 0;
    
    private static void mark(String line) {
        int taskNumToMark = Integer.parseInt(line.substring(4).trim())-1;
        allTasks[taskNumToMark].markAsDone();
        drawLine(true);
        System.out.println("\tGot it! Marking this task as done:");
        System.out.printf("\t[%s] %s\n", allTasks[taskNumToMark].getStatusIcon(), allTasks[taskNumToMark].getDescription());
        drawLine(true);
    }
    
    private static void unmark(String line) {
        int taskNumToUnmark = Integer.parseInt(line.substring(6).trim())-1;
        allTasks[taskNumToUnmark].markAsUndone();
        drawLine(true);
        System.out.println("\tAlright! Unmarking this task:");
        System.out.printf("\t[%s] %s\n", allTasks[taskNumToUnmark].getStatusIcon(), allTasks[taskNumToUnmark].getDescription());
        drawLine(true);
    }

    private static void listItems() {
        drawLine(true);
        for (int taskIndex = 0; taskIndex < numTasks; taskIndex += 1) {
            //TODO add icon
            System.out.printf("\t%d.[%s] %s\n", taskIndex+1, allTasks[taskIndex].getStatusIcon(), allTasks[taskIndex].getDescription());
        }
        drawLine(true);
    }

    public static void addTask(String line) {
        Task newTask = new Task(line);
        allTasks[numTasks] = newTask;        
        numTasks += 1;
    }

    public static void echoCommand(String lineString) {
        drawLine(true);
        System.out.println("\tadded: " + lineString);
        drawLine(true);
        System.out.println();
    }

    public static void drawLine(Boolean includeIndentation) {
        if (includeIndentation) {
            System.out.print("\t");
        }
        else {
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
                echoCommand(line);
                addTask(line);
            }
            
            line = in.nextLine();
        }

        drawLine(true);
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine(true);
    }
}


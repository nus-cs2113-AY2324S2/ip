import java.util.Scanner;

public class BobBot {

    private static String[] allTasks = new String[100];
    private static int numTasks = 0;

    private static void listItems() {
        drawLine(true);
        for (int taskIndex = 0; taskIndex < numTasks; taskIndex += 1) {
            System.out.printf("\t%d. %s\n", taskIndex+1, allTasks[taskIndex]);
        }
        drawLine(true);
    }

    public static void addTask(String newTask) {
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

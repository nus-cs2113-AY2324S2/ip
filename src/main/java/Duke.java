import java.util.Scanner;

public class Duke {
    static Task[] taskList = new Task[100];

    // Method to greet the user
    public static void greet() {
        String logo = "  TTTTT  AAAAA  TTTTT  EEEEE\n" +
                "    T    A   A    T    E\n" +
                "    T    AAAAA    T    EEEE\n" +
                "    T    A   A    T    E\n" +
                "    T    A   A    T    EEEEE\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------------------------------------------");
        System.out.println("                Hello! I'm Tate!");
        System.out.println("                What do you need?\n");
        System.out.println("------------------------------------------------------------");
    }

    // Method to say goodbye
    public static void end() {
        System.out.println("------------------------------------------------------------");
        System.out.println("            Goodbye and Take care, later then!");
        System.out.println("------------------------------------------------------------");
    }

    // Method to display marking of tasks
    public static void displayMarking(int taskNumber, String mark) {
        String displayString;
        if (mark.equals("mark")) {
            displayString = "    Completing tasks like a true Leader \uD83D\uDE01";
            taskList[taskNumber].markTask();
        } else {
            displayString = "    \uD83D\uDE21 Sad reality, you have NOT completed this task";
            taskList[taskNumber].unmarkTask();
        }
        System.out.println("------------------------------------------------------------");
        System.out.println(displayString);
        System.out.printf("        [%s] %s%n", taskList[taskNumber].getStatusIcon(), taskList[taskNumber].description);
        System.out.println("------------------------------------------------------------");
    }

    // Method to display tasks
    public static void displayTasks(int count) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("    Your list of Tasks");
        for (int i = 0; i < count; i++) {
            System.out.printf("     %d. [%s] %s%n", i + 1, taskList[i].getStatusIcon(), taskList[i].description);
        }
        System.out.println("----------------------------------------------------------------");
    }

    // Main method
    public static void main(String[] args) {
        greet();
        String line;
        Scanner in = new Scanner(System.in);
        int count = 0;
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                displayTasks(count);
            } else if (line.startsWith("mark") || line.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(line.split(" ")[1]);
                displayMarking(taskNumber - 1, line.split(" ")[0]);
            } else {
                taskList[count] = new Task(line);
                count++;
                System.out.println("    ------------------------------------------------------------");
                System.out.println("    " + "added: " + line);
                System.out.println("----------------------------------------------------------------");
            }
        }
        end();
    }
}

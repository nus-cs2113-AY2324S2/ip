import java.util.Scanner;

public class Jeff {
    private static int totalTasks = 0;

    private static void printIndented(String s) {
        System.out.println("    " + s);
    }

    public static void main(String[] args) {
        String[] tasks = new String[100];
        String divider = "____________________________________________________________";
        Scanner in = new Scanner(System.in);

        printIndented(divider);
        printIndented("Hello! I'm Jeff");
        printIndented("What can I do for you?");
        printIndented(divider);

        while (true) {
            String line = in.nextLine();
            switch (line) {
            case "list":
                printIndented(divider);
                for (int i = 0; i < totalTasks; i++) {
                    printIndented(" " + (i + 1) + ". " + tasks[i]);
                }
                printIndented(divider);
                break;
            case "bye":
                printIndented(divider);
                printIndented("Bye. Hope to see you again soon!");
                printIndented(divider);
                return;
            default:
                printIndented(divider);
                printIndented(" added: " + line);
                printIndented(divider);
                tasks[totalTasks] = line;
                totalTasks++;
                break;
            }
        }
    }
}

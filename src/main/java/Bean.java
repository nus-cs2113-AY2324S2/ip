import java.util.Scanner;
import java.util.Arrays;

public class Bean {
    public static void printList(Task[] list) {
        int numItems = 1;

        for (Task item : list) {
            System.out.print("  " + numItems + ".");
            item.printTask();
            numItems += 1;
        }
    }

    public static void main(String[] args) {
        String SEPARATOR = "--------------------------------------";
        System.out.println("Hello! I'm Bean.\nWhat can I do for you?");
        System.out.println(SEPARATOR);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        Task[] listOfTasks = new Task[100];
        int numItems = 0;
        while(!line.equals("bye")) {

            if (line.equals("list")) {
                printList(Arrays.copyOf(listOfTasks, numItems));
                System.out.println(SEPARATOR);
            } else if (line.startsWith("mark")) {
                int taskIndex = Integer.parseInt(line.substring(5)) - 1;
                if (taskIndex >= 0 & taskIndex < numItems) {
                    listOfTasks[taskIndex].setDone();
                }
                System.out.println(SEPARATOR);
            } else if (line.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(line.substring(7)) - 1;
                if (taskIndex >= 0 & taskIndex < numItems) {
                    listOfTasks[taskIndex].setUndone();
                }
                System.out.println(SEPARATOR);
            } else {
                System.out.println("    added: " + line);
                System.out.println(SEPARATOR);
                listOfTasks[numItems] = new Task(line);
                numItems += 1;
            }
            line = in.nextLine();
        }

        System.out.println("Bean will take a nap now. Bye!");
        System.out.println(SEPARATOR);
    }
}

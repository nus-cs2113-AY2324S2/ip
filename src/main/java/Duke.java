import java.util.Scanner;
import java.util.Arrays;
public class Duke {

    public static void printText(String textLine) {
        System.out.println(textLine);
    }

    public static void printTask(String[] tasks) {
        int index = 1;
        for (String task : tasks) {
            String indexPrinted = String.valueOf(index) + ". ";
            printText(indexPrinted + task);
            index++;
        }
    }

    public static void addTask(String[] tasks) {
        String horizon = "____________________________"
                + "________________________________";
        int index = 0;
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String text = userInput.nextLine();
            if (text.equals("bye")) {
                break;
            }
            else if (text.equals("list")) {
                printText(horizon);
                printTask(Arrays.copyOf(tasks, index));
                printText(horizon + "\n");
            }
            else {
                printText(horizon);
                tasks[index] = text;
                index++;
                printText("added: " + text);
                printText(horizon + "\n");
            }
        }
    }
    public static void main(String[] args) {
        String horizon = "____________________________"
                + "________________________________\n";
        String botName = "Battch";
        printText(horizon + "Hello! I'm " + botName);
        printText("What can I do for you?");
        printText(horizon);

        String[] tasks = new String[100];

        addTask(tasks);

        printText(horizon + "Bye. Hope to see you again soon!");
        printText(horizon);


    }
}

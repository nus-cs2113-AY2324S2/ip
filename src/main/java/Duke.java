import java.util.Scanner;
public class Duke {

    public static void printText(String textLine) {
        System.out.println(textLine);
    }

    public static void echo() {
        String horizon = "____________________________"
                + "________________________________\n";
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String text = userInput.next();
            if (text.equals("bye")) {
                break;
            }
            printText(horizon + text);
            printText(horizon);
        }
    }
    public static void main(String[] args) {
        String horizon = "____________________________"
                + "________________________________\n";
        String botName = "Battch";
        printText(horizon + "Hello! I'm " + botName);
        printText("What can I do for you?");
        printText(horizon);

        echo();

        printText(horizon + "Bye. Hope to see you again soon!");
        printText(horizon);


    }
}

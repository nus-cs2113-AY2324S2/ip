import java.util.Scanner;

public class Soot {
    private static int listCounter = 0;
    public static void main(String[] args) {
        String line;
        String lowerCase;
        Scanner in = new Scanner(System.in);
        ListItem[] items = new ListItem[100];

        boolean isBye = false;

        drawLine(); //initial greeting
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        drawLine();

        while (!isBye) {
            line = in.nextLine(); //user input
            drawLine();
            lowerCase = line.toLowerCase();
            isBye = verifyInput(items, line);
        }
    }

    public static boolean verifyInput(ListItem[] list, String input) {
        String lowerCase = input.toLowerCase();
        if (lowerCase.startsWith("done")) {
            lowerCase = "done";
        } else if (lowerCase.startsWith("unmark")) {
            lowerCase = "unmark";
        }

        switch (lowerCase) {
        case "bye":
            greetGoodbye();
            return true;
        case "list":
            System.out.println("tasks to be done!");
            for (int i = 0; i < listCounter; i++)
                list[i].printItem();
            drawLine();
            break;
        case "done":
            String taskNumber = input.substring(5);
            int listIndex = Integer.parseInt(taskNumber) - 1;
            list[listIndex].markDone();
            drawLine();
            break;
        case "unmark":
            taskNumber = input.substring(7);
            listIndex = Integer.parseInt(taskNumber) - 1;
            list[listIndex].markUndone();
            drawLine();
            break;
        default:
            System.out.println("added: " + input);
            drawLine();

            list[listCounter] = new ListItem(input, listCounter);
            listCounter++;
            break;
        }
        return false;
    }
    public static void drawLine() {
        int LINE_LENGTH = 60;
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void greetGoodbye() {
        System.out.println("Bye! Till the next time we meet...");
        drawLine();
    }
}

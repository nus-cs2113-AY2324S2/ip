import java.util.Scanner;

public class Beefy {
    private static final String BOT_NAME = "BEEFY";
    private Scanner userInput;
    private TaskList userTasks;

    public Beefy() {
        userInput = new Scanner(System.in);
        userTasks = new TaskList();
    }

    private void printSeparation() {
        int WIDTH = 59;
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("_");
        }
        System.out.println("_");
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void startChat() {
        String userLine;
        printSeparation();
        System.out.println(BOT_NAME);
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        printSeparation();
        do {
            System.out.println("You");
            userLine = userInput.nextLine().trim();
            String[] userWords = userLine.split("\\s+");
            printSeparation();
            if (userLine.equalsIgnoreCase("bye")) {
                break;
            } else if (userLine.equalsIgnoreCase("list")) {
                System.out.println(BOT_NAME);
                userTasks.listOut();
                printSeparation();
            } else if (userWords[0].equalsIgnoreCase("mark") && userWords.length == 2
                    && isInteger(userWords[1])) {
                System.out.println(BOT_NAME);
                userTasks.markTask(Integer.parseInt(userWords[1]));
                printSeparation();
            } else if (userWords[0].equalsIgnoreCase("unmark") && userWords.length == 2
                    && isInteger(userWords[1])){
                System.out.println(BOT_NAME);
                userTasks.unmarkTask(Integer.parseInt(userWords[1]));
                printSeparation();
            } else {
                System.out.println(BOT_NAME);
                userTasks.addTask(userLine);
                printSeparation();
            }
        } while(!userLine.equalsIgnoreCase("bye"));
    }

    public void endChat() {
        System.out.println(BOT_NAME + ": Bye. Hope to see you again soon!");
        printSeparation();
    }

}
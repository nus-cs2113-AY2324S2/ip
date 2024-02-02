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

    private void executeCommand(String userWords, CommandType command) {
        String[] taskDetails;
        switch(command) {
        case UNMARK:
            userTasks.unmarkTask(Integer.parseInt(userWords));
            break;
        case MARK:
            userTasks.markTask(Integer.parseInt(userWords));
            break;
        case TODO:
            userTasks.addTask(userWords);
            break;
        case DEADLINE:
            taskDetails = userWords.trim().split("/by");
            userTasks.addTask(taskDetails[0].trim(), taskDetails[1].trim());
            break;
        case EVENT:
            taskDetails =  userWords.trim().split("/from|/to");
            userTasks.addTask(taskDetails[0].trim(), taskDetails[1].trim(), taskDetails[2].trim());
            break;
        default:
            System.out.println("Crikey Mate! What is this command?");
        }
    }

    public void startChat() {
        String userLine;
        printSeparation();
        System.out.println(BOT_NAME);
        System.out.println("G'Day! I'm " + BOT_NAME);
        System.out.println("What can I do for you mate?");
        printSeparation();
        do {
            System.out.println("You");
            userLine = userInput.nextLine();
            printSeparation();
            String[] userWords = userLine.trim().split("\\s+", 2);
            System.out.println(BOT_NAME);
            if (userLine.equalsIgnoreCase("bye")) {
                break;
            } else if (userLine.equalsIgnoreCase("list")) {
                userTasks.listOut();
            } else if (userWords.length == 2 && isInteger(userWords[1])) {
                if (userWords[0].equalsIgnoreCase("mark")) {
                    executeCommand(userWords[1], CommandType.MARK);
                } else if (userWords[0].equalsIgnoreCase("unmark")) {
                    executeCommand(userWords[1], CommandType.UNMARK);
                }
            } else if (userWords[0].equalsIgnoreCase("todo")){
                executeCommand(userWords[1], CommandType.TODO);
            } else if (userWords[0].equalsIgnoreCase("deadline")){
                executeCommand(userWords[1], CommandType.DEADLINE);
            } else if (userWords[0].equalsIgnoreCase("event")){
                executeCommand(userWords[1], CommandType.EVENT);
            } else {
                System.out.println("Okay, so what?");
            }
            printSeparation();
        } while(!userLine.equalsIgnoreCase("bye"));
    }

    public void endChat() {
        System.out.println(BOT_NAME + ": See Ya Mate!");
        printSeparation();
    }

}
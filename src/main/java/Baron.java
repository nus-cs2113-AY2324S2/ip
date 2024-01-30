import java.util.Scanner;

public class Baron {
    public static void displayCommandsList(String[] commandsList) {
        int listNumber = 1;
        for (String command: commandsList) {
            if (command == null) {
                break;
            }
            System.out.println(listNumber + ". " + command);
            listNumber += 1;
        }
    }

    public static void main(String[] args) {
        String[] commandsList = new String[100];
        int numberOfCommands = 0;
        System.out.println("Hello! I'm Baron");
        System.out.println("What can I do for you?\n");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equalsIgnoreCase("list")) {
                displayCommandsList(commandsList);
                System.out.println("");
                continue;
            }
            System.out.println("Added: " + input + "\n");
            commandsList[numberOfCommands] = input;
            numberOfCommands += 1;
        }
    }
}
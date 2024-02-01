import java.util.Scanner;

public class Baron {
    public static void displayCommandsList(Task[] commandsList) {
        int listNumber = 1;
        for (Task command: commandsList) {
            if (command == null) {
                break;
            }
            System.out.println(listNumber + ".[" + command.getStatusIcon() + "] " + command.description);
            listNumber += 1;
        }
    }

    public static void main(String[] args) {
        Task[] commandsList = new Task[100];
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
            else if (input.toLowerCase().startsWith("mark")) {
                String digits = input.replaceAll("\\D+","");
                int commandIndex = Integer.parseInt(digits);
                commandsList[commandIndex - 1].markAsDone();
                continue;
            }
            else if (input.toLowerCase().startsWith("unmark")) {
                String digits = input.replaceAll("\\D+","");
                int commandIndex = Integer.parseInt(digits);
                commandsList[commandIndex - 1].unmarkAsDone();
                continue;
            }
            System.out.println("Added: " + input + "\n");
            commandsList[numberOfCommands] = new Task(input);
            numberOfCommands += 1;
        }
    }
}
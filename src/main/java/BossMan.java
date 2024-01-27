import java.util.Scanner;

public class BossMan {
    private final Scanner scanner;
    private final String sep = "____________________________________________________________";
    private final TaskList taskList;

    public BossMan() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    public void greetUser() {
        String botName = "BossMan";
        String greet = "Hello! I'm " + botName;
        String offerService = "What can I do for you?";
        System.out.println(sep + "\n" + greet);
        System.out.println(offerService + "\n" + sep);
    }

    public void endChat() {
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(exit + "\n" + sep);
    }

    public void startChat() {
        String userInput;

        do {
            System.out.print("You: ");
            userInput = scanner.nextLine();

            String[] parts = userInput.trim().split("\\s+");

            if (parts[0].equalsIgnoreCase("mark")) {
                int number = Integer.parseInt(parts[1]);
                taskList.markTask(number);
            } else if (parts[0].equalsIgnoreCase("unmark")) {
                int number = Integer.parseInt(parts[1]);
                taskList.unmarkTask(number);
            } else if (userInput.equalsIgnoreCase("list")) {
                // Print the updated to-do list
                taskList.printTasks();
            } else if (!userInput.equalsIgnoreCase("bye")) {
                // Add user input to the to-do list
                taskList.addTask(userInput);
                System.out.println("Added: " + userInput + "\n" + sep);
            }
        } while (!userInput.equalsIgnoreCase("bye"));
    }
}






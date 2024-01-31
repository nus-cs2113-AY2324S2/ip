import java.util.Scanner;
public class Davvy {
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printStatement(String statementType) {
        printLine();
        switch (statementType) {
        case "greetings":
            System.out.println(" Hello! I'm Davvy\n" + " What can I do for you?");
            break;
        case "goodbye":
            System.out.println(" Bye. Hope to see you again soon!");
            break;
        case "list":
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < TaskList.listLength(); i++) {
                System.out.print(" " + (i + 1) + ".");
                System.out.println(" [" + TaskList.getTask(i).getStatusIcon() + "] " +
                        TaskList.getTask(i).description);
            }
            break;
        case "add":
            System.out.println(" added: " + TaskList.getTask(TaskList.listLength()-1).description);
            break;
        default:
            break;
        }
        printLine();
    }

    private static void printStatement(String statementType, int taskNumber) {
        printLine();
        switch (statementType) {
        case "mark":
            System.out.println(" Nice! I've marked this task as done:");
            break;
        case "unmark":
            System.out.println(" OK, I've marked this task as not done yet:");
            break;
        }
        System.out.println(" [" + TaskList.getTask(taskNumber).getStatusIcon() + "] " +
                TaskList.getTask(taskNumber).description);
        printLine();
    }

    public void startChat() {
        printStatement("greetings");

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            int taskNumber;
            if (userInput.equalsIgnoreCase("list")) {
                printStatement("list");
            } else if (userInput.toLowerCase().contains("unmark")) {
                try {
                    taskNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                    TaskList.getTask(taskNumber).markNotDone();
                    printStatement("unmark", taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Exception thrown: " + e);
                    System.out.println("Please Enter A Valid Number");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Exception thrown: " + e);
                    System.out.println("There is no such task!");
                }

            } else if (userInput.toLowerCase().contains("mark")) {
                try {
                    taskNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                    TaskList.getTask(taskNumber).markDone();
                    printStatement("mark", taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Exception thrown: " + e);
                    System.out.println("Please Enter A Valid Number");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Exception thrown: " + e);
                    System.out.println("There is no such task!");
                }

            } else {
                Task inputTask = new Task(userInput);
                TaskList.addTask(inputTask);
                printStatement("add");
            }
            userInput = in.nextLine();
        }
    }

    public void endChat() {
        printStatement("goodbye");
    }
}

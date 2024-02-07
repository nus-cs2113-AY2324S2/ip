import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Davvy {
    private static final String BOT_NAME = "Davvy";
    private static boolean toEnd;
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printStatement(String statementType) {
        printLine();
        switch (statementType) {
        case "greetings":
            System.out.println(" Hello! I'm " + BOT_NAME + "\n" + " What can I do for you?");
            break;
        case "goodbye":
            System.out.println(" Bye. Hope to see you again soon!");
            break;
        }
        printLine();
    }

    public void startChat() {
        printStatement("greetings");
        toEnd = false;
        Scanner in = new Scanner(System.in);
        while (!toEnd) {
            String[] parsedInput = processInput(in.nextLine());
            processCommand(parsedInput);
        }
    }

    public void endChat() {
        printStatement("goodbye");
    }

    public String[] processInput (String inputText) {
        if (inputText.isEmpty()) {
            printLine();
            System.out.println("Please type something >:(");
            printLine();
            return null;
        }
        return inputText.split("\\b", 2);
    }

    public void processCommand (String[] input) {
        String commandType = input[0].trim().toLowerCase();
        String commandArg = input[1].isEmpty() ? "" : input[1];

        switch (commandType) {
        case "list":
            TaskList.printList();
            break;
        case "mark":
            try {
                TaskList.getTask(parseInt(commandArg.trim()) - 1).markDone();
            } catch (NumberFormatException e) {
                System.out.println("Exception thrown: " + e);
                System.out.println("Please Enter A Valid Number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Exception thrown: " + e);
                System.out.println("There is no such task!");
            }
            break;
        case "unmark":
            try {
                TaskList.getTask(parseInt(commandArg.trim()) - 1).markNotDone();
            } catch (NumberFormatException e) {
                System.out.println("Exception thrown: " + e);
                System.out.println("Please Enter A Valid Number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Exception thrown: " + e);
                System.out.println("There is no such task!");
            }
            break;
        case "bye":
            toEnd = true;
            break;
        case "todo":
            Todo inputTodo = new Todo(commandArg);
            TaskList.addTask(inputTodo);
            break;
        case "deadline":
            if (commandArg.contains("/by")) {
                String[] newCommandArg = commandArg.split("/by", 2);
                Deadline inputDeadline = new Deadline(newCommandArg[0], newCommandArg[1]);
                TaskList.addTask(inputDeadline);
            } else {
                System.out.println("Please put a date!");
            }
            break;
        case "event":
            if (commandArg.contains("/from") && commandArg.contains("/to")) {
                String[] newCommandArg = commandArg.split("/from", 2);
                String[] newCommandArg2 = newCommandArg[1].split("/to", 2);
                Events inputEvent = new Events(newCommandArg[0], newCommandArg2[0], newCommandArg2[1]);
                TaskList.addTask(inputEvent);
            } else {
                System.out.println("Please put a correct time!");
            }
            break;
        default:
            System.out.println("Unknown Command, type something I know please!");
            break;
        }
    }
}

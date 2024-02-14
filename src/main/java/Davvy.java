import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Davvy {
    private static final String BOT_NAME = "Davvy";
    private static boolean isExitStatus;
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
        isExitStatus = false;
        Scanner in = new Scanner(System.in);
        while (!isExitStatus) {
            try {
                String[] parsedInput = processInput(in.nextLine());
                processCommand(parsedInput);
            } catch (EmptyStatementException e) {
                printLine();
                System.out.println("Please type something >:(");
            } catch (NumberFormatException e) {
                printLine();
                System.out.println("Exception thrown: " + e);
                System.out.println("Please Enter A Valid Number");
            } catch (IndexOutOfBoundsException e) {
                printLine();
                System.out.println("Exception thrown: " + e);
                System.out.println("There is no such task!");
            } catch (EmptyArgumentException e) {
                printLine();
                System.out.println("Please enter a proper argument >:(");
            }
            finally {
                printLine();
            }
        }
    }

    public void endChat() {
        printStatement("goodbye");
    }

    public String[] processInput (String inputText) throws EmptyStatementException, IllegalArgumentException {
        // String processing, \\b is used to signal the end of a word
        String[] processedInput = inputText.split("\\b", 2);
        if (inputText.isEmpty()) {
            throw new EmptyStatementException();
        }
        // String Cleaning
        processedInput[0] = processedInput[0].trim().toLowerCase();
        processedInput[1] = processedInput[1].isEmpty() ? "" : processedInput[1];
        return processedInput;
    }

    public void processCommand (String[] input) throws EmptyArgumentException {
        String commandType = input[0];
        String commandArg = input[1];
        int taskIndex = 0;

        switch (commandType) {
        case "list":
            TaskList.printList();
            break;
        case "mark":
            taskIndex = parseInt(commandArg.trim()) - 1;
            TaskList.getTask(taskIndex).markDone();
            break;
        case "unmark":
            taskIndex = parseInt(commandArg.trim()) - 1;
            TaskList.getTask(taskIndex).markNotDone();
            break;
        case "bye":
            isExitStatus = true;
            break;
        case "todo":
            if (commandArg.isEmpty()) {
                throw new EmptyArgumentException();
            }
            Todo inputTodo = new Todo(commandArg);
            TaskList.addTask(inputTodo);
            break;
        case "deadline":
            if (commandArg.contains("/by")) {
                String[] newCommandArg = commandArg.split("/by", 2);
                Deadline inputDeadline = new Deadline(newCommandArg[0], newCommandArg[1]);
                TaskList.addTask(inputDeadline);
            } else {
                printLine();
                System.out.println("Please put a date!");
                printLine();
            }
            break;
        case "event":
            if (commandArg.contains("/from") && commandArg.contains("/to")) {
                String[] newCommandArg = commandArg.split("/from", 2);
                String[] newCommandArg2 = newCommandArg[1].split("/to", 2);
                Events inputEvent = new Events(newCommandArg[0], newCommandArg2[0], newCommandArg2[1]);
                TaskList.addTask(inputEvent);
            } else {
                printLine();
                System.out.println("Please put a correct time!");
                printLine();
            }
            break;
        default:
            printLine();
            System.out.println("Unknown Command, type something I know please!");
            printLine();
            break;
        }
    }
}

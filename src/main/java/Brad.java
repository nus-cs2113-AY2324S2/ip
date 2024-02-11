import java.util.Scanner;


public class Brad {
    private static List inputList = new List();
    private static final String SEPARATOR = "__________________________________________________________";

    public static void main(String[] args) {
        greetUser();
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String input = userInput.nextLine();
            String[] splitInput = input.split(" ",2);
            String command = splitInput[0];
            if (command.equals("bye")) {
                printOutput("Bye. Hope to see you again soon!");
                break;
            }
            switch (command) {
                case "list":
                    printOutput("Here are the tasks in your list:\n" + inputList.getList());
                    break;
                case "mark":
                    try {
                        doMarkAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                        printOutput("Uh oh. Please enter a number to mark the corresponding " +
                                "task as done");
                    } catch (invalidNumberException e) {
                        printOutput("No! >:( Exceeded existing list size of: \" + inputList.listSize() +\n" +
                                "Please enter a valid number.\n;");
                    }
                    break;
                case "unmark":
                    try {
                        doUnmarkAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                            printOutput("Uh oh. Please enter a number to mark the corresponding " +
                                    "task as done");
                        } catch (invalidNumberException e) {
                            printOutput("No! >:( Exceeded existing list size of: \" + inputList.listSize() +\n" +
                                    "Please enter a valid number.\n");
                    }
                    break;
                case "todo":
                    try {
                        doTodoAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                        printOutput("Hey, you can't give me an empty todo!");
                    }
                    break;
                case "deadline":
                    try {
                        doDeadlineAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                        printOutput("Hey, you can't give me an empty deadline with no " +
                                "specified due date!");
                    }
                    break;
                case "event":
                    try {
                        doEventAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                        printOutput("Hey, you can't give me an event with no start & end time!");
                    }
                    break;
                default:
                    printOutput("Huh?! Sorry I don't understand. T_T\n Please only enter valid commands: " +
                            "'list', 'mark', 'unmark', 'todo', 'deadline', 'event', 'bye'");
                    break;
            }
        }
    }

    private static void greetUser() {
        final String name = "Brad";
        System.out.println("Hello I am " + name + ".\n");
        System.out.println("How can I help you today?\n");
    }

    private static void printOutput(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    private static void doMarkAction(String input) throws
            emptyArgumentException, invalidNumberException {
        int taskNumber = Integer.parseInt(input);
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        if (taskNumber > inputList.listSize()) {
            throw new invalidNumberException();
        }
        inputList.markAsDone(taskNumber, true);
            String message = "Nice! I've marked this task as done: \n" + inputList.getTask(taskNumber);
            printOutput(message);
    }

    private static void doUnmarkAction(String input) throws
            emptyArgumentException, invalidNumberException {
        int taskNumber = Integer.parseInt(input);
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        if (taskNumber > inputList.listSize()) {
            throw new invalidNumberException();
        }
        inputList.markAsDone(taskNumber, false);
        String message = "Ok. I've marked this task as not done yet: \n" + inputList.getTask(taskNumber);
        printOutput(message);
    }

    private static void doTodoAction(String input) throws emptyArgumentException {
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        inputList.addToList(input, TaskType.TODO);
        int size = inputList.listSize();
        String message = "Got it. I've added this task:\n" + inputList.getTask(size)
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
    }

    private static void doDeadlineAction(String input) throws emptyArgumentException {
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        inputList.addToList(input, TaskType.DEADLINE);
        int size = inputList.listSize();
        String message = "Got it. I've added this task:\n" + inputList.getTask(size)
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
    }

    private static void doEventAction(String input) throws emptyArgumentException {
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        inputList.addToList(input, TaskType.EVENT);
        int size = inputList.listSize();
        String message = "Got it. I've added this task:\n" + inputList.getTask(size)
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
    }
}
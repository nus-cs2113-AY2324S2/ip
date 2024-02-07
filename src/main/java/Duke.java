import java.util.Scanner;

public class Duke {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK= "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_EXIT = "bye";

    private static final String MESSAGE_ADD = "added: ";
    private static final String MESSAGE_EMPTY_LIST = "Your list is empty.";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_LIST_HEADER = "Here are the tasks in your list:";
    private static final String MESSAGE_MARK = "Nice! I've marked this task as done:";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";
    private static final String MESSAGE_WELCOME = "Hello! I'm FredBot.\nWhat can I do for you?";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static Task[] allTasks;
    private static final int TASK_CAPACITY = 100;
    private static int count;

    public static void main(String[] args) {
        initTaskList();
        showWelcomeMessage();
        while (true) {
            String command = readUserInput();
            executeCommand(command);
        }
    }

    private static void executeList() {
        if (count == 0) {
            System.out.println(MESSAGE_EMPTY_LIST);
            return;
        }
        System.out.println(MESSAGE_LIST_HEADER);
        for (int i = 0; i < count; i++) {
            System.out.println((i+1) + ". " + allTasks[i].getStatusIcon() + " " + allTasks[i].description);
        }
    }

    private static void executeMark(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        allTasks[index].markAsDone();
        System.out.println(MESSAGE_MARK);
        System.out.println(allTasks[index].getStatusIcon() + " " + allTasks[index].description);
    }

    private static void executeUnmark(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        allTasks[index].unmarkAsDone();
        System.out.println(MESSAGE_UNMARK);
        System.out.println(allTasks[index].getStatusIcon() + " " + allTasks[index].description);
    }

    private static void executeExit() {
        System.out.println(MESSAGE_GOODBYE);
        System.exit(0);
    }

    private static void executeAdd(String input) {
        allTasks[count] = new Task(input);
        count++;
        System.out.println(MESSAGE_ADD + input);
    }

    private static void initTaskList() {
        allTasks = new Task[TASK_CAPACITY];
        count = 0;
    }

    private static void showWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
    }

    private static String readUserInput() {
        return SCANNER.nextLine();
    }

    private static void executeCommand(String input) {
        final String[] commandWordAndArgs = splitCommandWordAndArgs(input);
        final String commandWord = commandWordAndArgs[0];
        final String commandArgs = commandWordAndArgs[1];
        switch (commandWord) {
            case COMMAND_LIST:
                executeList();
                break;
            case COMMAND_MARK:
                executeMark(commandArgs);
                break;
            case COMMAND_UNMARK:
                executeUnmark(commandArgs);
                break;
            case COMMAND_EXIT:
                executeExit();
                break;
            default:
                executeAdd(input);
                break;
        }
    }

    private static String[] splitCommandWordAndArgs(String input) {
        final String[] split = input.split(" ", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" };
    }
}

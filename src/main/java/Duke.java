import java.util.Scanner;

public class Duke {
    private static Task[] allTasks;
    private static final int TASK_CAPACITY = 100;
    private static int count;

    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK= "mark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_EXIT = "bye";

    private static final String MESSAGE_ADD = "Got it. I've added this task:";
    private static final String MESSAGE_EMPTY_LIST = "Your list is empty.";
    private static final String MESSAGE_ERROR = "Sorry, I did not understand that.";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_LIST_HEADER = "Here are the tasks in your list:";
    private static final String MESSAGE_MARK = "Nice! I've marked this task as done:";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";
    private static final String MESSAGE_WELCOME = "Hello! I'm FredBot.\nWhat can I do for you?";

    private static final String PREFIX_BY = "/by";
    private static final String PREFIX_FROM = "/from";
    private static final String PREFIX_TO = "/to";

    private static final Scanner SCANNER = new Scanner(System.in);

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
            System.out.println((i+1) + ". " + allTasks[i].toString());
        }
    }

    private static void executeMark(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        allTasks[index].markAsDone();
        System.out.println(MESSAGE_MARK);
        System.out.println(allTasks[index].toString());
    }

    private static void executeUnmark(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        allTasks[index].unmarkAsDone();
        System.out.println(MESSAGE_UNMARK);
        System.out.println(allTasks[index].toString());
    }

    private static void executeExit() {
        System.out.println(MESSAGE_GOODBYE);
        System.exit(0);
    }

    private static void executeAddDeadline(String input) {
        String[] split = splitDeadlineAndDate(input);
        allTasks[count] = new Deadline(split[0], split[1]);
        echoTask();
    }

    private static String[] splitDeadlineAndDate(String input) {
        final int indexOfByPrefix = input.indexOf(PREFIX_BY);
        String[] deadlineAndDate = new String[2];
        deadlineAndDate[0] = input.substring(0,indexOfByPrefix).trim();
        deadlineAndDate[1] = removePrefix(input.substring(indexOfByPrefix), PREFIX_BY).trim();
        return deadlineAndDate;
    }

    private static void executeAddEvent(String input) {
        String[] split = splitEventAndDates(input);
        allTasks[count] = new Event(split[0], split[1], split[2]);
        echoTask();
    }

    private static String[] splitEventAndDates(String input) {
        final int indexOfFromPrefix = input.indexOf(PREFIX_FROM);
        final int indexOfToPrefix = input.indexOf(PREFIX_TO);
        String[] eventAndDates = new String[3];
        eventAndDates[0] = input.substring(0,indexOfFromPrefix).trim();
        eventAndDates[1] = removePrefix(input.substring(indexOfFromPrefix, indexOfToPrefix), PREFIX_FROM).trim();
        eventAndDates[2] = removePrefix(input.substring(indexOfToPrefix), PREFIX_TO).trim();
        return eventAndDates;
    }

    private static void executeAddTodo(String input) {
        allTasks[count] = new Todo(input);
        echoTask();
    }

    private static String removePrefix(String s, String prefix) {
        return s.replace(prefix, "");
    }

    private static void echoTask() {
        System.out.println(MESSAGE_ADD);
        System.out.println(allTasks[count].toString());
        count++;
        String task = (count == 1) ? " task " : " tasks ";
        System.out.println("Now you have " + count + task + "in the list.");
    }

    private static void executeError() {
        System.out.println(MESSAGE_ERROR);
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
            case COMMAND_DEADLINE:
                executeAddDeadline(commandArgs);
                break;
            case COMMAND_EVENT:
                executeAddEvent(commandArgs);
                break;
            case COMMAND_LIST:
                executeList();
                break;
            case COMMAND_MARK:
                executeMark(commandArgs);
                break;
            case COMMAND_TODO:
                executeAddTodo(commandArgs);
                break;
            case COMMAND_UNMARK:
                executeUnmark(commandArgs);
                break;
            case COMMAND_EXIT:
                executeExit();
                break;
            default:
                executeError();
                break;
        }
    }

    private static String[] splitCommandWordAndArgs(String input) {
        final String[] split = input.split(" ", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" };
    }
}

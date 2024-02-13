package natsu;

import natsu.task.Deadline;
import natsu.task.Event;
import natsu.task.Task;
import natsu.task.Todo;
import natsu.exception.InvalidCommandException;

import java.util.Scanner;

public class Natsu {

    private static final String NAME = "Natsu";
    private static final int MAX_TASKS = 100;
    private static final int MARK_COMMAND_LENGTH = 5;
    private static final int UNMARK_COMMAND_LENGTH = 7;
    private static final int TODO_COMMAND_LENGTH = 5;
    private static final int DEADLINE_COMMAND_PREFIX_LENGTH = 9;
    private static final int EVENT_COMMAND_PREFIX_LENGTH = 6;
    private static final String DEADLINE_INDICATOR = "/by";
    private static final String EVENT_START_INDICATOR = "/from";
    private static final String EVENT_END_INDICATOR = "/to";
    private static final Task[] list = new Task[MAX_TASKS];
    private static int taskCount = 0;

    private static void printGreeting() {
        printLine();
        System.out.println("     Hello! I'm " + NAME);
        System.out.println("     What can I do for you?");
        printLine();
    }

    private static void echoCommands() {
        try (Scanner input = new Scanner(System.in)) {
            boolean isActive = true;
            while (isActive) {
                String userInput = input.nextLine();
                isActive = processInput(userInput);
            }
        }
    }

    private static boolean processInput(String userInput) {
        switch (userInput) {
        case "bye":
            printExitMessage();
            return false;
        case "list":
            listTasks();
            break;
        default:
            handleTaskCommands(userInput);
        }
        return true;
    }

    private static void handleTaskCommands(String userInput) throws NumberFormatException {
        if (userInput.startsWith("mark ")) {
            int itemIndex = Integer.parseInt(userInput.substring(MARK_COMMAND_LENGTH)) - 1;
            if (itemIndex >= taskCount || itemIndex < 0) {
                throw new NumberFormatException();
            } else {
                list[itemIndex].markAsDone();
                printLine();
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + list[itemIndex].toString());
                printLine();
            }
        } else if (userInput.startsWith("unmark ")) {
            int itemIndex = Integer.parseInt(userInput.substring(UNMARK_COMMAND_LENGTH)) - 1;
            if (itemIndex >= taskCount || itemIndex < 0) {
                throw new NumberFormatException();
            } else {
                list[itemIndex].markAsUndone();
                printLine();
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       " + list[itemIndex].toString());
                printLine();
            }
        } else {
            try {
                addTask(userInput);
            } catch (InvalidCommandException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }
    }

    private static void addTask(String userInput) throws InvalidCommandException {
        if (userInput.startsWith("todo")) {
            if (userInput.length() <= TODO_COMMAND_LENGTH) {
                throw new InvalidCommandException("     I'm terribly sorry, but the description of a todo cannot be empty. Please try again!");
            }
            String todoDescription = userInput.substring(TODO_COMMAND_LENGTH).trim();
            list[taskCount++] = new Todo(todoDescription);
        } else if (userInput.startsWith("deadline")) {
            if (!userInput.contains(DEADLINE_INDICATOR)) {
                throw new InvalidCommandException("     I'm terribly sorry, but do add '/by' along with the deadline of the task. Please try again!");
            } else if (userInput.indexOf(DEADLINE_INDICATOR) == DEADLINE_COMMAND_PREFIX_LENGTH) {
                throw new InvalidCommandException("     I'm terribly sorry, but the description of a deadline cannot be empty. Please try again!");
            } else if (userInput.indexOf(DEADLINE_INDICATOR) + DEADLINE_INDICATOR.length() >= userInput.length()) {
                throw new InvalidCommandException("     I'm terribly sorry, but the deadline date/time cannot be empty. Please try again!");
            } else {
                int byIndex = userInput.indexOf(DEADLINE_INDICATOR);
                String deadlineDescription = userInput.substring(DEADLINE_COMMAND_PREFIX_LENGTH, byIndex).trim();
                String deadlineBy = userInput.substring(byIndex + DEADLINE_INDICATOR.length() + 1).trim();
                list[taskCount++] = new Deadline(deadlineDescription, deadlineBy);
            }
        } else if (userInput.startsWith("event")) {
            if (!userInput.contains(EVENT_START_INDICATOR) || !userInput.contains(EVENT_END_INDICATOR)) {
                throw new InvalidCommandException("     I'm terribly sorry, but do add '/from' and '/to' along with the event details. Please try again!");
            }
            if (userInput.indexOf(EVENT_START_INDICATOR) == EVENT_COMMAND_PREFIX_LENGTH || userInput.indexOf(EVENT_END_INDICATOR) == EVENT_COMMAND_PREFIX_LENGTH) {
                throw new InvalidCommandException("     I'm terribly sorry, but the description of an event cannot be empty. Please try again!");
            }
            if (userInput.indexOf(EVENT_START_INDICATOR) + EVENT_START_INDICATOR.length() >= userInput.length() || userInput.indexOf(EVENT_END_INDICATOR) + EVENT_END_INDICATOR.length() == userInput.length()) {
                throw new InvalidCommandException("     I'm terribly sorry, but the details of an event cannot be empty. Please try again!");
            }
            int fromIndex = userInput.indexOf(EVENT_START_INDICATOR);
            int toIndex = userInput.indexOf(EVENT_END_INDICATOR);
            String eventDescription = userInput.substring(EVENT_COMMAND_PREFIX_LENGTH, fromIndex).trim();
            String eventFrom = userInput.substring(fromIndex + EVENT_START_INDICATOR.length() + 1, toIndex).trim();
            String eventTo = userInput.substring(toIndex + EVENT_END_INDICATOR.length() + 1).trim();
            list[taskCount++] = new Event(eventDescription, eventFrom, eventTo);
        } else {
            throw new InvalidCommandException("     I'm terribly sorry, but I do not know what that means. Please try again!");
        }
        printTaskAddedConfirmation();
    }

    private static void printTaskAddedMessage(String userInput) {
        printLine();
        System.out.println("     added: " + userInput);
        printLine();
    }

    private static void printTaskAddedConfirmation() {
        printLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + list[taskCount - 1].toString());
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        printLine();
    }


    private static void listTasks() {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + list[i].toString());
        }
        printLine();
    }

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private static void printExitMessage() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        printGreeting();
        echoCommands();
    }
}

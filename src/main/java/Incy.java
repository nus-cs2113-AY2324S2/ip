import java.util.Scanner;

public class Incy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        printWelcomeMessage();

        while (true) {
            String input = scanner.nextLine();
            if ("bye".equalsIgnoreCase(input)) {
                break;
            }
            processInput(input, taskManager);
        }

        printGoodbyeMessage();
        scanner.close();
    }

    private static void printWelcomeMessage() {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN +
                "Oi bruv! I'm\n" + Constants.LOGO +
                "Wotcha need from me today?\n" + Constants.LINE_STRING_BOTTOM);
    }

    private static void printGoodbyeMessage() {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN +
                "Cya later mate!\n" + Constants.LINE_STRING_BOTTOM);
    }

    private static void processInput(String input, TaskManager taskManager) {
        try {
            if ("list".equalsIgnoreCase(input)) {
                taskManager.handleListCommand();
            } else if (input.startsWith("mark ")) {
                taskManager.handleMarkCommand(input, true);
            } else if (input.startsWith("unmark ")) {
                taskManager.handleMarkCommand(input, false);
            } else {
                taskManager.handleAddTask(input);
            }
        } catch (IncyException e) {
            System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_RED + e.getMessage() + "\n" + Constants.LINE_STRING_BOTTOM);
        }
    }
}

class TaskManager {
    private Task[] tasks = new Task[Constants.MAX_TASKS];
    private int taskCount = 0;

    void handleListCommand() {
        System.out.println(Constants.LINE_STRING_BOTTOM);
        if (taskCount == 0) {
            System.out.println(Constants.ANSI_RED + "Blimey, your list is empty, innit?");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(Constants.ANSI_CYAN + (i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println(Constants.LINE_STRING_BOTTOM);
    }

    void handleMarkCommand(String input, boolean markAsDone) {
        int index = Integer.parseInt(input.substring(markAsDone ? 5 : 7)) - 1;
        if (isValidIndex(index)) {
            tasks[index].setDone(markAsDone);
            System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Smashed it, this one's sorted!:\n  " + tasks[index] + "\n" + Constants.LINE_STRING_BOTTOM);
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < taskCount;
    }

    void handleAddTask(String input) throws IncyException {
        if (taskCount >= tasks.length) {
            System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_RED + "The list chocked a block, mate. Can't shove more stuff in it!\n" + Constants.LINE_STRING_BOTTOM);
            return;
        }

        Task newTask = TaskFactory.createTask(input);
        tasks[taskCount++] = newTask;
        printTaskAddedMessage(newTask);
    }

    private void printTaskAddedMessage(Task task) {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN +
                "Sorted! Your task's in the bag, innit mate:\n" +
                "  " + task + "\n" +
                "You're now juggling " + taskCount + " tasks on your list, innit.\n" +
                Constants.LINE_STRING_BOTTOM);
    }
}


class IncyException extends Exception {
    public IncyException(String message) {
        super(message);
    }
}

class TaskFactory {
    static Task createTask(String input) throws IncyException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String taskInfo = parts.length > 1 ? parts[1] : "";

        switch (command.toLowerCase()) {
            case "todo":
                if (taskInfo.isEmpty()) {
                    throw new IncyException("Oi! You can't have a todo with no description!");
                }
                return new Todo(taskInfo);
            case "deadline":
                return createDeadline(taskInfo);
            case "event":
                return createEvent(taskInfo);
            default:
                throw new IncyException("Hol' up bruv, I dun get what that means...");
        }
    }

    private static Task createDeadline(String taskInfo) {
        String[] deadlineParts = taskInfo.split(" /by ", 2);
        if (deadlineParts.length < 2) {
            printFormatError("You've mucked up the deadline format. Do it like 'deadline [task] /by [date/time]', yeah?");
            return null;
        }
        return new Deadline(deadlineParts[0], deadlineParts[1]);
    }

    private static Task createEvent(String taskInfo) {
        String[] eventParts = taskInfo.split(" /from ", 2);
        String[] eventTime = eventParts.length > 1 ? eventParts[1].split(" /to ", 2) : new String[0];
        if (eventParts.length < 2 || eventTime.length < 2) {
            printFormatError("You've bungled the event format. It's gotta be 'event [task] /from [start time] /to [end time]', innit?");
            return null;
        }
        return new Event(eventParts[0], eventTime[0], eventTime[1]);
    }

    private static void printFormatError(String errorMessage) {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_RED + "Error: " + errorMessage + "\n" +
                Constants.LINE_STRING_BOTTOM);
    }
}
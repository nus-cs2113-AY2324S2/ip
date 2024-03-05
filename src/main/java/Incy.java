import java.util.ArrayList;
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
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Oi bruv! I'm\n" + Constants.LOGO + Constants.ANSI_CYAN + "Wotcha need from me today?\n" + Constants.LINE_STRING_BOTTOM);
    }

    private static void printGoodbyeMessage() {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Cya later mate!\n" + Constants.LINE_STRING_BOTTOM);
    }

    private static void processInput(String input, TaskManager taskManager) {
        try {
            if ("list".equalsIgnoreCase(input)) {
                taskManager.handleListCommand();
            } else if (input.startsWith("mark ")) {
                taskManager.handleMarkCommand(input, true);
            } else if (input.startsWith("unmark ")) {
                taskManager.handleMarkCommand(input, false);
            } else if (input.startsWith("delete ")) {
                taskManager.handleDeleteCommand(input);
            } else {
                taskManager.handleAddTask(input);
            }
        } catch (IncyException e) {
            System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_RED + e.getMessage() + "\n" + Constants.LINE_STRING_BOTTOM);
        }
    }
}

class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    void handleListCommand() {
        System.out.println(Constants.LINE_STRING_BOTTOM);
        if (tasks.isEmpty()) {
            System.out.println(Constants.ANSI_RED + "Blimey, your list is empty, innit?");
        } else {
            int index = 1;
            for (Task task : tasks) {
                System.out.println(Constants.ANSI_CYAN + index++ + ". " + task);
            }
        }
        System.out.println(Constants.LINE_STRING_BOTTOM);
    }

    void handleMarkCommand(String input, boolean markAsDone) throws IncyException {
        if (tasks.isEmpty()) {
            throw new IncyException("Nah, mate, nothin' to tick off, yer list's empty!");
        }

        int index = Integer.parseInt(input.substring(markAsDone ? 5 : 7)) - 1;
        if (!isValidIndex(index)) {
            throw new IncyException("Gimme a legit number, will ya? That one's not on.");
        }

        tasks.get(index).setDone(markAsDone);
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Banging! This one's sorted!:\n  " + tasks.get(index) + "\n" + Constants.LINE_STRING_BOTTOM);
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    void handleAddTask(String input) throws IncyException {
        Task newTask = TaskFactory.createTask(input);
        if (newTask != null) {
            tasks.add(newTask);
            printTaskAddedMessage(newTask);
        }
    }

    void handleDeleteCommand(String input) throws IncyException {
        if (tasks.isEmpty()) {
            throw new IncyException("Oi, nothin' to bin 'ere, yer list's bare!");
        }

        int index = Integer.parseInt(input.substring(7)) - 1;
        if (!isValidIndex(index)) {
            throw new IncyException("That ain't a legit number, try again with a proper one, yeah?");
        }

        Task deletedTask = tasks.get(index);
        removeTaskAt(index);
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN +
                "Safe! I've dashed this task:\n" +
                "  " + deletedTask + "\n" +
                "Now you've got " + tasks.size() + " tasks on your plate.\n" +
                Constants.LINE_STRING_BOTTOM);
    }

    private void removeTaskAt(int index) {
        tasks.remove(index);
    }

    private void printTaskAddedMessage(Task task) {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN +
                "Sorted! Your task's in the bag, innit mate:\n" +
                "  " + task + "\n" +
                "You're now juggling " + tasks.size() + " tasks on your list, innit.\n" +
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
                throw new IncyException("Hol' up bruv, I dun get what that means..., try typin 'todo', 'deadline', or 'event' first, yeah?");
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
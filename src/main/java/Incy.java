import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Incy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        ensureDataFolderExists();
        taskManager.loadTasksFromFile();

        printWelcomeMessage();

        while (true) {
            String input = scanner.nextLine();
            if ("bye".equalsIgnoreCase(input)) {
                break;
            }
            processInput(input, taskManager);
        }

        taskManager.saveTasksToFile();
        printGoodbyeMessage();
        scanner.close();
    }

    private static void ensureDataFolderExists() {
        Path dataFolderPath = Paths.get(Constants.DATA_FOLDER);
        if (!Files.exists(dataFolderPath)) {
            try {
                Files.createDirectory(dataFolderPath);
            } catch (IOException e) {
                System.err.println("Failed to create data folder: " + e.getMessage());
            }
        }
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
            } else if (input.startsWith("list by ")) {
                taskManager.handleListByDateCommand(input);
            } else if (input.startsWith("find ")) {
                taskManager.handleFindCommand(input);
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
        saveTasksToFile();
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    void handleAddTask(String input) throws IncyException {
        Task newTask = TaskFactory.createTask(input);
        if (newTask != null) {
            tasks.add(newTask);
            printTaskAddedMessage(newTask);
            saveTasksToFile();
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
        saveTasksToFile();
    }

    void handleFindCommand(String input) throws IncyException {
        if (input.length() <= 5) {
            throw new IncyException("Oi, you forgot to tell me what to find, mate!");
        }
        String keyword = input.substring(5).trim().toLowerCase();
        System.out.println(Constants.LINE_STRING_TOP);
        boolean found = false;
        int index = 1;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                System.out.println(Constants.ANSI_CYAN + "  " + index++ + ". " + task);
                found = true;
            }
        }
        if (!found) {
            System.out.println(Constants.ANSI_RED + "No tasks found with the keyword '" + keyword + "', bruv.");
        }
        System.out.println(Constants.LINE_STRING_BOTTOM);
    }

    void handleListByDateCommand(String input) throws IncyException {
        String dateString = input.substring(9).trim();
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new IncyException("Invalid date format, mate. Use 'yyyy-MM-dd' format.");
        }
        System.out.println(Constants.LINE_STRING_BOTTOM);
        boolean found = false;
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(date)) {
                    System.out.println(Constants.ANSI_CYAN + "- " + deadline);
                    found = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStart().toLocalDate().equals(date) || event.getEnd().toLocalDate().equals(date)) {
                    System.out.println(Constants.ANSI_CYAN + "- " + event);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println(Constants.ANSI_RED + "No tasks found for the specified date, bruv.");
        }
        System.out.println(Constants.LINE_STRING_BOTTOM);
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

    void loadTasksFromFile() {
        Path dataFilePath = Paths.get(Constants.DATA_FOLDER, Constants.DATA_FILE);
        if (Files.exists(dataFilePath)) {
            try {
                Scanner scanner = new Scanner(dataFilePath);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = TaskFactory.createTaskFromLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                scanner.close();
            } catch (IOException e) {
                TaskFactory.printFormatError("Failed to load tasks from file: " + e.getMessage());
            }
        }
    }

    void saveTasksToFile() {
        Path dataFilePath = Paths.get(Constants.DATA_FOLDER, Constants.DATA_FILE);
        try {
            FileWriter writer = new FileWriter(dataFilePath.toFile());
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TaskFactory.printFormatError("Failed to save tasks to file: " + e.getMessage());
        }
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

    static Task createTaskFromLine(String line) {
        String[] parts = line.split(" \\| ", 2);
        if (parts.length < 2) {
            return null;
        }

        String typeCode = parts[0];
        String taskData = parts[1];

        switch (typeCode) {
            case "T":
                return new Todo(taskData);
            case "D":
                return createDeadlineFromLine(taskData);
            case "E":
                return createEventFromLine(taskData);
            default:
                return null;
        }
    }

    private static Task createDeadline(String taskInfo) {
        String[] deadlineParts = taskInfo.split(" /by ", 2);
        if (deadlineParts.length < 2) {
            printFormatError("You've mucked up the deadline format. Do it like 'deadline [task] /by [yyyy-MM-dd HHmm]', yeah?");
            return null;
        }
        try {
            return new Deadline(deadlineParts[0], deadlineParts[1]);
        } catch (DateTimeParseException e) {
            printFormatError("Invalid date format for deadline. Use 'yyyy-MM-dd HHmm' format, mate.");
            return null;
        }
    }
    
    private static Task createEvent(String taskInfo) {
        String[] eventParts = taskInfo.split(" /from ", 2);
        String[] eventTime = eventParts.length > 1 ? eventParts[1].split(" /to ", 2) : new String[0];
        if (eventParts.length < 2 || eventTime.length < 2) {
            printFormatError("You've bungled the event format. It's gotta be 'event [task] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]', innit?");
            return null;
        }
        try {
            return new Event(eventParts[0], eventTime[0], eventTime[1]);
        } catch (DateTimeParseException e) {
            printFormatError("Invalid date format for event. Use 'yyyy-MM-dd HHmm' format, mate.");
            return null;
        }
    }

    private static Deadline createDeadlineFromLine(String line) {
        String[] parts = line.split(" \\| ", 3);
        if (parts.length < 3) {
            return null;
        }
        return new Deadline(parts[1], parts[2]);
    }

    private static Event createEventFromLine(String line) {
        String[] parts = line.split(" \\| ", 4);
        if (parts.length < 4) {
            return null;
        }
        return new Event(parts[1], parts[2], parts[3]);
    }

    static void printFormatError(String errorMessage) {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_RED + "Error: " + errorMessage + "\n" +
                Constants.LINE_STRING_BOTTOM);
    }
}
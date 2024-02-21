import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DavinciBot {
    private static final int SPLIT_INTO_TWO_PARTS = 2;
    private static final String DATA_FILE_PATH = "C:\\cs2113 individual project\\ip\\data\\DavinciBot.txt";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String DELETE = "delete";

    public static List<Task> taskList = new ArrayList<>();

    private static void userCommand() {
        while (true) {
            String userInput = Ui.getUserInput();

            if (userInput.isEmpty()) {
                Ui.printMessage("... are you mute?");
                continue;
            }

            if (userInput.equalsIgnoreCase(BYE)) {
                Ui.printMessage("Goodbye... It may be a mere few seconds for you but an eternity for me.");
                break;
            } else if (userInput.equalsIgnoreCase(LIST)) {
                Ui.displayTaskList(taskList);
            } else if (userInput.toLowerCase().startsWith(MARK)) {
                completeTask(userInput);
            } else if (userInput.toLowerCase().startsWith(UNMARK)) {
                unmarkTask(userInput);
            } else if (userInput.toLowerCase().startsWith(DELETE)) {
                deleteTask(userInput);
            } else if (userInput.toLowerCase().startsWith(TODO) ||
                    userInput.toLowerCase().startsWith(DEADLINE) ||
                    userInput.toLowerCase().startsWith(EVENT)) {
                getTasks(userInput);
            } else {
                Ui.printMessage("Bro, say something that I can understand.");
            }
        }
    }

    private static void completeTask(String userInput) {
        try {
            String[] parts = userInput.split(" ", SPLIT_INTO_TWO_PARTS);
            checkIfTaskCanBeCompleted(parts);
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printMessage("Error: Invalid task index format.");
        }
    }

    private static void checkIfTaskCanBeCompleted(String[] parts) throws DavinciException {
        if (parts.length > 1) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).markAsDone();
                Ui.printMessage("Nice job! I've marked this task as done :D");
                Ui.displayTaskList(taskList);
                writeFile();
            } else {
                throw new DavinciException("Invalid task index.");
            }
        } else {
            throw new DavinciException("Please specify the task index to complete.");
        }
    }

    private static void unmarkTask(String userInput) {
        try {
            String[] parts = userInput.split(" ", SPLIT_INTO_TWO_PARTS);
            checkIfTaskCanBeUnmarked(parts);
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printMessage("Error: Invalid task index format.");
        }
    }

    private static void checkIfTaskCanBeUnmarked(String[] parts) throws DavinciException {
        if (parts.length > 1) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).markAsNotDone();
                Ui.printMessage("OK, I've marked this task as not done, but stop being lazy!");
                Ui.displayTaskList(taskList);
                writeFile();
            } else {
                throw new DavinciException("Invalid task index.");
            }
        } else {
            throw new DavinciException("Please specify the task index to unmark.");
        }
    }

    private static void deleteTask(String userInput) {
        try {
            String[] parts = userInput.split(" ", SPLIT_INTO_TWO_PARTS);
            ableToDelete(parts);
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            Ui.printMessage("Error: Invalid task index format.");
        }
    }

    private static void ableToDelete(String[] parts) throws DavinciException {
        if (parts.length > 1) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.remove(taskIndex);
                Ui.printMessage("Noted. I've removed this task:");
                Ui.displayTaskList(taskList);
                writeFile();
            } else {
                throw new DavinciException("Invalid task index.");
            }
        } else {
            throw new DavinciException("Please specify the task index to delete.");
        }
    }

    private static Task[] getTasks(String userInput) {
        try {
            Scanner taskScanner = new Scanner(userInput);
            String taskType = taskScanner.next().toLowerCase();
            if (!taskScanner.hasNext()) {
                throw new DavinciException("Come on man, specify the " + taskType + " task.");
            }
            String description = taskScanner.nextLine().trim();
            switch (taskType) {
            case TODO:
                return executeTodoTask(description);
            case DEADLINE:
                return executeDeadlineTask(description);
            case EVENT:
                return executeEventTask(description);
            default:
                throw new DavinciException("Unknown task type. Please use 'todo', 'deadline', or 'event'.");
            }
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
            return taskList.toArray(new Task[0]);
        }
    }

    private static Task[] executeEventTask(String description) throws DavinciException {
        String[] eventParts = description.split("/from", SPLIT_INTO_TWO_PARTS);
        if (eventParts.length == 2) {
            String[] eventTimeParts = eventParts[1].split("/to", SPLIT_INTO_TWO_PARTS);
            if (eventTimeParts.length == 2) {
                taskList.add(new Event(eventParts[0].trim(), eventTimeParts[0].trim(), eventTimeParts[1].trim()));
                Ui.echoTask(taskList);
            } else {
                throw new DavinciException("Come on man. Please use: event <description> /from <start> /to <end>");
            }
        } else {
            throw new DavinciException("Whatcha' doing bruh, listen. Please use: event <description> /from <start> /to <end>");
        }
        return taskList.toArray(new Task[0]);
    }

    private static Task[] executeDeadlineTask(String description) throws DavinciException {
        String[] deadlineParts = description.split("/by", SPLIT_INTO_TWO_PARTS);
        if (deadlineParts.length == 2) {
            taskList.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            Ui.echoTask(taskList);
        } else {
            throw new DavinciException("Crappy formatting. Please use: deadline <description> /by <deadline>");
        }
        return taskList.toArray(new Task[0]);
    }

    private static Task[] executeTodoTask(String description) {
        taskList.add(new Todo(description));
        Ui.echoTask(taskList);
        return taskList.toArray(new Task[0]);
    }

    private static void readFile() {
        try {
            List<String> lines = DavinciFileHandler.readFile(DATA_FILE_PATH);
            taskList.clear();

            for (String line : lines) {
                Task task = readLine(line);
                if (task != null && !containsTask(taskList, task)) {
                    taskList.add(task);
                    Ui.printMessage("Got it. I've added a task from the file:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    Ui.displayTaskList(taskList);
                }
            }
        } catch (IOException e) {
            Ui.printMessage("Error reading file: " + e.getMessage());
        } catch (DavinciException e) {
            Ui.printMessage("Error: " + e.getMessage());
        }
    }

    private static void writeFile() {
        try{
            List<String> lines = new ArrayList<>();
            for (Task task : taskList) {
                lines.add(task.toFileString());
            }
            DavinciFileHandler.writeFile(DATA_FILE_PATH, lines);
        } catch (IOException e) {
            Ui.printMessage("Error writing file: " + e.getMessage());
        }
    }

    private static boolean containsTask(List<Task> tasks, Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    private static Task readLine(String line) throws DavinciException {
        try {
            String[] tokens = line.split("/");
            String command = tokens[0].toUpperCase();
            boolean isDone = tokens[tokens.length - 1].equals("1");
            Task newTask = commandCases(command, tokens);
            if (newTask == null) return null;
            if (isDone) {
                newTask.markAsDone();
            }
            if (!taskExists(newTask)) {
                return newTask;
            }
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DavinciException("Corrupted file");
        }
    }

    private static Task commandCases(String command, String[] tokens) {
        Task newTask;
        switch (command) {
        case "TODO":
            newTask = new Todo(tokens[1]);
            break;
        case "DEADLINE":
            newTask = new Deadline(tokens[1], tokens[2]);
            break;
        case "EVENT":
            newTask = new Event(tokens[1], tokens[2], tokens[3]);
            break;
        default:
            System.out.println("Unknown task type: " + command);
            return null;
        }
        return newTask;
    }

    private static boolean taskExists(Task newTask) {
        for (Task existingTask : taskList) {
            if (existingTask.equals(newTask)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        readFile();
        Ui.printStartingMessage();
        userCommand();
        writeFile();

        scanner.close();
    }
}

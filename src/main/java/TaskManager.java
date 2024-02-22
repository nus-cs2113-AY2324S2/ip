import Exceptions.InvalidDeadlineFormatException;
import Exceptions.InvalidEventFormatException;
import Exceptions.InvalidTodoFormatException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskManager {
    //private static final String FILE_PATH = "./data/tasks.txt";
    private static final String FILE_PATH = "." + File.separator + "data" + File.separator + "tasks.txt";

    public static final int DEADLINE_BEGIN_INDEX = 8;
    public static final int EVENT_BEGIN_INDEX = 5;
    public static final int TODO_BEGIN_INDEX = 4;
    public static final int EVENT_MAX_PARTS = 3;
    public static final int DEADLINE_MAX_PARTS = 2;
    private static final int MAX_TASKS = 100;
    public static final int INDEX_OFFSET = 1;
    public static final int PART_0 = 0;
    public static final int PART_1 = 1;
    public static final int PART_2 = 2;
    public static final int START_INDEX = 0;
    private Task[] taskList = new Task[MAX_TASKS];
    private int index = 0;
    UserInterface userInterface = new UserInterface();

    public void addTask(String taskDescription) {
        String taskType = taskDescription.split(" ")[START_INDEX];
        try {
            switch (taskType) {
            case "deadline":
                addDeadlineTask(taskDescription);
                break;
            case "event":
                addEventTask(taskDescription);
                break;
            case "todo":
                addTodoTask(taskDescription);
                break;
            default:
                throw new NoSuchMethodException();
            }
        } catch (NoSuchMethodException e) {
            userInterface.printInvalidTaskType(taskDescription);
        } catch (InvalidDeadlineFormatException e) {
            userInterface.printInvalidDeadlineFormat(e);
        } catch (InvalidTodoFormatException e) {
            userInterface.printInvalidTodoFormat(e);
        } catch (InvalidEventFormatException e) {
            userInterface.printInvalidEventFormat(e);
        }
    }

    private void addDeadlineTask(String taskDescription) throws InvalidDeadlineFormatException {
        String[] taskDetails = taskDescription.substring(DEADLINE_BEGIN_INDEX).split("/by");
        if (taskDetails.length == DEADLINE_MAX_PARTS) {
            taskList[index] = new Deadline(taskDetails[PART_0].trim(), taskDetails[PART_1].trim());
            index = Math.min(index + INDEX_OFFSET, MAX_TASKS);
            userInterface.printTaskAdded(taskList[index - INDEX_OFFSET], index);
        } else {
            throw new InvalidDeadlineFormatException("Invalid deadline format.");
        }
    }

    private void addEventTask(String taskDescription) throws InvalidEventFormatException {
        String[] taskDetails = taskDescription.substring(EVENT_BEGIN_INDEX).split("/from|/to");
        if (taskDetails.length == EVENT_MAX_PARTS) {
            taskList[index] = new Event(taskDetails[PART_0].trim(), taskDetails[PART_1].trim(),
                    taskDetails[PART_2].trim());
            index = Math.min(index + INDEX_OFFSET, MAX_TASKS);
            userInterface.printTaskAdded(taskList[index - INDEX_OFFSET], index);
        } else {
            throw new InvalidEventFormatException("Invalid event format. ");
        }
    }

    private void addTodoTask(String taskDescription) throws InvalidTodoFormatException {
        String taskDetails = taskDescription.substring(TODO_BEGIN_INDEX).trim();
        if (!taskDetails.isEmpty()) {
            taskList[index] = new Todo(taskDetails);
            index = Math.min(index + INDEX_OFFSET, MAX_TASKS);
            userInterface.printTaskAdded(taskList[index - INDEX_OFFSET], index);
        } else {
            throw new InvalidTodoFormatException("Invalid todo format. ");
        }
    }

    public void markTask(int taskIndex) throws IndexOutOfBoundsException {

        if (taskIndex >= index || taskIndex < START_INDEX) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index for marking: " + (taskIndex + INDEX_OFFSET));
        }
        if (taskList[taskIndex].isDone) {
            userInterface.printTaskAlreadyMarked("Task is already marked as done");
        } else {
            taskList[taskIndex].setAsDone();
            userInterface.printTaskMarked(taskList[taskIndex]);
        }

    }

    public void unmarkTask(int taskIndex) throws IndexOutOfBoundsException {

        if (taskIndex >= index || taskIndex < START_INDEX) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index for unmarking: " + (taskIndex + INDEX_OFFSET));
        }

        if (!taskList[taskIndex].isDone) {
            userInterface.printTaskAlreadyUnmarked("Task is already marked as undone");
        } else {
            taskList[taskIndex].setAsNotDone();
            userInterface.printTaskUnmarked(taskList[taskIndex]);
        }
    }

    public void printTaskList() {
        userInterface.printTaskList(taskList, index);
    }

    public TaskManager() {
        loadTasksFromFile();
    }

    private void loadTasksFromFile() {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating tasks file: " + e.getMessage() + "\n \n"
                        + "Your Tasks wont be written to the file");
                return;
            }
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromString(line);
                if (task != null) {
                    taskList[index] = task;
                    index++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage() + "\n"
                    + "Your Tasks wont be written to the file");
        }
    }

    private Task parseTaskFromString(String line) {
        Task task = null;
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                System.out.println("Unknown task type: " + taskType);
            }
            if (task != null && isDone) {
                task.setAsDone();
            }
        } else {
            System.out.println("Invalid task format: " + line);
        }
        return task;
    }

    public void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (int i = 0; i < index; i++) {
                writer.write(taskList[i].toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

}

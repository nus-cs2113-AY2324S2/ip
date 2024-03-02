package Helper;

import Exceptions.*;

import java.util.ArrayList;
import java.io.File;

/**
 * The TaskManager class manages tasks by providing methods to add, delete,
 * mark, unmark, and print tasks.
 * It also calls functions from Storage class to handle loading tasks from
 * a file and saving tasks to a file.
 */

public class TaskManager {
    private static final String FILE_PATH = "." + File.separator + "data" + File.separator + "tasks.txt";

    public static final int DEADLINE_BEGIN_INDEX = 8;
    public static final int EVENT_BEGIN_INDEX = 5;
    public static final int TODO_BEGIN_INDEX = 4;
    public static final int EVENT_MAX_PARTS = 3;
    public static final int DEADLINE_MAX_PARTS = 2;
    public static final int INDEX_OFFSET = 1;
    public static final int PART_0 = 0;
    public static final int PART_1 = 1;
    public static final int PART_2 = 2;
    public static final int START_INDEX = 0;
    private ArrayList<Task> taskList = new ArrayList<>();
    private int index = 0;
    UserInterface userInterface = new UserInterface();

    /**
     * Adds a deadline task to the task list.
     *
     * @param taskDescription The description of the deadline task.
     * @throws InvalidDeadlineFormatException If the deadline format is invalid.
     */

    protected void addDeadlineTask(String taskDescription) throws InvalidDeadlineFormatException {
        String[] taskDetails = taskDescription.substring(DEADLINE_BEGIN_INDEX).split("/by");
        if (taskDetails.length == DEADLINE_MAX_PARTS) {
            index += INDEX_OFFSET;
            taskList.add(new Deadline(taskDetails[PART_0].trim(), taskDetails[PART_1].trim()));
            userInterface.printTaskAdded(taskList.get(index - INDEX_OFFSET), index);
        } else {
            throw new InvalidDeadlineFormatException("Invalid deadline format.");
        }
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param taskDescription The description of the Event task.
     * @throws InvalidEventFormatException If the Event format is invalid.
     */

    protected void addEventTask(String taskDescription) throws InvalidEventFormatException {
        String[] taskDetails = taskDescription.substring(EVENT_BEGIN_INDEX).split("/from|/to");
        if (taskDetails.length == EVENT_MAX_PARTS) {
            index += INDEX_OFFSET;
            taskList.add(new Event(taskDetails[PART_0].trim(), taskDetails[PART_1].trim(),
                    taskDetails[PART_2].trim()));
            userInterface.printTaskAdded(taskList.get(index - INDEX_OFFSET), index);
        } else {
            throw new InvalidEventFormatException("Invalid event format. ");
        }
    }

    /**
     * Adds a Todo task to the task list.
     *
     * @param taskDescription The description of the deadline task.
     * @throws InvalidTodoFormatException If the deadline format is invalid.
     */

    protected void addTodoTask(String taskDescription) throws InvalidTodoFormatException {
        String taskDetails = taskDescription.substring(TODO_BEGIN_INDEX).trim();
        if (!taskDetails.isEmpty()) {
            index += INDEX_OFFSET;
            taskList.add(new Todo(taskDetails));
            userInterface.printTaskAdded(taskList.get(index - INDEX_OFFSET), index);
        } else {
            throw new InvalidTodoFormatException("Invalid todo format. ");
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the task index is out of bounds.
     */

    public void deleteTask(int taskIndex) throws IndexOutOfBoundsException {
        if (taskIndex < index || taskIndex >= START_INDEX) {
            String taskRemoved = taskList.get(taskIndex).toString();
            taskList.remove(taskIndex);
            index -= INDEX_OFFSET;
            userInterface.printTaskRemoved(taskRemoved, index);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds for length " +
                    index);
        }
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex The index of the task to be marked.
     * @throws IndexOutOfBoundsException If the task index is out of bounds.
     */

    public void markTask(int taskIndex) throws IndexOutOfBoundsException {

        if (taskIndex >= index || taskIndex < START_INDEX) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index for marking: " + (taskIndex + INDEX_OFFSET));
        }
        if (taskList.get(taskIndex).isDone) {
            userInterface.printTaskAlreadyMarked("Task is already marked as done");
        } else {
            taskList.get(taskIndex).setAsDone();
            userInterface.printTaskMarked(taskList.get(taskIndex));
        }

    }

    /**
     * Marks a task as not done.
     *
     * @param taskIndex The index of the task to be unmarked.
     * @throws IndexOutOfBoundsException If the task index is out of bounds.
     */

    public void unmarkTask(int taskIndex) throws IndexOutOfBoundsException {

        if (taskIndex >= index || taskIndex < START_INDEX) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index for unmarking: " + (taskIndex + INDEX_OFFSET));
        }

        if (!taskList.get(taskIndex).isDone) {
            userInterface.printTaskAlreadyUnmarked("Task is already marked as undone");
        } else {
            taskList.get(taskIndex).setAsNotDone();
            userInterface.printTaskUnmarked(taskList.get(taskIndex));
        }
    }

    /**
     * Prints the list of tasks.
     */

    public void printTaskList() {
        userInterface.printTaskList(taskList, index);
    }

    /**
     * Constructs a TaskManager object and loads tasks from the file.
     */

    public TaskManager() {
        loadTasksFromFile();
    }

    /**
     * Loads tasks from the file stored at FILE_PATH.
     * Calls helping function from Storage class to load data
     */

    private void loadTasksFromFile() {
        Storage storage = new Storage(FILE_PATH);
        try {
            ArrayList<Task> tasks = storage.load();
            taskList.addAll(tasks);
            index = tasks.size();
        } catch (LoadFileException e) {
            userInterface.printLoadFileError(e);
        }
    }

    /**
     * Saves tasks to the file at the location specified in FILE_PATH.
     * Calls helping function from Storage class to save data
     */

    public void saveTasksToFile() {
        Storage storage = new Storage(FILE_PATH);
        try {
            storage.saveTasksToFile(taskList);
        } catch (SaveFileException e) {
            userInterface.printUnableToSave(e);
        }
    }

}

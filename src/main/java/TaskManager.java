import Exceptions.InvalidDeadlineFormatException;
import Exceptions.InvalidEventFormatException;
import Exceptions.InvalidTodoFormatException;
import java.util.ArrayList;

public class TaskManager {
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
            taskList.add(new Deadline(taskDetails[PART_0].trim(), taskDetails[PART_1].trim()));
            index += INDEX_OFFSET;
            userInterface.printTaskAdded(taskList.get(index - INDEX_OFFSET), index);
        } else {
            throw new InvalidDeadlineFormatException("Invalid deadline format.");
        }
    }

    private void addEventTask(String taskDescription) throws InvalidEventFormatException {
        String[] taskDetails = taskDescription.substring(EVENT_BEGIN_INDEX).split("/from|/to");
        if (taskDetails.length == EVENT_MAX_PARTS) {
            taskList.add(new Event(taskDetails[PART_0].trim(), taskDetails[PART_1].trim(),
                    taskDetails[PART_2].trim()));
            index += INDEX_OFFSET;
            userInterface.printTaskAdded(taskList.get(index - INDEX_OFFSET), index);
        } else {
            throw new InvalidEventFormatException("Invalid event format. ");
        }
    }

    private void addTodoTask(String taskDescription) throws InvalidTodoFormatException {
        String taskDetails = taskDescription.substring(TODO_BEGIN_INDEX).trim();
        if (!taskDetails.isEmpty()) {
            taskList.add(new Todo(taskDetails));
            index += INDEX_OFFSET;
            userInterface.printTaskAdded(taskList.get(index - INDEX_OFFSET), index);
        } else {
            throw new InvalidTodoFormatException("Invalid todo format. ");
        }
    }

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

    public void unmarkTask(int taskIndex) throws IndexOutOfBoundsException {

        if (taskIndex >= index || taskIndex < START_INDEX) {
            throw new IndexOutOfBoundsException(
                    "Invalid task index for marking: " + (taskIndex + INDEX_OFFSET));
        }

        if (!taskList.get(taskIndex).isDone) {
            userInterface.printTaskAlreadyUnmarked("Task is already marked as undone");
        } else {
            taskList.get(taskIndex).setAsNotDone();
            userInterface.printTaskUnmarked(taskList.get(taskIndex));
        }
    }

    public void printTaskList() {
        userInterface.printTaskList(taskList, index);
    }
}

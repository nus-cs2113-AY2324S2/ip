public class TaskManager {
    public static final int DEADLINE_BEGIN_INDEX = 8;
    public static final int EVENT_BEGIN_INDEX = 5;
    public static final int TODO_BEGIN_INDEX = 4;
    public static final int EVENT_MAX_PARTS = 3;
    public static final int DEADLINE_MAX_PARTS = 2;
    private static final int MAX_TASKS = 100;
    private Task[] taskList = new Task[MAX_TASKS];
    private int index = 0;
    UserInterface userInterface = new UserInterface();

    public void addTask(String taskDescription) {
        String taskType = taskDescription.split(" ")[0];
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
            userInterface.printInvalidTaskType(taskDescription);
            break;
        }
    }

    private void addDeadlineTask(String taskDescription) {
        String[] taskDetails = taskDescription.substring(DEADLINE_BEGIN_INDEX).split("/by");
        if (taskDetails.length == DEADLINE_MAX_PARTS) {
            taskList[index] = new Deadline(taskDetails[0].trim(), taskDetails[1].trim());
            index = Math.min(index + 1, MAX_TASKS);
            userInterface.printTaskAdded(taskList[index - 1], index, index);
        } else {
            userInterface.printInvalidDeadlineFormat();
        }
    }

    private void addEventTask(String taskDescription) {
        String[] taskDetails = taskDescription.substring(EVENT_BEGIN_INDEX).split("/from|/to");
        if (taskDetails.length == EVENT_MAX_PARTS) {
            taskList[index] = new Event(taskDetails[0].trim(), taskDetails[1].trim(), taskDetails[2].trim());
            index = Math.min(index + 1, MAX_TASKS);
            userInterface.printTaskAdded(taskList[index - 1], index, index);
        } else {
            userInterface.printInvalidEventFormat();
        }
    }

    private void addTodoTask(String taskDescription) {
        String taskDetails = taskDescription.substring(TODO_BEGIN_INDEX).trim();
        if (!taskDetails.isEmpty()) {
            taskList[index] = new Todo(taskDetails);
            index = Math.min(index + 1, MAX_TASKS);
            userInterface.printTaskAdded(taskList[index - 1], index, index);
        } else {
            userInterface.printInvalidTodoFormat();
        }

    }

    public void markTask(int taskIndex) {
        if (taskIndex >= index || taskIndex < 0) {
            userInterface.printInvalidTaskIndex(index);
            return;
        }

        taskList[taskIndex].setAsDone();
        userInterface.printTaskMarked(taskList[taskIndex]);
    }

    public void unmarkTask(int taskIndex) {
        if (taskIndex >= index || taskIndex < 0) {
            userInterface.printInvalidTaskIndex(index);
            return;
        }

        taskList[taskIndex].setAsNotDone();
        userInterface.printTaskUnmarked(taskList[taskIndex]);
    }

    public void printTaskList() {
        userInterface.printTaskList(taskList, index);
    }
}

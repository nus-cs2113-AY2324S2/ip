public class TaskManager {
    private static final int MAX_TASKS = 10;
    private Task[] taskLists = new Task[MAX_TASKS];
    private int index = 0;
    UserInterface userInterface = new UserInterface();

    public void addTask(String taskDescription) {
        if (taskDescription.startsWith("deadline")) {
            String[] parts = taskDescription.substring(8).split("/by");
            if (parts.length == 2) {
                taskLists[index] = new Deadline(parts[0].trim(), parts[1].trim());
                index = Math.min(index + 1, MAX_TASKS);
                userInterface.printTaskAdded(taskLists[index - 1], index, index);
            } else {
                userInterface.printInvalidDeadlineFormat();
                return;
            }
        } else if (taskDescription.startsWith("event")) {
            String[] parts = taskDescription.substring(5).split("/from|/to");
            if (parts.length == 3) {
                taskLists[index] = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                index = Math.min(index + 1, MAX_TASKS);
                userInterface.printTaskAdded(taskLists[index - 1], index, index);
            } else {
                userInterface.printInvalidEventFormat();
                return;
            }
        } else if (taskDescription.startsWith("todo")) {
            taskLists[index] = new Todo(taskDescription.substring(4).trim());
            index = Math.min(index + 1, MAX_TASKS);
            userInterface.printTaskAdded(taskLists[index - 1], index, index);
        } else {
            userInterface.printInvalidTaskType(taskDescription);
        }
    }

    public void markTask(int taskIndex) {
        if (taskIndex >= index || taskIndex < 0) {
            userInterface.printInvalidTaskIndex(index);
            return;
        }

        taskLists[taskIndex].setAsDone();
        userInterface.printTaskMarked(taskLists[taskIndex]);
    }

    public void unmarkTask(int taskIndex) {
        if (taskIndex >= index || taskIndex < 0) {
            userInterface.printInvalidTaskIndex(index);
            return;
        }

        taskLists[taskIndex].setAsNotDone();
        userInterface.printTaskUnmarked(taskLists[taskIndex]);
    }

    public void printTaskList() {
        userInterface.printTaskList(taskLists, index);
    }
}

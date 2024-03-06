package n;

import n.exceptions.EmptyTaskDescriptionException;
import n.exceptions.NoTaskTypeException;
import n.task.*;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static void addTask(String message) {
        try {
            Type taskType = Parser.filterTask(message);
            TaskList.addToTaskList(taskType, message);
        } catch (NoTaskTypeException e) {
            Ui.printMessage(Ui.NO_TASK_TYPE_ERROR);
            return;
        } catch (EmptyTaskDescriptionException e) {
            return;
        }
    }
    public static void addToTaskList(Type taskType, String message)
            throws EmptyTaskDescriptionException {
        String taskDescription = "";
        switch(taskType) {
            case Event:
                try {
                    taskDescription = message.substring(5);
                    taskList.add(new Event(taskDescription, taskList.size()));
                } catch (StringIndexOutOfBoundsException e) {
                    Ui.printFormatErrorMessage(taskType);
                    return;
                }
                break;
            case Deadline:
                try {
                    taskDescription = message.substring(8);
                    taskList.add(new Deadline(taskDescription, taskList.size()));
                } catch (StringIndexOutOfBoundsException e) {
                    Ui.printFormatErrorMessage(taskType);
                    return;
                } catch (EmptyTaskDescriptionException e) {
                    Ui.printFormatErrorMessage(taskType);
                    return;
                }
                break;
            case ToDo:
                try {
                    taskDescription = message.substring(4);
                    taskList.add(new ToDo(taskDescription, taskList.size()));
                } catch (EmptyTaskDescriptionException e) {
                    Ui.printFormatErrorMessage(taskType);
                    return;
                }
                break;
        }
        Ui.printTaskAddedMessage();
    }
    public static void deleteTask(String message) {
        try {
            int indexToDelete = Integer.parseInt(message.split(" ")[1]) - 1;
            if (indexToDelete < taskList.size()) {
                Ui.printTaskDeletedMessage(indexToDelete);
                taskList.remove(indexToDelete);
            } else {
                Ui.printMessage(Ui.TASK_INDEX_OUT_OF_BOUNDS_ERROR);
            }

        } catch (NumberFormatException e) {
            Ui.printMessage(Ui.INVALID_TASK_INDEX_ERROR);
        } catch (IndexOutOfBoundsException e) {
            Ui.printMessage(Ui.NO_TASK_INDEX_ERROR);
        }
    }
    public static void changeTaskStatus(int taskIndex, boolean newStatus) {
        //check to ensure that task to be marked/unmarked exists in the list
        if (taskIndex < taskList.size()) {
            if (taskList.get(taskIndex).isDone() == newStatus) {
                Ui.printChangeTaskStatusNotNeededMessage(taskIndex, newStatus);
            } else {
                taskList.get(taskIndex).setDone(newStatus);
                Ui.printTaskStatusChangedMessage(taskIndex, newStatus);
            }
        } else {
            Ui.printMessage(Ui.TASK_INDEX_OUT_OF_BOUNDS_ERROR);
        }
    }
    public static void unmarkTask(String message) {
        try {
            int indexToUnmark = Integer.parseInt(message.split(" ")[1]);
            changeTaskStatus(indexToUnmark - 1, false);
        } catch (NumberFormatException e) {
            Ui.printMessage(Ui.INVALID_TASK_INDEX_ERROR);
        } catch (IndexOutOfBoundsException e) {
            Ui.printMessage(Ui.NO_TASK_INDEX_ERROR);
        }
    }

    public static void markTask(String message) {
        try {
            int indexToMark = Integer.parseInt(message.split(" ")[1]);
            changeTaskStatus(indexToMark - 1, true);
        } catch (NumberFormatException e) {
            Ui.printMessage(Ui.INVALID_TASK_INDEX_ERROR);
        } catch (IndexOutOfBoundsException e) {
            Ui.printMessage(Ui.NO_TASK_INDEX_ERROR);
        }
    }
}

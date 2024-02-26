package mimi.helper;

import mimi.classes.Deadline;
import mimi.classes.Event;
import mimi.classes.Task;
import mimi.classes.ToDo;
import mimi.exceptions.MimiException;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskList;
    public TaskList(){
        taskList = new ArrayList<>();
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static void setTaskList(ArrayList<Task> taskList) {
        TaskList.taskList = taskList;
    }

    public void addToDo(String[] inputs) {
        try {
            ToDo toDo = Parser.processsToDoFromInput(inputs);
            appendIntoTaskList(toDo);
            Ui.printSuccessMessage(toDo, taskList);
        } catch (MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDeadline(String[] inputs) {
        try {
            Deadline deadline = Parser.processDeadlineFromInput(inputs);
            appendIntoTaskList(deadline);
            Ui.printSuccessMessage(deadline, taskList);
        } catch (MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEvent(String[] inputs) {
        try {
            Event event = Parser.processEventFromInput(inputs);
            appendIntoTaskList(event);
            Ui.printSuccessMessage(event, taskList);
        } catch (MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }

    }



    public void unmarkTasks(String[] inputs) {
        try {
            int index = checkValidityOfIndex(inputs);
            taskList.get(index).markAsUndone();
            Ui.printUnmarkTask(taskList, index);
        } catch (MimiException.TaskNotFound |
                 MimiException.InsufficientParameters |
                 MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }

    }

    public void markTask(String[] inputs) {
        try {
            int index = checkValidityOfIndex(inputs);
            taskList.get(index).markAsDone();
            Ui.printMarkTask(taskList, index);
        } catch (MimiException.TaskNotFound | MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask( String[] inputs) {
        try {
            int index = checkValidityOfIndex(inputs);
            Task removedTask = taskList.get(index);
            taskList.remove(index);
            Ui.printDeleteMessage(removedTask, taskList);

        } catch (MimiException.TaskNotFound | MimiException.InsufficientParameters | MimiException.IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    public static void appendIntoTaskList(Task newTask) {
        taskList.add(newTask);
    }

    private static int checkValidityOfIndex(String[] inputs) throws MimiException.TaskNotFound,
            MimiException.InsufficientParameters, MimiException.IncorrectFormat {
        if (inputs.length != 2) {
            // Throws an error if parameters is incomplete
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_INDEX_PARAMETERS_MSG);
        }

        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            if (index < 0 || index >= taskList.size()) {
                // Throws an error if task is not found
                throw new MimiException.TaskNotFound(MimiException.TASK_NOT_FOUND_MSG);
            }
            return index;
        } catch (NumberFormatException e) {
            // Throws an error if the format is incorrect
            throw new MimiException.IncorrectFormat(MimiException.INCORRECT_INDEX_FORMAT_MSG);
        }
    }

}

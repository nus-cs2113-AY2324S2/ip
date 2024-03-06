package Ui;

import Storage.DukeFile;
import TaskList.Task;
import Ui.PrintText;

import java.util.ArrayList;

/**
 * Represents a command to print one or multiple tasks to the screen and file.
 */
public class PrintTask {
    /**
     * Print all the tasks stored in the list.
     *
     * @param tasks Arraylist of tasks stored.
     */
    public static void list(ArrayList<Task> tasks) {
        int index = 1;
        PrintText.print("Here are the tasks in your list:");
        for (Task task : tasks) {
            String indexPrinted = index + ".";
            char type = task.getTypeIcon();
            String typeMark = "[" + type + "]";
            String statusMark = "[" + task.getStatusIcon() + "] ";
            PrintText.print(indexPrinted + typeMark + statusMark + task.getDescription());
            index++;
        }
    }

    /**
     * Print a task to the file at path FILENAME.
     *
     * @param input The task to be printed to the file.
     * @param index The task index to be written in the file.
     * @param ifAppend Indicate if append the text at the end of the file (true)
     *                 or overwrite the file (false)
     */
    public static void printToFile(Task input, int index, boolean ifAppend) {
        String output = "";
        String indexPrinted = index + ".";
        char type = input.getTypeIcon();
        String typeMark = "[" + type + "]";
        String statusMark = "[" + input.getStatusIcon() + "] ";
        output += indexPrinted + typeMark + statusMark + input.getDescription() + "\n";
        DukeFile.updateFile(output, ifAppend);
    }

    /**
     * Print multiple tasks to the file at path FILENAME.
     *
     * @param tasks Arraylist of tasks stored.
     * @param ifAppend Indicate if append the text at the end of the file (true)
     *                 or overwrite the file (false)
     */
    public static void printMultipleToFile(ArrayList<Task> tasks, boolean ifAppend) {
        int index = 1;
        String output = "";
        for (Task task : tasks) {
            String indexPrinted = index + ".";
            char type = task.getTypeIcon();
            String typeMark = "[" + type + "]";
            String statusMark = "[" + task.getStatusIcon() + "] ";
            output += indexPrinted + typeMark + statusMark + task.getDescription() + "\n";
            index++;
        }
        DukeFile.updateFile(output, ifAppend);
    }

    /**
     * Print out the task with special type, toDo, event, or deadline.
     * TaskList.Task description is updated to the proper format.
     *
     * @param newTask The task to be printed.
     * @param taskNum Total number of tasks in the list.
     */
    public static void specialTask(Task newTask, int taskNum) {
        String intro = "Got it. I've added this task: \n";
        char type = newTask.getTypeIcon();
        String taskDisplayed;
        String[] descriptionWords = newTask.getDescription().split(" ");
        switch(descriptionWords[0]) {
        case "todo":
            taskDisplayed = newTask.getDescription().replace("todo ", "");
            break;
        case "deadline":
            taskDisplayed = newTask.getDescription().replace("deadline ", "");
            if (taskDisplayed.contains("/by ")) {
                taskDisplayed = taskDisplayed.replaceFirst("/by ", "(by: ");
                taskDisplayed += ")";
            }
            break;
        case "event":
            taskDisplayed = newTask.getDescription().replace("event ", "");
            if (taskDisplayed.contains("/from ") && taskDisplayed.contains("/to ")) {
                taskDisplayed = taskDisplayed.replaceFirst("/from ", "(from: ");
                taskDisplayed = taskDisplayed.replaceFirst("/to ", "to: ");
                taskDisplayed += ")";
            }
            break;
        default:
            taskDisplayed = newTask.getDescription();
        }
        String typeMark = "[" + type + "]";
        String statusMark = "[" + newTask.getStatusIcon() + "] ";
        String taskCount = String.format("Now you have %d tasks in the list.", taskNum);
        String output = intro +typeMark + statusMark + taskDisplayed + "\n" + taskCount;
        PrintText.printWithLinebreak(output);
        newTask.setDescription(taskDisplayed);
    }

    /**
     * Print out the task without special type, toDo, event, or deadline.
     *
     * @param newTask The task to be printed.
     * @param taskNum Total number of tasks in the list.
     */
    public static void normalTask(Task newTask, int taskNum) {
        String taskCount = String.format("Now you have %d tasks in the list.", taskNum);
        PrintText.printWithLinebreak("added: " + newTask.getDescription() + "\n" + taskCount);
    }
}

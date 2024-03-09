package taskmanager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handle the conversion of user commands into tasks in the task list
 */

public class TaskList {

    /**
     * Detects keywords in the user commands and execute the relevant commands
     *
     * @param receivedMessage User command
     * @param taskCounter Counter to keep track of the number of task in the task list
     * @param requestedMessage Scanner to take in User Command
     * @param taskList List to store tasks for the program to use
     */

    public static ArrayList<Task> processUserInputIntoTaskList (String receivedMessage, int taskCounter, Scanner requestedMessage,
            ArrayList<Task> taskList) {
        if (receivedMessage.trim().equals("list") && taskCounter == 0) { // list is empty
            Ui.printListIsEmptyMessage();
            receivedMessage = requestedMessage.nextLine();
        } else if (receivedMessage.trim().equals("list") && taskCounter > 0) { // list is not empty
            Parser.listIsNotEmpty(taskList, taskCounter);
        } else if (receivedMessage.contains("mark") && !receivedMessage.contains("unmark")) {
            // user keys in mark
            Parser.markTask(receivedMessage, taskList);
        } else if (receivedMessage.contains("unmark")) { // user keys in unmark
            Parser.unmarkTask(receivedMessage, taskList);
        } else if (receivedMessage.contains("todo")) { //user keys in todo
            taskCounter = Parser.addTodoTaskToList(receivedMessage, taskList, taskCounter);
        } else if (receivedMessage.contains("deadline")) { //user keys in deadline
            taskCounter = Parser.addDeadlineTaskToList(receivedMessage, taskList, taskCounter);
        } else if (receivedMessage.contains("event")) { //user keys in event
            taskCounter = Parser.addEventTaskToList(receivedMessage, taskList, taskCounter);
        } else if (receivedMessage.contains("delete")) { //user keys in event
            taskCounter = Parser.deleteTask(receivedMessage, taskList, taskCounter);
        } else if (receivedMessage.startsWith("find")) {
            Parser.findDescription(receivedMessage, taskList);
        } else {
            Ui.printTypoErrorMessage();
        }
        return taskList;
    }
}

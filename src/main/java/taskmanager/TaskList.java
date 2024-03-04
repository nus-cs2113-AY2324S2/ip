package taskmanager;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    public static void processUserInputIntoTaskList (String receivedMessage, int taskCounter, Scanner requestedMessage,
            ArrayList<Task> taskList) {
        if (receivedMessage.trim().equals("list") && taskCounter == 0) { // list is empty
            Ui.listIsEmptyMessage();
            receivedMessage = requestedMessage.nextLine();
            return;
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
        } else {
            Ui.typoErrorMessage();
        }
    }
}

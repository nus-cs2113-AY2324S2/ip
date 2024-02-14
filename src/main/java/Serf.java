import taskmanager.ConditionHandlers;
import taskmanager.Messages;
import taskmanager.Task;

import java.util.Scanner;

public class Serf {
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        int taskCounter = 0; // tracks number of tasks in taskList
        String chatBotName = "Serf";

        Messages.printGreetingMessage(chatBotName);
        Scanner requestedMessage = new Scanner(System.in);
        String receivedMessage = requestedMessage.nextLine();

        while(!receivedMessage.equals("bye")) {

            if (receivedMessage.equals("list") && taskCounter == 0) { // list is empty
                Messages.listIsEmptyMessage();
                receivedMessage = requestedMessage.nextLine();
                continue;
            } else if (receivedMessage.equals("list") && taskCounter > 0) { // list is not empty
                ConditionHandlers.listIsNotEmpty(taskList, taskCounter);
            } else if (receivedMessage.contains("mark") && !receivedMessage.contains("unmark")) {
                // user keys in mark
                ConditionHandlers.markTask(receivedMessage, taskList);
            } else if (receivedMessage.contains("unmark")) { // user keys in unmark
                ConditionHandlers.unmarkTask(receivedMessage, taskList);
            } else if (receivedMessage.contains("todo")) { //user keys in todo
                taskCounter = ConditionHandlers.addTodoTaskToList(receivedMessage, taskList, taskCounter);
            } else if (receivedMessage.contains("deadline")) { //user keys in deadline
                taskCounter = ConditionHandlers.addDeadlineTaskToList(receivedMessage, taskList, taskCounter);
            } else if (receivedMessage.contains("event")) { //user keys in event
                taskCounter = ConditionHandlers.addEventTaskToList(receivedMessage, taskList, taskCounter);
            } else {
                Messages.typoErrorMessage();
            }
            if (requestedMessage.hasNextLine()) { //check if user added another line
                receivedMessage = requestedMessage.nextLine();
            } else {
                break;
            }
        }
        Messages.printFarewellMessage();
    }
}
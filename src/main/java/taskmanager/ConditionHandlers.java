package taskmanager;

import newexceptions.InvalidDeleteIndexException;
import newexceptions.InvalidInputException;

public class ConditionHandlers {
    public static void listIsNotEmpty(Task[] taskList, int taskCounter) {
        Messages.startOfListMessage();
        for (int i = 0; i < taskCounter; i += 1) {
            switch (taskList[i].getTaskType()) {
                case "T":
                    Messages.todoListMessage(i, taskList[i].getTaskType(),
                            taskList[i].getStatusIcon(), taskList[i].getDescription());
                    break;
                case "D":
                    Messages.deadlineListMessage(i, taskList[i].getTaskType(),
                            taskList[i].getStatusIcon(), taskList[i].getDescription(),
                            taskList[i].getEndDate());
                    break;
                case "E":
                    Messages.eventListMessage(i, taskList[i].getTaskType(),
                            taskList[i].getStatusIcon(), taskList[i].getDescription(),
                            taskList[i].getStartDate(), taskList[i].getEndDate());
                    break;
                default:
                    Messages.invalidTaskTypeMessage();
            }
        }
        Messages.printVerticalLines();
    }
    public static void markTask(String receivedMessage, Task[] taskList) {
        String number = "";
        for (int j = 0; j < receivedMessage.length(); j += 1) { // reads number from input and store it in String number
            if (Character.isDigit(receivedMessage.charAt(j))) {
                number += receivedMessage.charAt(j);
            }
        }
        if (number.isEmpty()) {
            Messages.errorMessage();
            System.out.println("     Sire you need to input a digit after mark");
            return;
        } else {
            taskList[Integer.parseInt(number) - 1].markAsDone();
        }
        Messages.markOrUnmarkTaskMessage(taskList[Integer.parseInt(number) - 1].getTaskType(),
                taskList[Integer.parseInt(number) - 1].getStatusIcon(),
                taskList[Integer.parseInt(number) - 1].getDescription(), "complete");
    }
    public static void unmarkTask(String receivedMessage, Task[] taskList){
        String number = "";
        for (int j = 0; j < receivedMessage.length(); j += 1) { // reads number from input and store it in String number
            if (Character.isDigit(receivedMessage.charAt(j))) {
                number += receivedMessage.charAt(j);
            }
        }
        if (number.isEmpty()) {
            Messages.errorMessage();
            System.out.println("     Sire you need to input a digit after unmark");
            return;
        } else {
            taskList[Integer.parseInt(number) - 1].markAsUndone();
        }
        Messages.markOrUnmarkTaskMessage(taskList[Integer.parseInt(number) - 1].getTaskType(),
                taskList[Integer.parseInt(number) - 1].getStatusIcon(),
                taskList[Integer.parseInt(number) - 1].getDescription(), "incomplete");
    }
    public static int addTodoTaskToList(String receivedMessage, Task[] taskList, int taskCounter) {
        try {
            receivedMessage = receivedMessage.trim();
            if (!receivedMessage.startsWith("todo")) { // input does not start with todo
                throw new InvalidInputException();
            }
            String[] splittedMessage = receivedMessage.split("todo ");
            for (int i = 0; i < splittedMessage.length; i += 1) {
                splittedMessage[i] = splittedMessage[i].trim();
            }
            // user did not write a task in the input
            if (splittedMessage.length == 0 || splittedMessage.length == 1){
                throw new InvalidInputException();
            }
            taskList[taskCounter] = new Task(splittedMessage[1]);
            taskList[taskCounter].setTaskType("todo");
            taskCounter += 1;
            Messages.addTodoMessage(taskList[taskCounter - 1].getTaskType(),
                    taskList[taskCounter - 1].getStatusIcon(), taskList[taskCounter - 1].getDescription(),
                    taskCounter);
            return taskCounter;
        } catch (InvalidInputException e) {
        Messages.typoErrorMessage();
        return taskCounter;
        }

    }
    public static int addDeadlineTaskToList(String receivedMessage, Task[] taskList, int taskCounter) {
        try {
            receivedMessage = receivedMessage.trim();
            // input does not start with deadline or contain /by
            if (!receivedMessage.startsWith("deadline") ||
                    !receivedMessage.contains("/by")) {
                throw new InvalidInputException();
            }
            String[] splittedMessage = receivedMessage.split("deadline ");
            for (int i = 0; i < splittedMessage.length; i += 1) {
                splittedMessage[i] = splittedMessage[i].trim();
            }
            // user did not key in a task for the event
            if (splittedMessage[1].startsWith("/by")) {
                throw new InvalidInputException();
            }
            String[] doubleSplittedMessage = splittedMessage[1].split("/by");
            for (int j = 0; j < doubleSplittedMessage.length; j += 1) {
                doubleSplittedMessage[j] = doubleSplittedMessage[j].trim();
            }
            // user did not key in a start date for the event or did not key in the task or both
            if (doubleSplittedMessage.length == 0 ||  doubleSplittedMessage.length == 1){
                throw new InvalidInputException();
            }
            taskList[taskCounter] = new Task(doubleSplittedMessage[0]);
            taskList[taskCounter].setTaskType("deadline");
            taskList[taskCounter].setEndDate(doubleSplittedMessage[1]);
            taskCounter += 1;
            Messages.addDeadlineMessage(taskList[taskCounter - 1].getTaskType(),
                    taskList[taskCounter - 1].getStatusIcon(), taskList[taskCounter - 1].getDescription(),
                    taskList[taskCounter - 1].getEndDate(), taskCounter);
            return taskCounter;
        } catch (InvalidInputException e) {
            Messages.typoErrorMessage();
            return taskCounter;
        }
    }
    public static int addEventTaskToList(String receivedMessage, Task[] taskList, int taskCounter){
        try {
            receivedMessage = receivedMessage.trim();
            if (!receivedMessage.startsWith("event") || !receivedMessage.contains("/from")
                    || !receivedMessage.contains("/to")) { // input does not start with event
                throw new InvalidInputException();
            }
            String[] splittedMessage = receivedMessage.split("event ");
            for (int i = 0; i < splittedMessage.length; i += 1) {
                splittedMessage[i] = splittedMessage[i].trim();
            }
            // user did not key in a task for the event or did not key in a start date
            if (splittedMessage[1].startsWith("/from") || splittedMessage[1].startsWith("/to")) {
                throw new InvalidInputException();
            }
            String[] doubleSplittedMessage = splittedMessage[1].split("/from");
            for (int j = 0; j < doubleSplittedMessage.length; j += 1) {
                doubleSplittedMessage[j] = doubleSplittedMessage[j].trim();
            }
            if (doubleSplittedMessage[1].startsWith("/to")) { // user did not key in a start date for the event
                throw new InvalidInputException();
            }
            String[] tripleSplittedMessage = doubleSplittedMessage[1].split("/to");
            if (tripleSplittedMessage.length == 0 || tripleSplittedMessage.length == 1) {
                // user did not key in a end date for the event
                throw new InvalidInputException();
            }
            for (int k = 0; k < tripleSplittedMessage.length; k += 1) {
                tripleSplittedMessage[k] = tripleSplittedMessage[k].trim();
            }
            taskList[taskCounter] = new Task(doubleSplittedMessage[0]);
            taskList[taskCounter].setTaskType("event");
            taskList[taskCounter].setStartDate(tripleSplittedMessage[0]);
            taskList[taskCounter].setEndDate(tripleSplittedMessage[1]);
            taskCounter += 1;
            Messages.addEventMessage(taskList[taskCounter - 1].getTaskType(),
                    taskList[taskCounter - 1].getStatusIcon(), taskList[taskCounter - 1].getDescription(),
                    taskList[taskCounter - 1].getStartDate(), taskList[taskCounter - 1].getEndDate(),
                    taskCounter);
            return taskCounter;
        } catch (InvalidInputException e){
            Messages.typoErrorMessage();
            return taskCounter;
        }
    }

    public static int deleteTask(String receivedMessage, Task[] taskList, int taskCounter) {
        try {
            String number = "";
            for (int j = 0; j < receivedMessage.length(); j += 1) { // reads number from input and store it in String number
                if (Character.isDigit(receivedMessage.charAt(j))) {
                    number += receivedMessage.charAt(j);
                }
            }
            if (number.isEmpty()) {
                Messages.errorMessage();
                System.out.println("     Sire you need to input a digit after delete");
                return taskCounter;
            }
            int taskNumber = Integer.parseInt(number);
            if (taskNumber > taskCounter) {
                throw new InvalidDeleteIndexException();
            }
            switch(taskList[taskNumber - 1].getTaskType()) {
                case "T":
                    Messages.deleteTodoMessage(taskList[taskNumber - 1].getTaskType(),
                            taskList[taskNumber - 1].getStatusIcon(), taskList[taskNumber - 1].getDescription(),
                            taskCounter - 1);
                    break;
                case "D":
                    Messages.deleteDeadlineMessage(taskList[taskNumber - 1].getTaskType(),
                            taskList[taskNumber - 1].getStatusIcon(), taskList[taskNumber - 1].getDescription(),
                            taskList[taskNumber - 1].getEndDate(),taskCounter - 1);
                    break;
                case "E":
                    Messages.deleteEventMessage(taskList[taskNumber - 1].getTaskType(),
                            taskList[taskNumber - 1].getStatusIcon(), taskList[taskNumber - 1].getDescription(),
                            taskList[taskNumber - 1].getStartDate(), taskList[taskNumber - 1].getEndDate(),
                            taskCounter - 1);
                    break;
                default:
                    System.out.println("     Error, task type not found");
                    return taskCounter;
            }

            for (int iterator = taskNumber - 1; iterator < taskCounter - 1; iterator += 1) {
                taskList[iterator] = taskList[iterator + 1];
            }
            taskList[taskCounter - 1] = null;
            taskCounter = taskCounter - 1;
            return taskCounter;
        }
        catch (InvalidDeleteIndexException e) {
            Messages.typoErrorMessage();
            Messages.invalidDeleteIndexMessage();
            return taskCounter;
        }
    }
}

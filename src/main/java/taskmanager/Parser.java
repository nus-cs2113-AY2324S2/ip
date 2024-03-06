package taskmanager;

import newexceptions.InvalidDeleteIndexException;
import newexceptions.InvalidInputException;

import java.util.ArrayList;

/**
 * Collection of methods converts user commands into tasks
 */

public class Parser {

    /**
     * List out tasks in the task list when the task list is not empty
     *
     * @param taskList List to store tasks for the program to use
     * @param taskCounter Counter to keep track of the number of task in the task list
     */

    public static void listIsNotEmpty(ArrayList<Task> taskList, int taskCounter) {
        Ui.printStartOfListMessage();
        for (int i = 0; i < taskCounter; i += 1) {
            switch (taskList.get(i).getTaskType()) {
                case "T":
                    Ui.printTodoListMessage(i, taskList.get(i).getTaskType(),
                            taskList.get(i).getStatusIcon(), taskList.get(i).getDescription());
                    break;
                case "D":
                    Ui.printDeadlineListMessage(i, taskList.get(i).getTaskType(),
                            taskList.get(i).getStatusIcon(), taskList.get(i).getDescription(),
                            taskList.get(i).getEndDate());
                    break;
                case "E":
                    Ui.printEventListMessage(i, taskList.get(i).getTaskType(),
                            taskList.get(i).getStatusIcon(), taskList.get(i).getDescription(),
                            taskList.get(i).getStartDate(), taskList.get(i).getEndDate());
                    break;
                default:
                    Ui.printInvalidTaskTypeMessage();
            }
        }
        Ui.printVerticalLines();
    }

    /**
     * Mark task status with a "X" to indicate it is complete
     *
     * @param receivedMessage User command
     * @param taskList List to store tasks for the program to use
     */

    public static void markTask(String receivedMessage, ArrayList<Task> taskList) {
        try {
            String number = "";
            for (int j = 0; j < receivedMessage.length(); j += 1) {
                // reads number from input and store it in String number
                if (Character.isDigit(receivedMessage.charAt(j))) {
                    number += receivedMessage.charAt(j);
                }
            }
            if (Integer.parseInt(number) > taskList.size() || number.isEmpty()) {
                throw new InvalidInputException();
            }
            else {
                taskList.get(Integer.parseInt(number) - 1).markAsDone();
            }
            Ui.printMarkOrUnmarkTaskMessage(taskList, Integer.parseInt(number), "complete");
        } catch (InvalidInputException e) {
            Ui.printMarkOrUnmarkTaskErrorMessage();
        }
    }

    /**
     * Remove "X" from task status to indicate it is incomplete
     *
     * @param receivedMessage User command
     * @param taskList List to store tasks for the program to use
     */

    public static void unmarkTask(String receivedMessage, ArrayList<Task> taskList){
        try {
            String number = "";
            for (int j = 0; j < receivedMessage.length(); j += 1) {
                // reads number from input and store it in String number
                if (Character.isDigit(receivedMessage.charAt(j))) {
                    number += receivedMessage.charAt(j);
                }
            }
            if (Integer.parseInt(number) > taskList.size() || number.isEmpty()) {
                throw new InvalidInputException();
            }
            else {
                taskList.get(Integer.parseInt(number) - 1).markAsUndone();
            }
            Ui.printMarkOrUnmarkTaskMessage(taskList, Integer.parseInt(number), "incomplete");
        } catch (InvalidInputException e) {
            Ui.printMarkOrUnmarkTaskErrorMessage();
        }
    }

    /**
     * Process an user command into a todo task and store the todo task in the task list
     * Returns the updated taskCounter
     *
     * @param receivedMessage User command
     * @param taskList List to store tasks for the program to use
     * @param taskCounter Counter to keep track of the number of task in the task list
     * @return Updated counter which keep track of the number of task in the task list
     */

    public static int addTodoTaskToList(String receivedMessage, ArrayList<Task> taskList, int taskCounter) {
        try {
            receivedMessage = receivedMessage.trim();
            String[] splittedMessage = receivedMessage.split("todo ");
            for (int i = 0; i < splittedMessage.length; i += 1) {
                splittedMessage[i] = splittedMessage[i].trim();
            }
            // user did not write a task in the input
            if (splittedMessage.length == 0 || splittedMessage.length == 1){
                throw new InvalidInputException();
            }
            taskList.add(taskCounter, new Task(splittedMessage[1]));
            taskList.get(taskCounter).setTaskType("todo");
            if (receivedMessage.contains("mark")) {
                taskList.get(taskCounter).markAsDone();
            }
            taskCounter += 1;
            Ui.printAddTodoMessage(taskList.get(taskCounter - 1).getTaskType(),
                    taskList.get(taskCounter - 1).getStatusIcon(), taskList.get(taskCounter - 1).getDescription(),
                    taskCounter);
            return taskCounter;
        } catch (InvalidInputException e) {
            Ui.printTypoErrorMessage();
            return taskCounter;
        }
    }

    /**
     * Process an user command into a deadline task and store the deadline task in the task list
     * Returns the updated taskCounter
     *
     * @param receivedMessage User command
     * @param taskList List to store tasks for the program to use
     * @param taskCounter Counter to keep track of the number of task in the task list
     * @return Updated counter which keep track of the number of task in the task list
     */

    public static int addDeadlineTaskToList(String receivedMessage, ArrayList<Task> taskList, int taskCounter) {
        try {
            receivedMessage = receivedMessage.trim();
            if (!receivedMessage.contains("/by")) {
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
            taskList.add(taskCounter, new Task(doubleSplittedMessage[0]));
            taskList.get(taskCounter).setTaskType("deadline");
            taskList.get(taskCounter).setEndDate(doubleSplittedMessage[1]);
            if (receivedMessage.contains("mark")) {
                taskList.get(taskCounter).markAsDone();
            }
            taskCounter += 1;
            Ui.printAddDeadlineMessage(taskList.get(taskCounter - 1).getTaskType(),
                    taskList.get(taskCounter - 1).getStatusIcon(), taskList.get(taskCounter - 1).getDescription(),
                    taskList.get(taskCounter - 1).getEndDate(), taskCounter);
            return taskCounter;
        } catch (InvalidInputException e) {
            Ui.printTypoErrorMessage();
            return taskCounter;
        }
    }

    /**
     * Process an user command into an event task and store the event task in the task list
     * Returns the updated taskCounter
     *
     * @param receivedMessage User command
     * @param taskList List to store tasks for the program to use
     * @param taskCounter Counter to keep track of the number of task in the task list
     * @return Updated counter which keep track of the number of task in the task list
     */

    public static int addEventTaskToList(String receivedMessage, ArrayList<Task> taskList, int taskCounter){
        try {
            receivedMessage = receivedMessage.trim();
            if (!receivedMessage.contains("/from")
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
            taskList.add(taskCounter, new Task(doubleSplittedMessage[0]));
            taskList.get(taskCounter).setTaskType("event");
            taskList.get(taskCounter).setStartDate(tripleSplittedMessage[0]);
            taskList.get(taskCounter).setEndDate(tripleSplittedMessage[1]);
            if (receivedMessage.contains("mark")) {
                taskList.get(taskCounter).markAsDone();
            }
            taskCounter += 1;
            Ui.printAddEventMessage(taskList.get(taskCounter - 1).getTaskType(),
                    taskList.get(taskCounter - 1).getStatusIcon(), taskList.get(taskCounter - 1).getDescription(),
                    taskList.get(taskCounter - 1).getStartDate(), taskList.get(taskCounter - 1).getEndDate(),
                    taskCounter);
            return taskCounter;
        } catch (InvalidInputException e){
            Ui.printTypoErrorMessage();
            return taskCounter;
        }
    }

    /**
     * Remove task located at index specified by the user in the user commands from the task list
     * Returns the updated taskCounter
     *
     * @param receivedMessage User command
     * @param taskList List to store tasks for the program to use
     * @param taskCounter Counter to keep track of the number of task in the task list
     * @return Updated counter which keep track of the number of task in the task list
     */

    public static int deleteTask(String receivedMessage, ArrayList<Task> taskList, int taskCounter) {
        try {
            String number = "";
            for (int j = 0; j < receivedMessage.length(); j += 1) { // reads number from input and store it in String number
                if (Character.isDigit(receivedMessage.charAt(j))) {
                    number += receivedMessage.charAt(j);
                }
            }
            if (number.isEmpty()) {
                Ui.printInvalidDeleteIndexMessage();
                return taskCounter;
            }
            int taskNumber = Integer.parseInt(number);
            if (taskNumber > taskCounter) {
                throw new InvalidDeleteIndexException();
            }
            switch(taskList.get(taskNumber - 1).getTaskType()) {
                case "T":
                    Ui.printDeleteTodoMessage(taskList.get(taskNumber - 1).getTaskType(),
                            taskList.get(taskNumber - 1).getStatusIcon(), taskList.get(taskNumber - 1).getDescription(),
                            taskCounter - 1);
                    break;
                case "D":
                    Ui.printDeleteDeadlineMessage(taskList.get(taskNumber - 1).getTaskType(),
                            taskList.get(taskNumber - 1).getStatusIcon(), taskList.get(taskNumber - 1).getDescription(),
                            taskList.get(taskNumber - 1).getEndDate(),taskCounter - 1);
                    break;
                case "E":
                    Ui.printDeleteEventMessage(taskList.get(taskNumber - 1).getTaskType(),
                            taskList.get(taskNumber - 1).getStatusIcon(), taskList.get(taskNumber - 1).getDescription(),
                            taskList.get(taskNumber - 1).getStartDate(), taskList.get(taskNumber - 1).getEndDate(),
                            taskCounter - 1);
                    break;
                default:
                    System.out.println("     Error, task type not found");
                    return taskCounter;
            }

            for (int iterator = taskNumber - 1; iterator < taskCounter - 1; iterator += 1) {
                taskList.set(iterator, taskList.get(iterator + 1));
            }
            taskList.remove(taskCounter - 1);
            taskCounter = taskCounter - 1;
            return taskCounter;
        } catch (InvalidDeleteIndexException e) {
            Ui.printTypoErrorMessage();
            Ui.printInvalidDeleteIndexMessage();
            return taskCounter;
        }
    }

    /**
     * Look through the task list to find tasks related to the keyword indicated in the user's command
     *
     * @param receivedMessage User command
     * @param taskList List to store tasks for the program to use
     */

    public static void findDescription (String receivedMessage, ArrayList<Task> taskList) {
        try {
            receivedMessage = receivedMessage.trim();
            String[] splittedMessage = receivedMessage.split("find ");
            String stringToBefound = splittedMessage[1];
            if (stringToBefound.isEmpty() || stringToBefound.equals("find")) {
                throw new InvalidInputException();
            }
            ArrayList<Task> temporaryArray = new ArrayList<Task>();
            int temporaryArrayCounter = 0;
            for (int iterator = 0; iterator < taskList.size(); iterator += 1) {
                if (taskList.get(iterator).getDescription().contains(stringToBefound)) {
                    temporaryArray.add(temporaryArrayCounter, taskList.get(iterator));
                    temporaryArrayCounter += 1;
                }
            }
            Ui.printFindMessage(temporaryArray, temporaryArrayCounter);

        } catch (InvalidInputException e){
            Ui.printTypoErrorMessage();
        }
    }

}

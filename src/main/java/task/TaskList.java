package task;

import java.util.ArrayList;

import baronException.BaronException;
import baronException.DeadlineMissingByException;
import baronException.EmptyDeadlineDescriptionException;
import baronException.EmptyEventDescriptionException;
import baronException.EmptyToDoDescriptionException;
import baronException.EventMissingFromException;
import baronException.EventMissingToException;

import parser.Parser;

/**
 * The TaskList class allows users manage tasks by adding, deleting and marking
 * them as done or undone.
 */

public class TaskList {
    public static final String TODO_TASK = "T";
    public static final String DEADLINE_TASK = "D";
    public static final String EVENT_TASK = "E";

    private static ArrayList<Task> tasks;
    private static int taskCount;

    public TaskList() {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    public static int getLength() {
        return tasks.size();
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void incrementTaskCount() {
        taskCount += 1;
    }

    /**
     * Prints all current tasks in the task list.
     */

    public void displayTasksList() {
        if (tasks.isEmpty()) {
            System.out.println("\tYour list does not contain any tasks.");
        } else {
            int taskNumber = 1;
            System.out.println("\tHere are the tasks in your list:");
            for (Task task : tasks) {
                System.out.println("\t" + taskNumber + "." + task.getTaskDetails());
                taskNumber += 1;
            }
        }
        System.out.println();
    }

    /**
     * Marks a task as done.
     * @param taskIndex the number of the task to be marked as done
     * @throws IndexOutOfBoundsException when the index is out of range
     */

    public void markTask(int taskIndex) throws IndexOutOfBoundsException {
        try {
            tasks.get(taskIndex).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please enter a valid task number\n");
        }
    }

    /**
     * Marks a task as undone.
     * @param taskIndex the number of the task to be marked as undone
     * @throws IndexOutOfBoundsException when the index is out of range
     */

    public void unmarkTask(int taskIndex) throws IndexOutOfBoundsException {
        try {
            tasks.get(taskIndex).unmarkAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please enter a valid task number\n");
        }
    }

    public void addTask(String taskType, String[] userInput) throws BaronException {
        switch (taskType) {
        case Parser.TODO_COMMAND:
            handleToDo(userInput);
            break;
        case Parser.DEADLINE_COMMAND:
            handleDeadline(userInput);
            break;
        case Parser.EVENT_COMMAND:
            handleEvent(userInput);
            break;
        default:
            System.out.println("Sorry but that is not a valid command. Please enter a valid command.");
            break;
        }
    }

    /**
     * Deletes a task from the task list.
     * @param taskIndex the index of the task to be removed
     */

    public void deleteTask(int taskIndex) {
        try {
            Task task = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            taskCount -= 1;
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t\t" + task.getTaskDetails());
            System.out.println("\tNow you have " + taskCount
                    + (taskCount == 1 ? " task" : " tasks") + " in the list.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please enter a valid task number.\n");
        }
    }

    /**
     * Increments the task count when a valid task is added and prints the
     * details of the task.
     */

    public void validTaskAdded() {
        taskCount += 1;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + tasks.get(taskCount - 1).getTaskDetails());
        System.out.println("\tNow you have " + taskCount
                + (taskCount == 1 ? " task" : " tasks") + " in the list.\n");
    }

    /**
     * Adds a new ToDo task to the task list.
     * @param userInput the string containing the ToDo details
     * @throws ArrayIndexOutOfBoundsException when the ToDo description is empty
     */

    public void handleToDo(String[] userInput) throws ArrayIndexOutOfBoundsException {
        try {
            if (userInput[1].isEmpty()) {
                throw new EmptyToDoDescriptionException("Todo task description cannot be empty.\n");
            }
            tasks.add(new ToDo(userInput[1]));
            validTaskAdded();
        } catch (EmptyToDoDescriptionException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Adds a new Deadline task to the task list.
     * @param userInput the string containing the Deadline details
     * @throws ArrayIndexOutOfBoundsException when the Deadline description is empty
     */

    public void handleDeadline(String[] userInput) throws ArrayIndexOutOfBoundsException {
        try {
            if (userInput[1].isEmpty()) {
                throw new EmptyDeadlineDescriptionException("Deadline task description cannot be empty.\n");
            }
            if (!userInput[1].contains("/by"))          {
                throw new DeadlineMissingByException("Deadline task should contain /by.");
            }
            String taskDetails = userInput[1];
            int dividerPosition = taskDetails.indexOf("/by");
            String description = taskDetails.substring(0, dividerPosition);
            String deadline = taskDetails.substring(dividerPosition + 4);
            tasks.add(new Deadline(description, deadline));
            validTaskAdded();
        } catch (EmptyDeadlineDescriptionException | ArrayIndexOutOfBoundsException | DeadlineMissingByException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Adds a new Event task to the task list.
     * @param userInput the string containing the Event details
     * @throws ArrayIndexOutOfBoundsException when the Event description is empty
     */

    public void handleEvent(String[] userInput) throws ArrayIndexOutOfBoundsException {
        try {
            if (userInput[1].isEmpty()) {
                throw new EmptyEventDescriptionException("Event task description cannot be empty.\n");
            }
            if (!userInput[1].contains("/from"))          {
                throw new EventMissingFromException("Event task should contain /from.");
            }
            if (!userInput[1].contains("/to"))          {
                throw new EventMissingToException("Event task should contain /to.");
            }
            String taskDetails = userInput[1];
            int firstDividerPosition = taskDetails.indexOf("/from");
            int secondDividerPosition = taskDetails.indexOf("to");
            String description = taskDetails.substring(0, firstDividerPosition);
            String eventStart = taskDetails.substring(firstDividerPosition + 6, secondDividerPosition - 1);
            String eventEnd = taskDetails.substring(secondDividerPosition + 3);
            tasks.add(new Event(description, eventStart, eventEnd));
            validTaskAdded();
        } catch (EmptyEventDescriptionException | ArrayIndexOutOfBoundsException | EventMissingToException
                 | EventMissingFromException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Loads existing data on file and converts them into their correct type of
     * tasks and their correct descriptions.
     * @param inputArray the string containing the type of task and its details
     */

    public static void readTaskFromFile(String inputArray) {
        String[] taskDetails = inputArray.split("\\|");
        String status = taskDetails[1].trim();
        String description = taskDetails[2].trim();

        if (inputArray.startsWith(TODO_TASK)) {
            Task tempToDo = new ToDo(description);
            tempToDo.setDone(status);
            tasks.add(tempToDo);
        } else if (inputArray.startsWith(DEADLINE_TASK)) {
            String deadline = taskDetails[3].trim();
            Task tempDeadline = new Deadline(description, deadline);
            tempDeadline.setDone(status);
            tasks.add(tempDeadline);
        } else if (inputArray.startsWith(EVENT_TASK)) {
            String eventInfo = taskDetails[3].trim();
            String[] eventParts = eventInfo.split("\\s+", 2);
            String eventStart = eventParts[0];
            String eventEnd = eventParts[1];
            Task tempEvent = new Event(description, eventStart, eventEnd);
            tempEvent.setDone(status);
            tasks.add(tempEvent);
        } else {
            System.out.println("Invalid data in file.");
        }
        incrementTaskCount();
    }
}

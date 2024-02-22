package task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import parser.Parser;

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

    public void markTask(int taskIndex) throws IndexOutOfBoundsException {
        try {
            tasks.get(taskIndex).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please enter a valid task number");
        }
    }

    public void unmarkTask(int taskIndex) throws IndexOutOfBoundsException {
        try {
            this.tasks.get(taskIndex).unmarkAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please enter a valid task number\n");
        }
    }

    public void addTask(String taskType, String[] userInput) {
        if (taskType.equals(Parser.TODO_COMMAND)) {
            handleToDo(userInput);
        } else if (taskType.equals(Parser.DEADLINE_COMMAND)) {
            handleDeadline(userInput);
        } else {
            handleEvent(userInput);
        }
    }

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

    public void validTaskAdded() {
        taskCount += 1;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + tasks.get(taskCount - 1).getTaskDetails());
        System.out.println("\tNow you have " + taskCount
                + (taskCount == 1 ? " task" : " tasks") + " in the list.\n");
    }

    public void handleToDo(String[] userInput) throws ArrayIndexOutOfBoundsException {
        try {
            tasks.add(new ToDo(userInput[1]));
            validTaskAdded();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ToDo task description cannot be empty. Please try again.");
        }
    }

    public void handleDeadline(String[] userInput) {
        try {
            String taskDetails = userInput[1];
            int dividerPosition = taskDetails.indexOf('/');
            String description = taskDetails.substring(0, dividerPosition);
            String deadline = taskDetails.substring(dividerPosition + 4);
            tasks.add(new Deadline(description, deadline));
            validTaskAdded();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Deadline task description cannot be empty. Please try again.");
        }
    }

    public void handleEvent(String[] userInput) {
        try {
            String taskDetails = userInput[1];
            int firstDividerPosition = taskDetails.indexOf('/');
            int secondDividerPosition = taskDetails.indexOf("to");
            String description = taskDetails.substring(0, firstDividerPosition);
            String eventStart = taskDetails.substring(firstDividerPosition + 6, secondDividerPosition - 1);
            String eventEnd = taskDetails.substring(secondDividerPosition + 3);
            tasks.add(new Event(description, eventStart, eventEnd));
            validTaskAdded();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Event task description cannot be empty. Please try again.");
        }
    }

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
        } else {
            String eventInfo = taskDetails[3].trim();
            String[] eventParts = eventInfo.split("\\s+", 2);
            String eventStart = eventParts[0];
            String eventEnd = eventParts[1];
            Task tempEvent = new Event(description, eventStart, eventEnd);
            tempEvent.setDone(status);
            tasks.add(tempEvent);
        }
        incrementTaskCount();
    }
}

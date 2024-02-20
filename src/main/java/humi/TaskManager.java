package humi;

import java.io.File;
import java.util.ArrayList;

public class TaskManager {
    //public static final int MAX_TASK = 100;
    ArrayList<Task> taskList;
    public static int taskCount;

    TaskManager() {
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    // add task from text file
    public void addTask(String inputString) {
        String[] taskComponents = inputString.split("/");
        String taskType = taskComponents[0];
        boolean isDone = (taskComponents[1].equals("1"));
        String description = taskComponents[2];

        if (taskType.equals("T")) {
            taskList.add(new TodoTask(description, isDone));
            taskCount++;
        } else if (taskType.equals("D")) {
            String deadline = taskComponents[3];
            taskList.add(new DeadlineTask(description, deadline, isDone));
            taskCount++;
        } else if (taskType.equals("E")) {
            String startDate = taskComponents[3];
            String endDate = taskComponents[4];
            taskList.add(new EventTask(description, startDate, endDate, isDone));
            taskCount++;
        } else {
            System.out.println("Invalid text, cannot add new task.");
        }
    }

    public void handleCommand(String command) {
        try {
            if (command.equals("list")) {
                handleList();
            } else if (command.startsWith("todo")) {
                handleTodo(command);
                String text = FileProcessor.convertCommandToText(command);
                FileProcessor.appendTextContent(text);
            } else if (command.startsWith("deadline")) {
                handleDeadline(command);
                String text = FileProcessor.convertCommandToText(command);
                FileProcessor.appendTextContent(text);
            } else if (command.startsWith("event")) {
                handleEvent(command);
                String text = FileProcessor.convertCommandToText(command);
                FileProcessor.appendTextContent(text);
            } else if (command.startsWith("mark")) {
                int taskIndex = Integer.parseInt(command.substring(5));
                taskList.get(taskIndex - 1).mark();
            } else if (command.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(command.substring(7));
                taskList.get(taskIndex - 1).unmark();
                FileProcessor.markText(false, taskIndex);
            } else if (command.startsWith("delete")) {
                handleDelete(command);
                int taskIndex = Integer.parseInt(command.substring(7));
                FileProcessor.deleteText(taskIndex);
            } else {
                System.out.println("Invalid input. What is " + command + "?");
            }
        } catch (HumiException e) {
            System.out.println(e.message);
        }
    }

    private void handleDelete(String command) {
        System.out.println(Humi.LINE);
        System.out.print("    Noted. I've removed this task:\n      ");
        int taskIndex = Integer.parseInt(command.substring(7));
        taskList.get(taskIndex - 1).print();
        taskList.remove(taskIndex - 1);
        taskCount -= 1;
        System.out.println("    Now you have " + taskCount + " tasks in the list");
        System.out.println(Humi.LINE);
    }

    private void handleEvent(String command) throws HumiException {
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 5) {
            String[] splitArray = trimmedCommand.split("/");
            if (splitArray.length >= 3) {
                String description = splitArray[0].substring(6);
                String startDate = splitArray[1].substring(5);
                String endDate = splitArray[2].substring(3);
                taskList.add(new EventTask(description, startDate, endDate));
                taskCount += 1;
            } else {
                throw new HumiException("Please specify both the start date and end date");
            }
        } else {
            throw new HumiException("Description of an event cannot be empty.");
        }
    }

    private void handleDeadline(String command) throws HumiException{
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 8) {
            String[] splitArray = trimmedCommand.split("/");
            if (splitArray.length >= 2) {
                String description = splitArray[0].substring(9);
                String deadline = splitArray[1].substring(3);
                taskList.add(new DeadlineTask(description, deadline));
                taskCount += 1;
            } else {
                throw new HumiException("Please specify the deadline date.");
            }
        } else {
            throw new HumiException("Description of a deadline cannot be empty.");
        }
    }

    private void handleTodo(String command) throws HumiException {
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 4) {
            String description = trimmedCommand.substring(5);
            taskList.add(new TodoTask(description));
            taskCount += 1;
        } else {
            throw new HumiException("Description of a todo cannot be empty.");
        }
    }

    private void handleList() {
        System.out.println(Humi.LINE);
        System.out.println("    Here are the tasks in your list");
        for (int i = 0; i < taskCount; i++) {
            System.out.print("     " + (i+1) + ".");
            taskList.get(i).print();
        }
        System.out.println(Humi.LINE);
    }
}
package humi;
import java.io.IOException;

public class TaskManager {
    public static final int MAX_TASK = 100;
    public static Task[] taskList;
    public static int taskCount;

    TaskManager() {
        taskList = new Task[MAX_TASK];
        taskCount = 0;
    }

    public void markText(boolean isDone, int index) {
        String[] textArray = Humi.textContent.split("\n");
        String mark = isDone ? "1" : "0";
        String newString = textArray[index-1].substring(0, 2) + mark + textArray[index-1].substring(3);
        textArray[index-1] = newString;
        Humi.textContent = "";
        for (int i = 0; i < textArray.length; i++) {
            Humi.appendTextContent(textArray[i]);
        }
    }

    public void addTask(String inputString) {
        String[] taskComponents = inputString.split("/");
        String taskType = taskComponents[0];
        boolean isDone = (taskComponents[1].equals("1"));
        String description = taskComponents[2];

        if (taskType.equals("T")) {
            taskList[taskCount] = new TodoTask(description, isDone);
            taskCount++;
        } else if (taskType.equals("D")) {
            String deadline = taskComponents[3];
            taskList[taskCount] = new DeadlineTask(description, deadline, isDone);
            taskCount++;
        } else if (taskType.equals("E")) {
            String startDate = taskComponents[3];
            String endDate = taskComponents[4];
            taskList[taskCount] = new EventTask(description, startDate, endDate, isDone);
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
                Humi.appendTextContent(text);
            } else if (command.startsWith("deadline")) {
                handleDeadline(command);
                String text = FileProcessor.convertCommandToText(command);
                Humi.appendTextContent(text);
            } else if (command.startsWith("event")) {
                handleEvent(command);
                String text = FileProcessor.convertCommandToText(command);
                Humi.appendTextContent(text);
            } else if (command.startsWith("mark")) {
                int taskIndex = Integer.parseInt(command.substring(5));
                taskList[taskIndex - 1].mark();
                markText(true, taskIndex);
            } else if (command.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(command.substring(7));
                taskList[taskIndex - 1].unmark();
                markText(false, taskIndex);
            } else {
                System.out.println("Invalid input. What is " + command + "?");
            }
        } catch (HumiException e) {
            System.out.println(e.message);
        }
    }

    private void handleEvent(String command) throws HumiException {
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 5) {
            String[] splitArray = trimmedCommand.split("/");
            if (splitArray.length >= 3) {
                String description = splitArray[0].substring(6);
                String startDate = splitArray[1].substring(5);
                String endDate = splitArray[2].substring(3);
                taskList[taskCount] = new EventTask(description, startDate, endDate);
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
                taskList[taskCount] = new DeadlineTask(description, deadline);
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
            taskList[taskCount] = new TodoTask(description);
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
            taskList[i].print();
        }
        System.out.println(Humi.LINE);
    }
}
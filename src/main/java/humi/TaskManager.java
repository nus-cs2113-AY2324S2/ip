package humi;

import java.io.File;
import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> taskList;
    public static int taskCount;
    Storage storage;

    TaskManager(Storage storage) {
        this.taskList = new ArrayList<>();
        taskCount = 0;
        this.storage = storage;
    }

    public void handleCommand(String command) {
        try {
            if (command.equals("list")) {
                handleList();
            } else if (command.startsWith("todo")) {
                handleTodo(command);
            } else if (command.startsWith("deadline")) {
                handleDeadline(command);
            } else if (command.startsWith("event")) {
                handleEvent(command);
            } else if (command.startsWith("mark")) {
                handleMark(command);
            } else if (command.startsWith("unmark")) {
                handleUnmark(command);
            } else if (command.startsWith("delete")) {
                handleDelete(command);
            } else if (command.startsWith("find")) {
                handleFind(command);
            } else {
                System.out.println("Invalid input. What is " + command + "?");
            }
        } catch (HumiException e) {
            System.out.println(e.message);
        }
    }

    private void handleList() {
        System.out.println(Ui.LINE);
        System.out.println("    Here are the tasks in your list");
        for (int i = 0; i < taskCount; i++) {
            System.out.print("     " + (i+1) + ".");
            taskList.get(i).print();
        }
        System.out.println(Ui.LINE);
    }

    private void handleTodo(String command) {
        try {
            Parser.parseCommand(command);
            String description = Parser.todoDescription;
            taskList.add(new TodoTask(description));
            taskCount += 1;
            String text = Parser.convertCommandToText(command);
            storage.appendTextContent(text);
        } catch (HumiException e) {
            System.out.println(e.message);
        }
    }

    private void handleDeadline(String command) {
        try {
            Parser.parseCommand(command);
            String description = Parser.deadlineDescription;
            String deadline = Parser.deadlineDate;
            taskList.add(new DeadlineTask(description, deadline));
            taskCount += 1;
            String text = Parser.convertCommandToText(command);
            storage.appendTextContent(text);
        } catch (HumiException e) {
            System.out.println(e.message);
        }
    }

    private void handleEvent(String command) throws HumiException {
        try {
            Parser.parseCommand(command);
            String description = Parser.eventDescription;
            String startDate = Parser.eventStartDate;
            String endDate = Parser.eventEndDate;
            taskList.add(new EventTask(description, startDate, endDate));
            taskCount += 1;
            String text = Parser.convertCommandToText(command);
            storage.appendTextContent(text);
        } catch (HumiException e) {
            System.out.println(e.message);
        }
    }

    private void handleFind(String command) {
        String keyword = command.substring(5).trim().toLowerCase();
        int index = 1;
        System.out.println(Ui.LINE);
        System.out.println("    Here are the matching tasks in your list:");
        for (Task t : taskList) {
            if (t.description.toLowerCase().contains(keyword)) {
                System.out.print("    " + index + ".");
                t.print();
                index++;
            }
        }
        System.out.println(Ui.LINE);
    }

    private void handleMark(String command) throws HumiException {
        try {
            Parser.parseCommand(command);
            int taskIndex = Parser.taskIndex;
            taskList.get(taskIndex - 1).mark();
            storage.markText(true, taskIndex);
        } catch (HumiException e) {
            System.out.println(e.message);
        }
    }

    private void handleUnmark(String command) throws HumiException {
        try {
            Parser.parseCommand(command);
            int taskIndex = Parser.taskIndex;
            taskList.get(taskIndex - 1).unmark();
            storage.markText(false, taskIndex);
        } catch (HumiException e) {
            System.out.println(e.message);
        }
    }

    private void handleDelete(String command) {
        try {
            Parser.parseCommand(command);
            int taskIndex = Parser.taskIndex;
            System.out.println(Ui.LINE);
            System.out.print("    Noted. I've removed this task:\n      ");
            taskList.get(taskIndex - 1).print();
            taskList.remove(taskIndex - 1);
            taskCount -= 1;
            System.out.println("    Now you have " + taskCount + " tasks in the list");
            System.out.println(Ui.LINE);
            storage.deleteText(taskIndex);
        } catch (HumiException e) {
            System.out.println(e.message);
        }
    }


    // add task from text file
    public void loadTask(String inputString) {
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
}
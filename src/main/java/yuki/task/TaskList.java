package yuki.task;

import yuki.Constants;

import yuki.InputParser;
import yuki.Utils;
import yuki.exceptions.YukiExceptions;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> taskData;
    String[] data;
    String description;

    public TaskList(ArrayList<Task> taskData){
        this.taskData = taskData;
    }

    public ArrayList<Task> getTaskData() {
        return taskData;
    }

    public void reportNumberOfTasks() {
        System.out.println("Now you have " + taskData.size() + " tasks in the list.");
    }

    public void listTasks() {
        System.out.println("Wake up your idea and do these tasks:");
        int index = 1;
        for (Task item : taskData) {
            System.out.println((index) + ".[" + item.getStatusIcon() + "] "
                    + item.taskType + " " + item.description);
            index++;
        }
        reportNumberOfTasks();
    }

    public void markTask(String line, String cmd) throws YukiExceptions.InvalidIndexException {
        int indexTask = Integer.parseInt(line.split(" ")[1]);
        if (indexTask < 0 || indexTask > taskData.size()) {
            throw new YukiExceptions.InvalidIndexException("Invalid index for marking: " + indexTask);
        }
        if (cmd.equals(Constants.MARK_COMMAND)){
            taskData.get(indexTask - 1).markAsDone();
        } else if (cmd.equals(Constants.UNMARK_COMMAND)) {
            taskData.get(indexTask - 1).markAsUndone();
        } else {
            System.out.println("invalid command in mark method");
        }
    }

    public void deleteTask(String line) throws YukiExceptions.InvalidIndexException {
        int indexTask = Integer.parseInt(line.split(" ")[1]);
        if (indexTask < 0 || indexTask > taskData.size()) {
            throw new YukiExceptions.InvalidIndexException("Invalid index for marking: " + indexTask);
        }
        Task toDelete = taskData.get(indexTask - 1);
        taskData.remove(indexTask - 1);
        System.out.println("Deleted task number " + (indexTask) + ": " + toDelete.description);
    }

    public void findTask(String line) {
        String keyword = InputParser.parseDescription(line);
        System.out.println("Tasks found:");
        taskData.stream()
                .filter(task -> task.description.contains(keyword))
                .forEach(System.out::println);
    }

    public void addTodo(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(Constants.LENGTH_TODO_COMMAND));
        if (data[0].isEmpty()) {
            throw new YukiExceptions.InvalidDescriptionException("Invalid description");
        }
        description = data[0];
        Task t = new Todo(description, false);
        taskData.add(t);
        Utils.printTaskAddedMessage();
        System.out.println(t);
        reportNumberOfTasks();
    }

    public void addDeadline(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(Constants.LENGTH_DEADLINE_COMMAND));
        if (data.length < 2) {
            throw new YukiExceptions.InvalidDescriptionException("Invalid description");
        }
        description = data[0] + " (by:" + data[1] + ")";
        Task t = new Deadline(description, false);
        taskData.add(t);
        Utils.printTaskAddedMessage();
        System.out.println(t);
        reportNumberOfTasks();
    }

    public void addEvent(String input) throws YukiExceptions.InvalidDescriptionException {
        data = InputParser.parseInput(input.substring(Constants.LENGTH_EVENT_COMMAND));
        if (data.length < 3) {
            throw new YukiExceptions.InvalidDescriptionException("Invalid description");
        }
        description = data[0] + " (from: " + data[1] + " to: " + data[2] + ")";
        Task t = new Event(description, false);
        taskData.add(t);
        Utils.printTaskAddedMessage();
        System.out.println(t);
        reportNumberOfTasks();
    }

    public void executeCommand(String command, String userInput) {
        Utils.printLine();
        switch (command) {
        case Constants.LIST_COMMAND:
            listTasks();
            break;
        case Constants.MARK_COMMAND:
            try {
                markTask(userInput, Constants.MARK_COMMAND);
            } catch (YukiExceptions.InvalidIndexException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid index\n");
                listTasks();
            }
            break;
        case Constants.UNMARK_COMMAND:
            try {
                markTask(userInput, Constants.UNMARK_COMMAND);
            } catch (YukiExceptions.InvalidIndexException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid index\n");
                listTasks();
            }
            break;
        case Constants.DELETE_COMMAND:
            try {
                deleteTask(userInput);
            } catch (YukiExceptions.InvalidIndexException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid index\n");
                listTasks();
            }
            break;
        case Constants.FIND_COMMAND:
            findTask(userInput);
            break;
        case Constants.TODO_COMMAND:
            try {
                addTodo(userInput);
            } catch (YukiExceptions.InvalidDescriptionException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid description\n");
                Utils.printInstructions();
            }
            break;
        case Constants.DEADLINE_COMMAND:
            try {
                addDeadline(userInput);
            } catch (YukiExceptions.InvalidDescriptionException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid description\n");
                Utils.printInstructions();
            }
            break;
        case Constants.EVENT_COMMAND:
            try {
                addEvent(userInput);
            } catch (YukiExceptions.InvalidDescriptionException e) {
                System.out.println(e.getMessage() + "\nPlease enter a valid description\n");
                Utils.printInstructions();
            }
            break;
        case Constants.HELP_COMMAND:
            Utils.printInstructions();
            break;
        default:
            Utils.printInvalidCommandWarning();
            Utils.printInstructions();
            break;
        }
        Utils.printLine();
    }
}

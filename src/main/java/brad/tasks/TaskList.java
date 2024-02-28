package brad.tasks;

import java.util.ArrayList;

import brad.exceptions.emptyArgumentException;
import brad.exceptions.invalidCommandException;
import brad.exceptions.invalidNumberException;
import brad.parser.Command;


public class TaskList {
    private ArrayList<Tasks> taskList = new ArrayList<>();
    private final String FILE_PATH = "data/data.md";

    public ArrayList<Tasks> getTaskList() {
        return taskList;
    }


    public String getList() {
        String output = "";
        for (int i = 1; i <= taskList.size(); i++) {
            output +=  i  + ". " + getTask(i) + "\n";
        }
        return output;
    }

    public void addToList(String input, TaskType type, boolean isDone) {
        Tasks newTask;
        if (type == TaskType.EVENT) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            String description = input.substring(0, fromIndex).strip();
            String startTime = input.substring(fromIndex + "/from ".length(), toIndex).trim();
            String endTime = input.substring(toIndex + "/to ".length()).trim();
            newTask = new Event(description, startTime, endTime, isDone);
        } else if (type == TaskType.DEADLINE) {
            int timeIndex = input.indexOf("/by");
            String description = input.substring(0, timeIndex).strip();
            String time = input.substring(timeIndex + "/by ".length()).strip();
            newTask = new Deadline(description, time, isDone);
        } else if (type == TaskType.TODO) {
            newTask = new Todo(input, isDone);
        } else {
            newTask = new Tasks(input, isDone);
        }
        taskList.add(newTask);

    }


    public String getTask(int n) {
        String output = taskList.get(n - 1).getFullDescription();
        return output;
    }

    public void markAsDone(int n, boolean isDone) {
        taskList.get(n - 1).setIsDone(isDone);
    }

    public int listSize() {
        return taskList.size();
    }

    public void deleteTask(int n) {
        taskList.remove(n - 1);
    }

    public String executeCommand(Command command, String userInput)
            throws invalidCommandException, emptyArgumentException, invalidNumberException {
        String message;
        switch (command) {
            case MARK:
                message = doMarkAction(userInput);
                break;
            case UNMARK:
                message = doUnmarkAction(userInput);
                break;
            case TODO:
                message = doTodoAction(userInput);
                break;
            case DEADLINE:
                message = doDeadlineAction(userInput);
                break;
            case EVENT:
                message = doEventAction(userInput);
                break;
            case DELETE:
                message = doDeleteAction(userInput);
                break;
            case FIND:
                message = doFindAction(userInput);
                break;
            default:
                throw new invalidCommandException();
        }
        return message;
    }

    private String doMarkAction(String input) throws
            invalidNumberException, emptyArgumentException {
        int taskNumber = Integer.parseInt(input);
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        if (taskNumber > listSize()) {
            throw new invalidNumberException();
        }
        markAsDone(taskNumber, true);
        return getTask(taskNumber);
    }

    private String doUnmarkAction(String input) throws
            invalidNumberException, emptyArgumentException {
        int taskNumber = Integer.parseInt(input);
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        if (taskNumber > listSize()) {
            throw new invalidNumberException();
        }
        markAsDone(taskNumber, false);
        return getTask(taskNumber);
    }

    private String doTodoAction(String input) {
        addToList(input, TaskType.TODO, false);
        return getTask(listSize());
    }

    private String doDeadlineAction(String input) {
        addToList(input, TaskType.DEADLINE, false);
        return getTask(listSize());
    }

    private String doEventAction(String input) {
        addToList(input, TaskType.EVENT, false);
        return getTask(listSize());
    }

    private String doDeleteAction(String input) throws
            emptyArgumentException, invalidNumberException {
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        int taskNumber = Integer.parseInt(input);
        if (taskNumber > listSize()) {
            throw new invalidNumberException();
        }
        String task = getTask(taskNumber);
        deleteTask(taskNumber);
        return task;
    }

    private String doFindAction(String keyword) throws
            emptyArgumentException {
        ArrayList <Tasks> matchedTasks = new ArrayList<>();
        if (keyword.isBlank()) {
            throw new emptyArgumentException();
        }
        String message = "";
        int index = 1;
        for (Tasks tasks : taskList) {
            if (tasks.getTaskDescription().contains(keyword)) {
                message += index + ". " + tasks.getFullDescription();
                message += "\n";
                index += 1;
            }
        }
        return message;
    }
}


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

    /**
     * Returns the entire task list
     * @return full list of tasks
     */
    public String getList() {
        String output = "";
        for (int i = 1; i <= taskList.size(); i++) {
            output +=  i  + ". " + getTask(i) + "\n";
        }
        return output;
    }

    /**
     * Add user input task to the list based on the type
     * @param input full user input
     * @param type user command type
     * @param isDone boolean to mark task as not done yet
     */
    public void addToList(String input, TaskType type, boolean isDone) {
        Tasks newTask = null;
        if (type == TaskType.EVENT) {
            try {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                String description = input.substring(0, fromIndex).strip();
                String startTime = input.substring(fromIndex + "/from ".length(), toIndex).trim();
                String endTime = input.substring(toIndex + "/to ".length()).trim();
                newTask = new Event(description, startTime, endTime, isDone);
                taskList.add(newTask);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Uh oh, please enter the appropriate command!");
                System.out.println("event <task> /from <start time> /to <end time>");
            }

        } else if (type == TaskType.DEADLINE) {
            try {
                int timeIndex = input.indexOf("/by");
                String description = input.substring(0, timeIndex).strip();
                String time = input.substring(timeIndex + "/by ".length()).strip();
                newTask = new Deadline(description, time, isDone);
                taskList.add(newTask);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Uh oh, please enter the appropriate command!");
                System.out.println("deadline <task> /by <time>");
            }

        } else if (type == TaskType.TODO) {
            newTask = new Todo(input, isDone);
            taskList.add(newTask);
        } else {
            newTask = new Tasks(input, isDone);
            taskList.add(newTask);
        }
    }

    /**
     * Gets nth task on the task list
     * @param n the task number on the list
     * @return task queried
     */
    public String getTask(int n) {
        String output = taskList.get(n - 1).getFullDescription();
        return output;
    }

    /**
     * Marks nth task on the list as done or not done
     * @param n the task number on the list
     * @param isDone if task is to be marked as done or not done
     */
    public void markAsDone(int n, boolean isDone) {
        taskList.get(n - 1).setIsDone(isDone);
    }

    public int listSize() {
        return taskList.size();
    }

    public void deleteTask(int n) {
        taskList.remove(n - 1);
    }

    /**
     * Calls the corresponding action to process user input based on command given
     * @param command input command
     * @param userInput user string input
     * @return message to be printed based on processed input
     * @throws invalidCommandException if user command is not valid
     * @throws emptyArgumentException if no argument (task) is given
     * @throws invalidNumberException if user is trying to access a task number that
     * is greater of the list size
     */
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

    /**
     * Mark task as done
     * @param input task to be mark as done
     * @return marked task to be printed for user
     * @throws invalidNumberException if input number is invalid on task list
     * @throws emptyArgumentException if no task number is specified
     */
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

    /**
     * Mark task as not done
     * @param input task to be mark as not done
     * @return unmarked task to be printed for user
     * @throws invalidNumberException if input number is invalid on task list
     * @throws emptyArgumentException if no task number is specified
     */
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

    /**
     * Categorize task as a TODO
     * and add to the task list
     * @param input task to be added as a TODO
     * @return task that was added
     */
    private String doTodoAction(String input) {
        addToList(input, TaskType.TODO, false);
        return getTask(listSize());
    }

    /**
     * Categorize task as a DEADLINE
     * and add to the task list
     * @param input task to be added as a DEADLINE
     * @return task that was added
     */
    private String doDeadlineAction(String input) {
        addToList(input, TaskType.DEADLINE, false);
        return getTask(listSize());
    }

    /**
     * Categorize task as an EVENT
     * and add to the task list
     * @param input task to be added as a EVENT
     * @return task that was added
     */
    private String doEventAction(String input) {
        addToList(input, TaskType.EVENT, false);
        return getTask(listSize());
    }

    /**
     * Delete a user specified task from the list
     * @param input task to be deleted
     * @return task that was deleted
     * @throws emptyArgumentException if no task is specified
     * @throws invalidNumberException if task number is invalid
     */
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

    /**
     * Find a task from the list based on the given keyword
     * @param keyword keyword to base the search on
     * @return task(s) that contain the specified keyword
     * @throws emptyArgumentException if no keyword is specified by user
     */
    private String doFindAction(String keyword) throws
            emptyArgumentException {
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


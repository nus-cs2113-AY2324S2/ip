package tasks;

import java.util.ArrayList;

import exceptions.DuplicateUnmarkException;
import storage.Storage;
import java.io.IOException;
import exceptions.DuplicateMarkException;
import exceptions.EmptyTaskException;
import parser.Parse;

/**
 * Represents an array of tasks
 * Supports operations on tasks: add, mark, unmark, delete, find
 */
public class Task {
    public static int taskLength = 0;
    public static ArrayList<Task> list = new ArrayList<>();
    public static final String NEW_LINE = "____________________________________________________________\n";
    public String description;
    public boolean isDone;
    public String taskType;

    public Task(String description, boolean taskDone) {
        this.description = description;
        this.isDone = taskDone;
    }

    /**
     * Adds either a todo, deadline or event
     *
     * @param taskType input from user in the command line.
     * @param taskDone true if task is done, false otherwise
     * @param task description of tasks
     * @param quietLoad set to true to display task when created by user, but set to false when loading tasks from a
     *                  save file to avoid unnecessary displaying of tasks
     */
    public static void createTask(String taskType, boolean taskDone, String task, boolean quietLoad) {
        switch (taskType) {
        case "todo":
            list.add(new Todo(task, taskDone));
            break;
        case "deadline":
            list.add(new Deadline(task, taskDone));
            break;
        default:
            list.add(new Event(task, taskDone));
        }
        taskLength++;
        if (!quietLoad) {
            System.out.println(NEW_LINE + "Okay, I've added: " + task + "\n" + NEW_LINE);
        }
    }

    /**
     * Removes the task given by the index
     *
     * @param taskNumber index of task to be deleted
     * @throws IOException if index is out of range
     */
    public static void deleteTask(int taskNumber) {
        Task task = list.get(taskNumber);
        list.remove(taskNumber);
        taskLength--;
        try {
            Storage.saveFile(list);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println(NEW_LINE + "Okay, I've removed: " + task.description + "\n" + NEW_LINE);
    }

    /**
     * Process the type of user input and proceeds with the appropriate task formatting, creation and exceptions
     *
     * @param input user input's to be processed
     * @throws EmptyTaskException if the task is empty
     * @throws IndexOutOfBoundsException if task is out of bounds
     * @throws IOException for other types of exceptions
     */
    public static void handleTasks(String input) throws EmptyTaskException {
        int index = input.indexOf(" ");
        String taskType = input.substring(0, index);
        String taskContent = input.substring(index);
        String taskFinal;

        switch (taskType) {
        case "deadline":
            try {
                taskFinal = Parse.formatDeadline(taskContent);
                createTask(taskType, false, taskFinal, false);
                Storage.saveFile(list);
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Cannot parse deadline start date!\n"
                        + "Format: deadline [task] /by [time]");
            }
            catch (IOException e) {
                System.out.println("Error saving file" + e.getMessage());
            }
            break;

        case "event":
            try {
                taskFinal = Parse.formatEvent(taskContent);
                createTask(taskType, false, taskFinal, false);
                Storage.saveFile(list);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Cannot parse event start/end date!\n"
                        +  "Format: event [task] /from [start time] /to [end time]");
            } catch (IOException e) {
                System.out.println("Error saving file" + e.getMessage());
            }
            break;

        default:
            if (taskContent.trim().isEmpty()) {
                throw new EmptyTaskException();
            } else {
                try {
                    createTask(taskType, false, taskContent, false);
                    Storage.saveFile(list);
                } catch (IOException e) {
                    System.out.println("Error saving file" + e.getMessage());
                }
            }
        }
    }

    public static void showTasks() {
        System.out.println(NEW_LINE);
        System.out.println("Your current tasks");
        for (int i = 0; i < taskLength; i++){
            System.out.print((i + 1) + ". ");
            System.out.println(list.get(i).getTask());
        }
        System.out.print(NEW_LINE);
    }

    /**
     * Finds the task matching user inputs and displays them
     *
     * @param task keyword to match inputs
     */
    public static void findTask(String task) {
        String keyword = task.split("find ")[1].trim();
        int count = 0;
        for (Task item: list) {
            if (item.description.contains(keyword)) {
                System.out.print((count + 1) + ". ");
                System.out.println(item.getTask());
                count++;
            }
        }
    }

    /**
     * Marks the corresponding task in list
     *
     * @param taskNumber the task to be marked
     * @throws DuplicateMarkException exception thrown if a task if marked again
     */
    public static void mark(int taskNumber) throws DuplicateMarkException {
        if (list.get(taskNumber - 1).isDone) {
            throw new DuplicateMarkException();
        } else {
            list.get(taskNumber - 1).markAsDone();
            try {
                Storage.saveFile(list);
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Good job! I've marked task " + taskNumber + " as done");
        }
    }

    /**
     * Unmark the corresponding task in list
     *
     * @param taskNumber the task to be unmarked
     * @throws DuplicateMarkException exception thrown if a task if unmarked again
     */
    public static void unMark(int taskNumber) throws DuplicateUnmarkException{
        if (!list.get(taskNumber - 1).isDone) {
            throw new DuplicateUnmarkException();
        } else {
            list.get(taskNumber - 1).markNotDone();
            try {
                Storage.saveFile(list);
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Okay! I've marked task " + taskNumber + " as not done");
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Format the task, taskType and description
     */
    public String getTask() {
        String taskType;
        if (this.getClass().toString().equals("class tasks.Todo")) {
            taskType = "[T]";
        } else if (this.getClass().toString().equals("class tasks.Deadline")) {
            taskType = "[D]";
        } else {
            taskType = "[E]";
        }
        this.taskType = taskType;
        return (taskType + "[" + (isDone ? "X" : " ") + "]" + this.description);
    }

}

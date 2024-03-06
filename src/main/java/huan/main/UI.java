package huan.main;

import huan.task.DeadlineTask;
import huan.task.Task;

import java.time.LocalDateTime;

public class UI {
    private static String botName = "Huan";

    /**
     * Display welcome message
     */
    public static void displayWelcomeMessage() {
        System.out.println("Hello! I'm " + botName + ", a chat bot");
    }

    /**
     * Display a separator line
     */
    public static void displaySeparator() {
        System.out.println("-------------------------");
    }

    /**
     * Display a message to inform format error, and show the correct format
     * @param correctFormat the correct format, as well as any necessary information
     */
    public static void displayFormatError(String correctFormat) {
        System.out.println("Invalid format! Should be " + correctFormat);
    }

    /**
     * Display a message informing an unrecognized message
     */
    public static void displayUnrecognizedMessage() {
        System.out.println("Unrecognized command, please try again!");
    }

    /**
     * Display a message informing success adding a new TodoTask
     * @param taskName name of the task
     */
    public static void displayAddTodoSuccess(String taskName) {
        System.out.println("Added todo type task with name: " + taskName);
    }

    /**
     * Display a message informing success adding a new EventTask
     * @param taskName name of the task
     */
    public static void displayAddEventSuccess(String taskName) {
        System.out.println("Added event type task with name: " + taskName);
    }

    /**
     * Display a message informing success adding a new DeadlineTask
     * @param taskName
     */
    public static void displayAddDeadlineSuccess(String taskName) {
        System.out.println("Added deadline type task with name: " + taskName);
    }

    /**
     * Display a message informing success deleting a new task
     * @param deleteIndex index of the to-be-deleted task
     */
    public static void displayDeleteTaskSuccess(int deleteIndex) {
        System.out.println("Removed task number " + deleteIndex + ": " + TaskList.tasks.get(deleteIndex - 1).getName());
    }

    /**
     * Display a message informing success marking a Task as completed
     * @param markIndex index of the to-be-marked task
     */
    public static void displayMarkTaskSuccess(int markIndex) {
        System.out.println("Set task number " + markIndex + ": " + TaskList.tasks.get(markIndex - 1).getName() + " as done.");
    }

    /**
     * Display a message informing success unmarking a Task as completed
     * @param unmarkIndex index of the to-be-unmarked task
     */
    public static void displayUnmarkTaskSuccess(int unmarkIndex) {
        System.out.println("Set task number " + unmarkIndex + ": " + TaskList.tasks.get(unmarkIndex - 1).getName() + " as not done.");
    }

    /**
     * Display a message informing the index input is incorrect
     */
    public static void displayIndexError() {
        System.out.println("Invalid task index!");
    }

    /**
     * Display a goodbye message
     */
    public static void displayByeMessage() {
        System.out.println("Bye! See ya!");
    }

    /**
     * Show the matching tasks in the list, with a given keyword
     * @param keyword the keyword for matching
     */
    public static void displayMatchingTasks(String keyword) {
        int cnt = 0;
        for (Task task : TaskList.tasks) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                cnt += 1;
                task.printTask();
            }
        }
        System.out.println("Found " + cnt + " matching tasks.");
    }

    /**
     * Display a message informing success in parsing a LocalDateTime
     */
    public static void displayDateTimeParseSuccess() {
        System.out.println("Parsing dateTime success!");
    }

    /**
     * Display a message informing failure in parsing a LocalDateTime
     */
    public static void displayDateTimeParseError() {
        System.out.println("Parsing dateTime failed, Use format 'yyyy-mm-dd HH:mm:ss'");
    }

    /**
     * List all tasks in the List
     */
    public static void listTasks() {
        int cnt = 0;
        System.out.println("You have a total of " + TaskList.tasks.size() + " tasks.");
        for (Task task : TaskList.tasks) {
            cnt += 1;
            System.out.printf(cnt + ". ");

            task.printTask();
        }
    }

    /**
     * List all DeadlineTasks before the given date time
     * @param dateTime the given datetime
     */
    public static void listTaskBeforeDateTime(LocalDateTime dateTime) {
        int cnt = 0;
        for (Task task : TaskList.tasks) {
            if (task.getTaskType() == 3) {
                DeadlineTask ddlTask = (DeadlineTask)task;
                if(ddlTask.isBefore(dateTime)) {
                    cnt += 1;
                    ddlTask.printTask();
                }
            }
        }
        System.out.println("You have a total of " + cnt + " deadlines before " + dateTime);
    }
}

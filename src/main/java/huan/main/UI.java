package huan.main;

import huan.task.DeadlineTask;
import huan.task.Task;

import java.time.LocalDateTime;

public class UI {
    private static String botName = "Huan";

    public static void displayWelcomeMessage() {
        System.out.println("Hello! I'm " + botName + ", a chat bot");
    }

    public static void displayListTask() {
        System.out.println("You have a total of " + TaskList.tasks.size() + " tasks.");
    }

    public static void displaySeparator() {
        System.out.println("-------------------------");
    }

    public static void displayFormatError(String correctFormat) {
        System.out.println("Invalid format! Should be " + correctFormat);
    }

    public static void displayUnrecognizedMessage() {
        System.out.println("Unrecognized command, please try again!");
    }

    public static void displayAddTodoSuccess(String taskName) {
        System.out.println("Added todo type task with name: " + taskName);
    }

    public static void displayAddEventSuccess(String taskName) {
        System.out.println("Added event type task with name: " + taskName);
    }

    public static void displayAddDeadlineSuccess(String taskName) {
        System.out.println("Added deadline type task with name: " + taskName);
    }

    public static void displayDeleteTaskSuccess(int deleteIndex) {
        System.out.println("Removed task number " + deleteIndex + ": " + TaskList.tasks.get(deleteIndex - 1).getName());
    }

    public static void displayMarkTaskSuccess(int markIndex) {
        System.out.println("Set task number " + markIndex + ": " + TaskList.tasks.get(markIndex - 1).getName() + " as done.");
    }

    public static void displayUnmarkTaskSuccess(int unmarkIndex) {
        System.out.println("Set task number " + unmarkIndex + ": " + TaskList.tasks.get(unmarkIndex - 1).getName() + " as not done.");
    }

    public static void displayIndexError() {
        System.out.println("Invalid task index!");
    }

    public static void displayByeMessage() {
        System.out.println("Bye! See ya!");
    }

    public static void displayDateTimeParseSuccess() {
        System.out.println("Parsing dateTime success!");
    }

    public static void displayDateTimeParseError() {
        System.out.println("Parsing dateTime failed, Use format 'yyyy-mm-dd HH:mm:ss'");
    }

    public static void listTasks() {
        int cnt = 0;
        System.out.println("You have a total of " + TaskList.tasks.size() + " tasks.");
        for (Task task : TaskList.tasks) {
            cnt += 1;
            System.out.printf(cnt + ". ");

            task.printTask();
        }
    }

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

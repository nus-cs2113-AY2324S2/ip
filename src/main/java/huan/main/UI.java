package huan.main;

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
}

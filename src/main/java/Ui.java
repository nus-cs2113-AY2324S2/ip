import java.io.IOException;

/**
 * Handles interactions with the user.
 */
public class Ui {

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Sunny\nWhat can I do for you?\n");
    }

    /**
     * Displays a goodbye message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating that tasks have been successfully loaded.
     */
    public void showTasksLoaded() {
        System.out.println("Tasks loaded successfully!" + System.lineSeparator());
    }

    /**
     * Displays a message indicating that tasks have been successfully saved.
     */
    public void showTasksSaved() {
        System.out.println("Tasks saved successfully!" + System.lineSeparator());
    }

    /**
     * Displays the message indicating the start of the task list.
     */
    public void showTaskList() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Displays a message for an invalid command.
     */
    public void showInvalidCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(" + System.lineSeparator());
    }

    /**
     * Displays a message for an invalid task index.
     */
    public void showInvalidTaskIndexMessage() {
        System.out.println("Invalid task index. Please provide a valid task index.");
    }

    /**
     * Displays a message for marking a task as done.
     */
    public void showTaskAsMarked() {
        System.out.println("Nice! I've marked this task as done!");
    }

    /**
     * Displays a message for marking a task as not done.
     */
    public void showTaskAsUnmarked() {
        System.out.println("OK, I've marked this task as not done yet.");
    }

    /**
     * Displays a message for successfully adding a task.
     */
    public void showTaskAdded() {
        System.out.println("Got it! I have successfully added:");
    }

    /**
     * Displays a message for removing a task.
     */
    public void showTaskDeleted() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Displays a message with the current task count.
     */
    public void showTaskCountMessage() {
        int taskCount = Sunny.tasksList.size();
        String taskNoun = (taskCount == 1) ? "task" : "tasks";
        System.out.println("Now you have " + taskCount + " " + taskNoun + " in the list." + System.lineSeparator());
    }

    /**
     * Displays a message for the creation of a file.
     */
    public void showFileCreated() {
        System.out.println("File created: " + Sunny.FILE_PATH);
    }

    /**
     * Displays a message for found tasks during a search.
     */
    public void showFoundTasks() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Handles and displays errors based on exception types.
     *
     * @param e The exception to be handled.
     */
    public static void handleErrors(Exception e) {
        if (e instanceof IOException) {
            System.out.println("OOPS!!! An error occurred while handling the file.");
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.println("OOPS!!! The command seems to be incomplete or incorrect.");
        } else if (e instanceof NumberFormatException) {
            System.out.println("OOPS!!! Please provide a valid task index.");
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println("OOPS!!! The description cannot be empty.");
        } else if (e instanceof IllegalArgumentException) {
            System.out.println("OOPS!!! " + e.getMessage());
        } else {
            System.out.println("OOPS!!! I'm sorry, but I encountered an unexpected error.");
        }
        System.out.println(" ");
    }
}

package kurobot;

import java.util.ArrayList;

/**
 * Display contents to the user, such as welcome
 * and goodbye messages, errors and current tasks.
 */
public class Ui {

    private final String LOGO =
            " ___   ___    ___    ___ \n"
                    + "|   |/   /   |  |   |  | \n"
                    + "|       /    |  |   |  | \n"
                    + "|   |\\   \\   |_ |___| _| \n"
                    + "|___| \\___\\    |_____|   \n";


    private final int LINE_LEN = 60;
    private final String LINE =  "-".repeat(LINE_LEN);

    /**
     * Display a welcome message to greet the user.
     */
    public void showWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm KuroBot\n" + "What can I do for you?");
        System.out.println(LOGO);
        System.out.println(LINE);
    }

    /**
     * Display goodbye message to end the session.
     */
    public void showGoodByeMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        System.out.println(LOGO);
    }

    /**
     * Indicate that the command entered is invalid
     * to prompt for a new valid command.
     */
    public void showInvalidCommand() {
        System.out.println(LINE);
        System.out.println("Whoops! Please enter a valid command~");
        System.out.println(LINE);
    }

    /**
     * Print out all the tasks in the list.
     *
     * @param tasks Current tasks.
     */
    public void printTasks(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks){
            System.out.println(tasks.indexOf(task)+1 + "." + task.printTask());
        }
        System.out.println(LINE);
    }

    /**
     * Show that the details regarding the task was omitted.
     */
    public void showNoTaskGiven() {
        System.out.println(LINE);
        System.out.println("Hmmm.. what is it about?");
        System.out.println(LINE);
    }

    /**
     * Show that the deadline or start and end time of the task was omitted.
     */
    public void showNoTimingGiven() {
        System.out.println(LINE);
        System.out.println("uhoh! don't forget the time!");
        System.out.println(LINE);
    }

    /**
     * Show that the entered task does not exist.
     */
    public void showNoSuchTask() {
        System.out.println(LINE);
        System.out.println("there's no such task though...");
        System.out.println(LINE);
    }

    /**
     * Show that the index of the task is omitted.
     */
    public void showNoIndexGiven() {
        System.out.println(LINE);
        System.out.println("mhmm.. which task? >.<");
        System.out.println(LINE);
    }

    /**
     * Display the task when it is added or been removed.
     *
     * @param task Task to be added or removed.
     * @param taskNum Total number of tasks in the list.
     * @param isAdd True if the task should be added, false if should be removed.
     */
    public void printGivenTask(Task task, int taskNum, boolean isAdd) {
        System.out.println(LINE);
        String text = isAdd ? "Got it. I've added this task:" : "Noted. I've removed this task:";
        System.out.println(text);
        System.out.println(task.printTask());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        System.out.println(LINE);
    }
}

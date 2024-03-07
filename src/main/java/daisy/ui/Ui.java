package daisy.ui;

import daisy.task.Task;

/**
 * The Ui class handles the program printouts to the user.
 */

public class Ui {

    protected static String INTRO_PROMPT = "Good day! This is Daisy.\nAny task for today?";
    protected final static String EXIT_PROMPT = "Ending prompt received. Remember to keep to the deadlines!";
    protected final static String LINE_BREAK = "____________________________________";

    /**
     * Creates a ui instance.
     */
    public Ui() {
    }

    /**
     * Outputs a linebreak.
     */
    public void setLineBreak() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Sends intro prompt to the user. This should be executed at the start of the program
     */
    public void sendIntro() {
        setLineBreak();
        System.out.println(INTRO_PROMPT);
        setLineBreak();
    }

    /**
     * Sends ending prompt to the user. This should be executed at the end of the program.
     */
    public void sendExit() {
        System.out.println(EXIT_PROMPT);
        setLineBreak();
    }

    /**
     * Prints out the String representation of a task object. The String is obtained by calling the corresponding toString()
     * method of the task object
     * @param task task object which require printing
     * @see Task
     */
    public void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints out the success message for the successful marking of a task.
     * @param task task object that is marked
     */
    public void printMarked(Task task) {
        System.out.println("Congrats on completing the task!");
        printTask(task);
    }

    /**
     * Prints out the success message for the successful unmarking of a task.
     * @param task task object that is unmarked
     */
    public void printUnmarked(Task task) {
        System.out.println("More time needed for the following task? Sure!");
        printTask(task);
    }

    /**
     * Alerts the user on the MissingInformationException
     * @see daisy.error.MissingInformationException
     */
    public void printIndexMissingError() {
        System.out.println("Error! No index detected for this operation. Try again!");
    }

    /**
     * Alerts the user on the IllegalFindFormatException
     */
    public void printFindMissingError() {
        System.out.println("Error! No keyword detected for find. Try again!");
    }

    /**
     * Alerts the user on the IllegalTodoFormatException
     * @see daisy.error.IllegalTodoFormatException
     */
    public void printTodoMissingError() {
        System.out.println("Error! No event detected for todo. Try again!");
    }

    /**
     * Alerts the user on the IllegalDeadlineFormatException
     * @see daisy.error.IllegalDeadlineFormatException
     */
    public void printDeadlineInputError() {
        System.out.println("Error! Deadline entry is not following format. Try again!");
    }

    /**
     * Alerts the user on the IllegalEventFormatException
     * @see daisy.error.IllegalEventFormatException
     */
    public void printEventInputError() {
        System.out.println("Error! Event entry is not following format. Try again!");
    }

    /**
     * Alerts the user on the IllegalEntryException
     * @see daisy.error.IllegalEntryException
     */
    public void printInvalidCommandError() {
        System.out.println("Your input does not match any of my programs! Try again!");
    }

}

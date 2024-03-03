package Xavier;

import Exceptions.InvalidInputException;
import Exceptions.NoInputException;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    public static final String LINE = "_________________________________________________________________";

    /** Specifies whether user has input the exit command  */
    public boolean isExit = false;
    static Scanner input = new Scanner(System.in);

    /** Prints the welcome message upon the start of the application  */
    public void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + "Xavier");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /** Prints the error message when unable to load file  */
    public void showLoadingError() {
        System.out.println("Unable to load file");
    }

    /** Prints a line divider */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads in command for the user and passes it to the parser
     *
     * @param parser the Parser object which parses the input and executes the command
     * @param taskList the list of task and list related operations
     * @param ui the Ui object which handles printing to the user interface
     * @param storage the Storage object which handles saving and reading the file
     */
    public void readCommand(Parser parser, TaskList taskList, Ui ui, Storage storage) {
        String command = input.nextLine();
        String[] stringArray = command.split(" ");
        showLine();
        try {
            parser.parse(command, taskList, ui, storage);
        } catch (NoInputException e) {
            System.out.println("OOPS!!! The description of a " + stringArray[0] + " cannot be empty.");
        } catch (InvalidInputException e) {
            if (stringArray[0].equals("deadline")) {
                System.out.println("OOPS!!! " + stringArray[0] + " must contains /by");
            } else if (stringArray[0].equals("event")) {
                System.out.println("OOPS!!! " + stringArray[0] + " must contains /from and /to");
            } else {
                printErrorMessage();
            }
        }
        showLine();
    }

    /**
     * Prints the confirmation message when a task has been successfully added to the list
     *
     * @param taskList the list of task and list related operations
     */
    public void printAddTaskMessage(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getLastTask());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Prints the confirmation message when a task has been successfully deleted to the list
     *
     * @param taskList the list of task and list related operations
     * @param index the index of the task intended for deletion
     */
    public void printDeleteTaskMessage(TaskList taskList, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.getTask(index));
        System.out.println("Now you have " + (taskList.getSize()-1) + " tasks in the list.");
    }

    /**
     * Prints the confirmation message when a task has been successfully marked as done
     *
     * @param taskList the list of task and list related operations
     * @param index the index of the task to be marked as done
     */
    public void printMarkTaskMessage(TaskList taskList, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.getTask(index));
    }

    /**
     * Prints the confirmation message when a task has been successfully unmarked as done
     *
     * @param taskList the list of task and list related operations
     * @param index the index of the task to be unmarked as done
     */
    public void printUnmarkTaskMessage(TaskList taskList, int index) {
        System.out.println("OK, I've marked this task as not yet done:");
        System.out.println(taskList.getTask(index));
    }

    /** Prints the error message when user inputs invalid input  */
    private void printErrorMessage() {
        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /** Prints the exit message and terminates the program  */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
        isExit = true;
    }
}

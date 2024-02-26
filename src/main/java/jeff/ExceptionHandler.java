package jeff;

import java.io.IOException;

/**
 * Provides static methods for handling various types of exceptions that may occur during program execution.
 */
public class ExceptionHandler {

    /**
     * Handles the invalid command exception by printing an error message and listing valid commands.
     */
    public static void handleInvalidCommandException() {
        Printer.printIndent("Invalid command.");
        Printer.printIndent("List of valid commands:");
        Printer.printIndent("list, todo, deadline, event, mark, unmark, bye");
    }

    /**
     * Handles the invalid todo syntax exception by printing an error message.
     * Provides guidance on the correct syntax for creating a todo task.
     */
    public static void handleInvalidTodoSyntaxException() {
        Printer.printIndent("Invalid todo syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("todo [description]");
    }

    /**
     * Handles the invalid deadline syntax exception by printing an error message.
     * Provides guidance on the correct syntax for creating a deadline task.
     */
    public static void handleInvalidDeadlineSyntaxException() {
        Printer.printIndent("Invalid deadline syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("deadline [description] /by [yyyy-mm-dd]");
    }

    /**
     * Handles the invalid event syntax exception by printing an error message.
     * Provides guidance on the correct syntax for creating an event task.
     */
    public static void handleInvalidEventSyntaxException() {
        Printer.printIndent("Invalid event syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("event [description] /from [from] /to [to]");
    }

    /**
     * Handles the invalid mark syntax exception by printing an error message.
     * Provides guidance on the correct syntax for marking a task.
     */
    public static void handleInvalidMarkSyntaxException() {
        Printer.printIndent("Invalid mark syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("mark [any number from 1 to " + TaskList.size() + "]");
    }

    /**
     * Handles the invalid unmark syntax exception by printing an error message.
     * Provides guidance on the correct syntax for unmarking a task.
     */
    public static void handleInvalidUnmarkSyntaxException() {
        Printer.printIndent("Invalid unmark syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("unmark [any number from 1 to " + TaskList.size() + "]");
    }

    /**
     * Handles the invalid delete syntax exception by printing an error message.
     * Provides guidance on the correct syntax for deleting a task.
     */
    public static void handleInvalidDeleteSyntaxException() {
        Printer.printIndent("Invalid delete syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("delete [any number from 1 to " + TaskList.size() + "]");
    }

    /**
     * Handles the file not found exception by creating a new file and printing a message.
     */
    public static void handleInvalidFindSyntaxException() {
        Printer.printIndent("Invalid find syntax.");
        Printer.printIndent("Correct syntax should be:");
        Printer.printIndent("find [text to find]");
    }

    public static void handleFileNotFoundException() {
        Storage.createNewFile();
        Printer.printIndent("File not found. data/jeff.txt created.");
        Printer.printDivider();
    }

    /**
     * Handles the corrupt file exception by printing an error message and exits the program.
     */
    public static void handleCorruptFileException() {
        Printer.printIndent("File is corrupt. Content not in expected format.");
        Printer.printDivider();
        System.exit(1);
    }

    /**
     * Handles the IOException by printing the specific error message from the exception and exits the program.
     *
     * @param e The IOException that occurred.
     */
    public static void handleIOException(IOException e) {
        Printer.printIndent("Something went wrong: " + e.getMessage());
        Printer.printDivider();
        System.exit(1);
    }

    /**
     * Handles the exception when unable to mark a task.
     * Prints a message indicating the inability to mark the task.
     */
    public static void handleUnableToMarkException() {
        Printer.printUnableToMark();
    }

    /**
     * Handles the exception when unable to unmark a task.
     * Prints a message indicating the inability to unmark the task.
     */
    public static void handleUnableToUnmarkException() {
        Printer.printUnableToUnmark();
    }

    /**
     * Handles the exception when unable to delete a task.
     * Prints a message indicating the inability to delete the task.
     */
    public static void handleUnableToDeleteException() {
        Printer.printUnableToDelete();
    }

    /**
     * Handles the exception when an invalid character '|' is found in the task description.
     * Prints a message indicating that '|' should not be used in the task description.
     */
    public static void handleInvalidCharacterInDescriptionException() {
        Printer.printIndent("Please do not use '|' in the task description.");
    }

    /**
     * Handles the exception when an invalid character '|' or '-' is found after "/from" in an event command.
     * Prints a message indicating that '|' or '-' should not be used after "/from" in an event command.
     */
    public static void handleInvalidCharacterInFromException() {
        Printer.printIndent("Please do not use '|' or '-' after /from.");
    }

    /**
     * Handles the exception when an invalid character '|' or '-' is found after "/to" in an event command.
     * Prints a message indicating that '|' or '-' should not be used after "/to" in an event command.
     */
    public static void handleInvalidCharacterInToException() {
        Printer.printIndent("Please do not use '|' or '-' after /to.");
    }
}

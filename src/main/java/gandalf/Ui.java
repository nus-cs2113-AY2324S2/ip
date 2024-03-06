package gandalf;

import action.Task;

import exception.EmptyFindException;
import exception.IncompleteCommandException;
import exception.InvalidDeadlineFormatException;
import exception.InvalidKeywordException;
import exception.InvalidTaskDeletionException;
import exception.InvalidTaskIndexException;
import exception.MissingDescriptionException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles user interface interactions.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________";
    public static final String BYE_STATEMENT = "bye";

    /** Initializes the Scanner and ArrayList Objects. */
    static Scanner in = new Scanner(System.in);
    static ArrayList<Task> listTasks = new ArrayList<>();

    /** A flag indicating whether the program should exit. */
    public static boolean isExit = false;

    /**
     * Reads user input and handles it accordingly.
     * If the user inputs "bye", sets the isExit flag to true and saves tasks to storage.
     * Otherwise, handles the user command.
     */
    public static void readUserInput() {
        String userInput = getUserInput();
        if (hasSaidBye(userInput)) {
            isExit = true;
            System.out.println(LINE);
            Storage.saveTasks(listTasks);
        } else {
            handleUserCommand(userInput, listTasks, false);
        }
    }

    /**
     * Reads the user input from the console.
     *
     * @return A string containing the user's input.
     */
    public static String getUserInput() {
        return in.nextLine();
    }

    /** Initializes Gandalf by loading previous data and printing add item message. */
    static void initializeGandalf() {
        Storage.loadData(listTasks);
        printAddItemMessage();
    }

    /**
     * Handles the user command based on the input.
     *
     * @param userInput The user input.
     * @param listTasks The list of tasks.
     * @param hideInput Boolean flag indicating whether to hide input or not.
     */
    public static void handleUserCommand(String userInput, ArrayList<Task> listTasks, boolean hideInput)  {
        try {
            if (hasSaidMarkOrUnmark(userInput)) {
                TaskList.handleTasksMarkings(userInput, listTasks, hideInput);
            } else if (hasSaidDelete(userInput)) {
                TaskList.deleteUserTasks(userInput, listTasks);
            } else if (hasSaidList(userInput)) {
                printListOfTasks(listTasks);
            } else if (hasSaidFind(userInput)) {
                TaskList.findUserTasks(userInput, listTasks);
            } else {
                TaskList.insertUserTasks(userInput, listTasks, hideInput);
            }
        } catch (InvalidKeywordException e) {
            printInvalidKeywordMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            printListIsFullMessage();
        } catch (MissingDescriptionException e) {
            printMissingDescriptionMessage();
        } catch (InvalidTaskIndexException e) {
            printInvalidTaskIndexMessage();
        } catch (InvalidTaskDeletionException e) {
            printInvalidTaskDeletionMessage();
        } catch (IncompleteCommandException e) {
            printIncompleteCommandMessage();
        } catch (EmptyFindException e) {
            printEmptyFindArgumentMessage();
        } catch (InvalidDeadlineFormatException e) {
            printInvalidDeadlineDateFormat();
        } catch (NumberFormatException e) {
            printNumberFormatErrorMessage();
        }
    }

    /**
     * Checks if the user input starts with the "find" command.
     *
     * @param userInput The user input to check.
     * @return True if the input starts with "find", false otherwise.
     */
    private static boolean hasSaidFind(String userInput) {
        return userInput.startsWith("find");
    }

    /**
     * Checks if the user input is "list".
     *
     * @param userInput The user input to check.
     * @return True if the input is "list", false otherwise.
     */
    public static boolean hasSaidList(String userInput) {
        return userInput.equals("list");
    }

    /**
     * Checks if the user input is "bye".
     *
     * @param userInput The user input to check.
     * @return True if the text is "bye", false otherwise.
     */
    public static boolean hasSaidBye(String userInput) {
        return userInput.equals(BYE_STATEMENT);
    }

    /**
     * Checks if the user input starts with "mark" or "unmark".
     *
     * @param userInput The user input to check.
     * @return True if the input starts with "mark" or "unmark", false otherwise.
     */
    public static boolean hasSaidMarkOrUnmark(String userInput) {
        return (userInput.startsWith("mark ")  || userInput.startsWith("unmark "));
    }

    /**
     * Checks if the user input starts with "delete".
     *
     * @param userInput The user input to check.
     * @return True if the input starts with "delete", false otherwise.
     */
    public static boolean hasSaidDelete(String userInput) {
        return userInput.startsWith("delete");
    }

    /**
     * Prints the list of tasks that match the given search criteria.
     *
     * @param taskToFind The search criteria entered by the user.
     * @param listTasks The list of tasks to search within.
     */
    public static void printMatchingListOfTasks(String taskToFind, ArrayList<Task> listTasks) {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");
        if (listTasks.isEmpty()) {
            System.out.println("  [What do you want to find from an empty list. Haiyaa.]");
        }
        int matchingIndex = 0;
        for (Task listTask : listTasks) {
            if (listTask.toString().contains(taskToFind)) {
                System.out.println((matchingIndex + 1) + ". " + listTask);
                matchingIndex += 1;
            }
        }
        if (matchingIndex == 0 && !listTasks.isEmpty()) {
            System.out.println("  [No entries found]");
        }
        System.out.println(LINE);
    }

    /**
     * Prints the current list of tasks.
     *
     * @param listTasks The list of tasks to print.
     */
    public static void printListOfTasks(ArrayList<Task> listTasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        if (listTasks.isEmpty()) {
            System.out.println("  [Whoops your list is empty]");
        }
        for (int i = 0; i < listTasks.size(); i++) {
            if (listTasks.get(i) != null) {
                System.out.println((i + 1) + ". " + listTasks.get(i));
            }
        }
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     *
     * @param listTasks The list of tasks.
     * @param indexToUnmark The index of the task being marked.
     */
    public static void printTaskIsUnmarkedMessage(ArrayList<Task> listTasks, int indexToUnmark) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + listTasks.get(indexToUnmark - 1));
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param listTasks The list of tasks.
     * @param indexToMark The index of the task being marked.
     */
    public static void printTaskIsMarkedMessage(ArrayList<Task> listTasks, int indexToMark) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + listTasks.get(indexToMark - 1));
        System.out.println(LINE);
    }

    /**
     * Prints the number of tasks in the list.
     *
     * @param insertIndex The number of tasks.
     */
    public static void printNumberOfTasks(int insertIndex) {
        System.out.println("Now you have " + insertIndex + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param listTasks The list of tasks.
     * @param indexToDelete The index of the task being deleted.
     */
    public static void printTaskIsDeletedMessage(ArrayList<Task> listTasks, int indexToDelete) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + listTasks.get(indexToDelete - 1));
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param listTasks The list of tasks.
     * @param insertIndex The index of the added task.
     */
    public static void printTaskIsAddedMessage(ArrayList<Task> listTasks, int insertIndex) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + listTasks.get(insertIndex));
    }

    /** Prints an error message for invalid number format. */
    private static void printNumberFormatErrorMessage() {
        System.out.println(LINE);
        System.out.println("Please type in a valid number after your command.");
        System.out.println(LINE);
    }
    /** Prints an error message for find command with no description. */
    private static void printEmptyFindArgumentMessage() {
        System.out.println(LINE);
        System.out.println("Incomplete find argument (find [task name]).");
        System.out.println(LINE);
    }

    /** Prints an error message for deadline command with wrong date format. */
    private static void printInvalidDeadlineDateFormat() {
        System.out.println(LINE);
        System.out.println("Deadline format is invalid (yyyy-mm-dd).");
        System.out.println(LINE);
    }

    /** Prints a message prompting the user to add an item to the list. */
    public static void printAddItemMessage() {
        System.out.println("What would you like to be added to the list?");
        System.out.println(LINE);
    }

    /** Prints a welcome message and Gandalf icon when the application starts. */
    public static void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Gandalf, your favorite personal assistant.");
        System.out.println("Please wait while I load your previous To-Do List.");
        System.out.println("                               ");
        System.out.println("                           /\\");
        System.out.println("                          /  \\");
        System.out.println("                         |    |");
        System.out.println("                       --:'''':--");
        System.out.println("                         :'_' :");
        System.out.println("                         _:\"\":\\___");
        System.out.println("          ' '      ____.' :::     '._");
        System.out.println("         . *=====<<=)           \\    :");
        System.out.println("          .  '      '-'-'\\_      /'._.'");
        System.out.println("                           \\====:_ \"\"");
        System.out.println("                          .'     \\\\");
        System.out.println("                         :       :");
        System.out.println("                        /   :    \\");
        System.out.println("                       :   .      '.");
        System.out.println("                       :  : :      :");
        System.out.println("                       :__:-:__.;--'");
        System.out.println("                       '-'   '-'");
    }

    /** Prints a welcome message and Gandalf icon when the application starts. */
    public static void printEndMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        in.close();
    }

    /** Prints an error message for incomplete command. */
    public static void printIncompleteCommandMessage() {
        System.out.println(LINE);
        System.out.println("Your command is incomplete.");
        System.out.println(LINE);
    }

    /** Prints a message indicating that the previous To-Do list is being loaded. */
    public static void printLoadingMessage () {
        System.out.println(LINE);
        System.out.println("Loading previous To-Do List....");
    }

    /** Prints a message indicating that the save-file has not been made. */
    public static void printFileNotFoundMessage() {
        System.out.println(LINE);
        System.out.println("File not found. You may start creating your new list.");
        System.out.println(LINE);
    }

    /** Prints a message indicating that the save-file is empty. */
    public static void printEmptyFileMessage() {
        System.out.println(LINE);
        System.out.println("Your safe file is empty. You may start creating your new list.");
        System.out.println(LINE);
    }

    /** Prints a message indicating that the list is full. */
    public static void printListIsFullMessage() {
        System.out.println(LINE);
        System.out.println("List is full. Cannot add more items.");
        System.out.println(LINE);
    }

    /** Prints an error message for invalid command. */
    public static void printInvalidKeywordMessage() {
        System.out.println(LINE);
        System.out.println("Invalid keyword, the available keywords are:"
                + "\n1. todo -> (todo [description])"
                + "\n2. deadline -> (deadline [description] /by [due])"
                + "\n3. event -> (event [description] /from [start time] /to [end time])"
                + "\n4. list -> Shows all items on the To-Do list"
                + "\nPlease try again.");
        System.out.println(LINE);
    }

    /** Prints an error message for missing description. */
    public static void printMissingDescriptionMessage() {
        System.out.println(LINE);
        System.out.println("Please fill in the description of the task.");
        System.out.println(LINE);
    }

    /** Prints an error message for invalid task index. */
    public static void printInvalidTaskIndexMessage() {
        System.out.println(LINE);
        System.out.println("Invalid task index. Please try again.");
        System.out.println(LINE);
    }

    /** Prints an error message for invalid task deletion. */
    public static void printInvalidTaskDeletionMessage() {
        System.out.println(LINE);
        System.out.println("Deletion unsuccessful, please try again.");
        System.out.println(LINE);
    }

    /** Prints an error message indicating that directory creation failed. */
    public static void printFailedDirectoryCreationMessage() {
        System.out.println("Failed to create data directory.");
    }

    /** Prints confirmation message for successful directory creation. */
    public static void printSuccessfulDirectoryCreationMessage() {
        System.out.println("Data directory created successfully.");
    }

    /** Prints a confirmation message for successful write to save-file.txt. */
    public static void printSuccessfulSaveMessage() {
        System.out.println("Content has been saved to the file successfully.");
    }

    /** Prints an error message indicating corrupted write to save-file.txt. */
    public static void printCorruptedWriteMessage(IOException e) {
        System.out.println("An error occurred while writing to the file: " + e.getMessage());
    }
}

package gandalf;

import action.Task;
import exception.EmptyFindException;
import exception.IncompleteCommandException;
import exception.InvalidKeywordException;
import exception.InvalidTaskDeletionException;
import exception.InvalidTaskIndexException;
import exception.MissingDescriptionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    static Scanner in = new Scanner(System.in);

    static ArrayList<Task> listTasks = new ArrayList<>();

    public static final String LINE = "____________________________________________________________";
    public static final String BYE_STATEMENT = "bye";

    public static void startProgram() {

        Storage.loadData(listTasks);
        printAddItemMessage();

        while (true) {
            String userInput = getUserInput();
            if (hasSaidBye(userInput)) {
                System.out.println(LINE);
                Storage.saveTasks(listTasks);
                return;
            } else {
                handleUserInput(userInput, listTasks, false);
            }
        }
    }

    public static void handleUserInput(String userInput, ArrayList<Task> listTasks, boolean hideInput)  {
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
        }
    }

    private static void printEmptyFindArgumentMessage() {
        System.out.println(LINE);
        System.out.println("Incomplete find argument (find [task name]).");
        System.out.println(LINE);
    }

    private static boolean hasSaidFind(String userInput) {
        return userInput.startsWith("find");
    }

    public static void printAddItemMessage() {
        System.out.println("What would you like to be added to the list?");
        System.out.println(LINE);
    }

    public static boolean hasSaidList(String userInput) {
        return userInput.equals("list");
    }

    public static boolean hasSaidBye(String text) {
        return text.equals(BYE_STATEMENT);
    }

    public static String getUserInput() {
        return in.nextLine();
    }

    public static void welcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Gandalf, your favorite personal assistant.");
        System.out.println("Please wait while I load your previous To-Do List.");
    }

    public static void endMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        in.close();
    }

    private static void printIncompleteCommandMessage() {
        System.out.println(LINE);
        System.out.println("Your command is incomplete.");
        System.out.println(LINE);
    }

    public static void printLoadingMessage () {
        System.out.println(LINE);
        System.out.println("Loading previous To-Do List....");
    }

    public static void printFileNotFoundMessage() {
        System.out.println(LINE);
        System.out.println("File not found. You may start creating your new list.");
        System.out.println(LINE);
    }

    public static void printEmptyFileMessage() {
        System.out.println(LINE);
        System.out.println("Your safe file is empty. You may start creating your new list.");
        System.out.println(LINE);
    }

    public static void printListIsFullMessage() {
        System.out.println(LINE);
        System.out.println("List is full. Cannot add more items.");
        System.out.println(LINE);
    }

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

    public static void printMissingDescriptionMessage() {
        System.out.println(LINE);
        System.out.println("Please fill in the description of the task.");
        System.out.println(LINE);
    }

    public static void printInvalidTaskIndexMessage() {
        System.out.println(LINE);
        System.out.println("Invalid task index. Please try again.");
        System.out.println(LINE);
    }

    public static void printInvalidTaskDeletionMessage() {
        System.out.println(LINE);
        System.out.println("Deletion unsuccessful, please try again.");
        System.out.println(LINE);
    }

    private static boolean hasSaidMarkOrUnmark(String userInput) {
        return (userInput.startsWith("mark ")  || userInput.startsWith("unmark "));
    }

    private static boolean hasSaidDelete(String userInput) {
        return userInput.startsWith("delete");
    }

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

    public static void printTaskIsUnmarkedMessage(ArrayList<Task> listTasks, int indexToUnmark) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + listTasks.get(indexToUnmark - 1));
        System.out.println(LINE);
    }

    public static void printTaskIsMarkedMessage(ArrayList<Task> listTasks, int indexToMark) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + listTasks.get(indexToMark - 1));
        System.out.println(LINE);
    }

    public static void printNumberOfTasks(int insertIndex) {
        System.out.println("Now you have " + insertIndex + " tasks in the list.");
        System.out.println(LINE);
    }

    public static void printTaskIsDeletedMessage(ArrayList<Task> listTasks, int indexToDelete) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + listTasks.get(indexToDelete - 1));
    }

    public static void printTaskIsAddedMessage(ArrayList<Task> listTasks, int insertIndex) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + listTasks.get(insertIndex));
    }

    public static void printFailedDirectoryCreationMessage() {
        System.out.println("Failed to create data directory.");
    }

    public static void printSuccessfulDirectoryCreationMessage() {
        System.out.println("Data directory created successfully.");
    }

    public static void printSuccessfulSaveMessage() {
        System.out.println("Content has been saved to the file successfully.");
    }

    public static void printCorruptedWriteMessage(IOException e) {
        System.out.println("An error occurred while writing to the file: " + e.getMessage());
    }

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
        if (matchingIndex == 0) {
            System.out.println("  [No entries found]");
        }
        System.out.println(LINE);
    }
}

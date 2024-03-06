package Parser;

import Parser.DukeExceptions;
import TaskList.Task;
import Ui.PrintTask;
import Ui.PrintText;

import java.util.ArrayList;

/**
 * Represents a command to mark a task stored in the list.
 * Print the task for user to see current task status.
 */
public class MarkTask {
    /**
     * Return the index of the task needed to be marked as written in the file.
     *
     * @param userInputWords User input as an array of words.
     * @param tasks Arraylist of tasks stored.
     * @throws DukeExceptions.InvalidItemException If the index to mark is not
     * a valid index/cannot be converted to an integer.
     * @throws ArrayIndexOutOfBoundsException If the index input is out of the
     * Arraylist's bound.
     */
    public static int findIndexToMark(String[] userInputWords, ArrayList<Task> tasks) throws
            DukeExceptions.InvalidItemException, ArrayIndexOutOfBoundsException {
        try {
            int testIndex = Integer.parseInt(userInputWords[1]);
        } catch(Exception e) {
            DukeExceptions.throwInvalidItemException();
        }
        int indexFound = Integer.parseInt(userInputWords[1]);
        if (indexFound > tasks.size() || indexFound < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return indexFound;
    }

    /**
     * Mark a task listed in file based on user input given.
     *
     * @param userInputWords User input as an array of words.
     * @param tasks Arraylist of tasks stored.
     */
    public static void mark(String[] userInputWords, ArrayList<Task> tasks) {
        int indexToMark = -1;
        String isMarked = userInputWords[0];
        try {
            indexToMark = findIndexToMark(userInputWords, tasks);
        } catch(ArrayIndexOutOfBoundsException e) {
            PrintText.printWithLinebreak("Index out of range");
            return;
        } catch(DukeExceptions.InvalidItemException e) {
            PrintText.printWithLinebreak("Please enter a valid item to mark/unmark.");
            return;
        }

        int indexInList = indexToMark - 1;
        if (isMarked.equals("mark")) {
            tasks.get(indexInList).setIsDone(true);
            String statusMark = "[" + tasks.get(indexInList).getStatusIcon() + "] ";
            PrintText.printWithLinebreak("Nice! I've marked this task as done:\n" +
                    statusMark + tasks.get(indexInList).getDescription());
        } else if (isMarked.equals("unmark")) {
            tasks.get(indexInList).setIsDone(false);
            String statusMark = "[" + tasks.get(indexInList).getStatusIcon() + "] ";
            PrintText.printWithLinebreak("OK, I've marked this task as not done yet:\n" +
                    statusMark + tasks.get(indexInList).getDescription());
        } else {
            PrintText.printWithLinebreak("unknown instruction");
        }
        PrintTask.printMultipleToFile(tasks, false);
    }
}

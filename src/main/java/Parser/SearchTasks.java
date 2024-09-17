package Parser;

import TaskList.Task;
import Ui.PrintText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a command to search for tasks that have description matched with user's input.
 */
public class SearchTasks {
    /**
     * Returns a list of tasks that have description matched with the input line of string.
     *
     * @param toFind The input string to compare with.
     * @param allTasks All tasks stored in the list.
     */
    public static ArrayList<Task> matchedTasks(String toFind, ArrayList<Task> allTasks) {
        ArrayList<Task> outputList = new ArrayList<>();
        ArrayList<String> toFindWords = new ArrayList<>(Arrays.asList(toFind.split(" ")));
        for (Task task : allTasks) {
            ArrayList<String> descriptionWords = new ArrayList<>(Arrays.asList(task.getDescription().split(" ")));
            boolean isOneWordMatched = false;
            for (String word : toFindWords) {
                if (descriptionWords.contains(word)) {
                    isOneWordMatched = true;
                    break;
                }
            }
            if (isOneWordMatched && task.getDescription().contains(toFind)) {
                outputList.add(task);
            }
        }
        return outputList;
    }
}

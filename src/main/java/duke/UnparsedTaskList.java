package duke;

import java.util.ArrayList;

/**
 * Class contains a static list that holds unparsed task commands, to be saved locally to the users device
 */
public class UnparsedTaskList {
    private static ArrayList<String> unparsedTaskList = new ArrayList<>(2);

    /**
     * Returns the unparsed task at the chosen index
     *
     * @param i The chosen index
     * @return The chosen unparsed task
     */
    public String get(int i) {
        return unparsedTaskList.get(i);
    }

    /**
     * Adds the unparsed task into the list
     *
     * @param input unparsed task that is to be added to the list
     */
    public void add(String input) {
        unparsedTaskList.add(input);
    }

    /**
     * Removes chosen unparsed task from list
     *
     * @param input Unparsed task to be removed
     */
    public void remove(String input) {
        unparsedTaskList.remove(input);
    }

    /**
     * Returns the current size of the unparsed task list
     *
     * @return Length of list
     */
    public int size() {
        return unparsedTaskList.size();
    }
}

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents management and display of the task list.
 */
public class List {

    /**
     * Adds a new task to the task list.
     *
     * @param tasks The list of tasks to be modified.
     * @param task The new task to be added.
     */
    public static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @param tasks The list of tasks to be counted.
     * @return The total number of tasks in the list.
     */
    public static int getTotal (ArrayList<Task> tasks) {
        return tasks.size();
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param tasks The list of tasks to be modified.
     * @param index The index of the task to be removed.
     * @throws IndexOutOfBoundsException If the provided index is out of bounds.
     */
    public static void removeTask(ArrayList<Task> tasks, int index) {
        tasks.remove(index);
    }

    /**
     * Prints the details of all tasks in the list, or displays a message if the list is empty.
     *
     * @param tasks The list of tasks to be printed.
     */
    public static void handleTasks(ArrayList<Task> tasks) {
        if (List.getTotal(tasks) == Constant.ARRAY_START_INDEX) {
            Reply.printReply(Constant.EMPTY_LIST);
            return;
        }

        int taskIndex = 1;
        Reply.printReply("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(taskIndex + ". "  + task.toString());
            taskIndex++;
        }

        Reply.printLine();
    }

    /**
     * Searches all tasks in the provided list matching the search query parameter
     * or displays a message if there are no matches.
     *
     * @param tasks The list of tasks to be searched.
     * @param userInput A string representing the user input, typically containing a search query.
     *                  The expected format is "find" followed by a space and the desired search term.
     * @throws CustomException If the user input is empty, indicating a missing search term.
     */
    public static void searchList(ArrayList<Task> tasks, String userInput) {
        String query = userInput.substring(Constant.FIND_OFFSET).trim();
        if (query.isEmpty()) {
            throw new CustomException(Constant.UNSPECIFIED_PARAMETER);
        }

        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getLabel().contains(query))
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            Reply.printReply(Constant.NO_RESULTS);
        } else {
            Reply.printSearch(filteredList);
        }

    }

}

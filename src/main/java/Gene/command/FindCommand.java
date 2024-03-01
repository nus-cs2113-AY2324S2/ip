package Gene.command;

import Gene.GeneException;
import Gene.task.TaskList;
import Gene.task.Todo;

/**
 * Represents a command to find specific tasks with a query keyword.
 */
public class FindCommand {

    /**
     * Execute the find command, finding the tasks with a specified keyword
     *
     * @param command  User input of the Find command.
     * @param taskList User list of tasks.
     * @throws GeneException If there are any formatting issues.
     */
    public static void execute(String command, TaskList taskList) throws GeneException {
        String[] parts = command.split(" ");
        if (parts.length <= 1) {
            throw new GeneException("Invalid find format." + System.lineSeparator() + "Use Format: find <keyword>");
        }
        String keyword = command.replaceFirst("\\S+", "").trim();
        taskList.findListItems(keyword);
    }
}

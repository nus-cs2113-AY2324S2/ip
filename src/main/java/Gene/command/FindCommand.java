package Gene.command;

import Gene.GeneException;
import Gene.task.TaskList;
import Gene.task.Todo;

public class FindCommand {
    public static void execute(String command, TaskList taskList) throws GeneException {
        String[] parts = command.split(" ");
        if (parts.length <= 1) {
            throw new GeneException("Invalid find format." + System.lineSeparator() + "Use Format: find <keyword>");
        }
        String keyword = command.replaceFirst("\\S+", "").trim();
        taskList.findListItems(keyword);
    }
}

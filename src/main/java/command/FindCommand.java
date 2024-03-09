package command;

import task.TaskList;
import ui.Message;

/**
 * The FindCommand class represents a command to find tasks.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Creates a FindCommand object.
     *
     * @param keywords The keywords to search for. Index 0 is the keyword.
     */
    public FindCommand(String[] keywords) {
        try {
            this.keyword = keywords[0];
        } catch (IndexOutOfBoundsException e) {
            this.keyword = "";
        }
    }

    /**
     * Executes the command to find tasks.
     *
     * @param tasks The list of tasks.
     * @return False because the program should continue running.s
     */
    @Override
    public boolean execute(TaskList tasks) {
        if (keyword.isEmpty()) {
            System.out.println(Message.INVALID_INPUT_MESSAGE);
            return false;
        }
        String tasksFound = tasks.findTasks(keyword);
        if (tasksFound.isEmpty()) {
            System.out.println(Message.FIND_FAIL_MESSAGE);
        } else {
            System.out.print(Message.FIND_MESSAGE + tasksFound);
        }
        return false;
    }
    
}

package gary.command;

import gary.task.Task;
import gary.task.TaskList;

import java.util.ArrayList;

/**
 * FindCommand class is to handle users command related to finding a keyword in tasks description.
 */
public class FindCommand extends Command {
    public static final int FIND_KEYWORD_START_INDEX = 5;
    private String line;
    private ArrayList<Task> todos;

    /**
     * Constructor for FindCommand class, taking in ArrayList of task and users' input.
     *
     * @param line user input to the terminal.
     * @param todos array list that stores and manages the task while programme is running.
     */
    public FindCommand(String line, ArrayList<Task> todos) {
        this.line = line;
        this.todos = todos;
    }

    /**
     * Handles users' command to find tasks that contain a given keyword. If keyword is not given
     * error is handled.
     */
    @Override
    public void handleCommand() {
        String keyword;
        try {
            keyword = line.substring(FIND_KEYWORD_START_INDEX);
            if (keyword.isBlank()) {
                throw new StringIndexOutOfBoundsException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Find keyword cannot be empty");
            return;
        }
        TaskList.processFind(keyword, todos);
    }
}

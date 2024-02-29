package gary.command;

import gary.task.Task;
import gary.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final int FIND_KEYWORD_START_INDEX = 5;
    private String line;
    private ArrayList<Task> todos;
    public FindCommand(String line, ArrayList<Task> todos) {
        this.line = line;
        this.todos = todos;
    }

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

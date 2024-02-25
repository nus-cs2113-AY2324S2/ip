package quill.command;

import quill.task.TaskList;
import quill.ui.TextUi;

/**
 * The ListCommand Class represents a command for listing task form the task list.
 * It extends the Command class and includes specific behavior for listing tasks.
 */
public class ListCommand extends Command{

    /**
     * Constructs the ListCommand object with the specified commandWord and parameter.
     *
     * @param commandWord The command word.
     * @param parameter The user input excluding the command word.
     */
    public ListCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    /**
     * Executes the ListCommand to list tasks.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for displaying messages.
     */
    public void execute (TaskList tasks, TextUi ui) {
        TextUi.showList(tasks);
    }
}

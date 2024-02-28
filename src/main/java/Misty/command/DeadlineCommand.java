package misty.command;

import java.util.regex.Pattern;
import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;
import misty.data.exception.EmptyParameterException;

/**
 * Adds deadline to task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_STRING = "deadline";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("(?<taskName>.*)\\s/by\\s(?<by>.*)");
    private String taskName;
    private String by;

    /**
     * Constructs DeadlineCommand object.
     *
     * @param taskName Name of deadline.
     * @param by Due date/time of deadline.
     */
    public DeadlineCommand(String taskName, String by) {
        this.taskName = taskName;
        this.by = by;
    }

    /**
     * Executes deadline command to add deadline to task list.
     *
     * @param taskList ArrayList containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.addDeadline(taskName, by);
        } catch (EmptyParameterException e) {
            userUi.printErrorEmptyParameter();
        }
    };
}
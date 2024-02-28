package misty.command;

import java.util.regex.Pattern;
import misty.data.TaskList;
import misty.data.exception.EmptyParameterException;
import misty.storage.Storage;
import misty.ui.UserUi;

/**
 * Adds an event to task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_STRING = "event";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("(?<taskName>.*)\\s/from\\s(?<from>.*)\\s/to\\s(?<to>.*)");
    private String taskName;
    private String from;
    private String to;

    /**
     * Constructs EventCommand object.
     *
     * @param taskName Name of event.
     * @param from Start date/time of event.
     * @param to End date/time of event.
     */
    public EventCommand(String taskName, String from, String to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes event command to add an event to task list.
     *
     * @param taskList ArrayList containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.addEvent(taskName, from, to);
        } catch (EmptyParameterException e) {
            userUi.printErrorEmptyParameter();
        }
    };
}
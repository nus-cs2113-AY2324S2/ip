package misty.command;

import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;
import misty.data.exception.EmptyParameterException;
import java.util.regex.Pattern;

public class EventCommand extends Command {
    public static final String COMMAND_STRING = "event";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("(?<taskName>.*)\\s/from\\s(?<from>.*)\\s/to\\s(?<to>.*)");
    protected String taskName;
    protected String from;
    protected String to;

    public EventCommand(String taskName, String from, String to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.addEvent(taskName, from, to);
        } catch (EmptyParameterException e) {
            userUi.printErrorEmptyParameter();
        }
    };
}

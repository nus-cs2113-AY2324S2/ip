package misty.command;

import java.util.regex.Pattern;
import misty.data.TaskList;
import misty.data.exception.EmptyParameterException;
import misty.storage.Storage;
import misty.ui.UserUi;

public class EventCommand extends Command {
    public static final String COMMAND_STRING = "event";
    public static final Pattern COMMAND_FORMAT =
            Pattern.compile("(?<taskName>.*)\\s/from\\s(?<from>.*)\\s/to\\s(?<to>.*)");
    private String taskName;
    private String from;
    private String to;

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
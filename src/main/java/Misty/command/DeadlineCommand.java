package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;
import misty.exception.EmptyParameterException;

public class DeadlineCommand extends Command {
    public static final String COMMAND_STRING = "deadline";
    protected String taskName;
    protected String by;

    public DeadlineCommand(String taskName, String by) {
        this.taskName = taskName;
        this.by = by;
    }

    @Override
    public void execute(List taskList, Storage storage, UserUi userUi) {
        try {
            taskList.addDeadline(taskName, by);
        } catch (EmptyParameterException e) {
            userUi.printErrorEmptyParameter();
        }
    };
}

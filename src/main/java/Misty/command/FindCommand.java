package misty.command;

import misty.data.TaskList;
import misty.data.exception.EmptyParameterException;
import misty.storage.Storage;
import misty.ui.UserUi;

public class FindCommand extends Command {
    public static final String COMMAND_STRING = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.find(keyword);
        } catch (EmptyParameterException e) {
            userUi.printErrorEmptyParameter();
        }
    }
}
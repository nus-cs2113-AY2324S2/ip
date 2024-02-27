package misty.command;

import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CheckCommand extends Command {
    public static final String COMMAND_STRING = "check";
    protected String check;
    protected LocalDate dateCheck;

    public CheckCommand(String check) {
        this.check = check;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            dateCheck = LocalDate.parse(check);
            taskList.check(dateCheck);
        } catch (DateTimeParseException e) {
            userUi.printUsageCheck();
            userUi.printErrorInvalidDateFormat();
        }


    };
}

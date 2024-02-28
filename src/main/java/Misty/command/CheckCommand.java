package misty.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;

public class CheckCommand extends Command {
    public static final String COMMAND_STRING = "check";
    private String check;
    private LocalDate dateCheck;

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
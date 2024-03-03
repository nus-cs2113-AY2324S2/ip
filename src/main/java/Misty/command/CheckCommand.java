package misty.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;

/**
 * Finds tasks occuring on specific date.
 */
public class CheckCommand extends Command {
    public static final String COMMAND_STRING = "check";
    private String check;
    private LocalDate dateCheck;

    /**
     * Constructs CheckCommand object.
     *
     * @param check Keyword to be searched in yyyy-mm-dd format.
     */
    public CheckCommand(String check) {
        this.check = check;
    }

    /**
     * Executes check command to find tasks occurring on specific date.
     *
     * @param taskList TaskList object containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
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
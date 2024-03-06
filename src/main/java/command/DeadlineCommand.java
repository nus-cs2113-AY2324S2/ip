package command;

import errorhandle.UserInputErrorOutputHandler;
import format.Formatter;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static constant.NormalConstant.*;

/**
 * Represent a deadlineCommand
 */
public class DeadlineCommand extends Command {
    Formatter formatter;
    public final String identity = "deadline";
    Storage storage;
    protected UserInputErrorOutputHandler userInputError;
    protected Ui ui;
    protected String by;
    protected LocalDateTime date;
    protected boolean canSearchDate;

    public DeadlineCommand(String userCommandText) {
        formatter = new Formatter();
        storage = new Storage();
        userInputError = new UserInputErrorOutputHandler();
        ui = new Ui();

        int spaceIndex = userCommandText.indexOf(" ");
        prepareDeadlineCommand(userCommandText.substring(spaceIndex + 1));
    }

    /**
     * Execute deadlineCommand, add new deadlineTask to the taskList and save that task on the local disk
     *
     * @param taskList Instance of Class <code>TaskList</code>
     */
    @Override
    public void execute(TaskList taskList) {
        String trimBy = by.trim();
        date = storeIfDateFormatIsAccepted(trimBy);
        if (!canSearchDate) {
            formatter.printDividingLine();
            System.out.println("\tUnsupported date type, date search function will not be available on this task");
        } else {
            trimBy = date.format(DateTimeFormatter.ofPattern(DISPLAY_LOCAL_DATE_FORMAT));
        }

        Task t = new Deadline(content.trim(), trimBy);
        t.setCanSearchDate(canSearchDate);
        taskList.addTask(t);
        ui.printNewTaskAddedMessage(t, taskList);

        try {
            storage.saveTask(taskList.getTask(taskList.getSize() - 1));
        } catch (IOException e) {
            System.out.println("Can not save your Task!!!\n" + e.getMessage());
        }
    }

    /**
     * Make deadlineCommand ready to execute
     *
     * @param unpreparedUserCommand user command that may have input error
     */
    public void prepareDeadlineCommand(String unpreparedUserCommand) {
        if (unpreparedUserCommand.toLowerCase().startsWith(identity)) {
            userInputError.printNoTaskContentError(identity);
            setIfNoError(false);
            return;
        }
        setIfNoError(true);
        int byIndex = unpreparedUserCommand.indexOf("/by");
        content = unpreparedUserCommand.substring(0, byIndex);
        by = unpreparedUserCommand.substring(byIndex + 3).trim();
    }

    public LocalDateTime storeIfDateFormatIsAccepted(String possibleDate) {
        int errorCount = 0;
        LocalDateTime result = null;
        possibleDate = possibleDate.toUpperCase();

        for (String currentFormat : ACCEPTED_LOCAL_DATE_TIME_FORMAT) {
            try {
                result = LocalDateTime.parse(possibleDate, DateTimeFormatter.ofPattern(currentFormat));
            } catch (DateTimeParseException e) {
                errorCount += 1;
            }
        }
        if (errorCount == ACCEPTED_LOCAL_DATE_TIME_FORMAT.length) {
            canSearchDate = false;
            return result;
        }
        canSearchDate = true;
        return result;
    }
}

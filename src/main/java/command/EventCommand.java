package command;

import errorhandle.UserInputErrorOutputHandler;
import format.Formatter;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.DataInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static constant.NormalConstant.*;

public class EventCommand extends Command {
    public static final String identity = "event";
    Formatter formatter;
    Storage storage;
    protected UserInputErrorOutputHandler userInputError;
    protected Ui ui;
    protected String from;
    protected String to;
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;
    protected boolean canSearchDate;

    public EventCommand(String userCommandText) {
        fromDate = null;
        toDate = null;
        formatter = new Formatter();
        storage = new Storage();
        userInputError = new UserInputErrorOutputHandler();
        ui = new Ui();
        int spaceIndex = userCommandText.indexOf(" ");
        prepareEventCommand(userCommandText.substring(spaceIndex + 1));
    }

    @Override
    public void execute(TaskList taskList) {
        String trimFrom = from.trim();
        String trimTo = to.trim();
        fromDate = storeIfDateFormatIsAccepted(trimFrom);
        toDate = storeIfDateFormatIsAccepted(trimTo);
        if (!canSearchDate) {
            formatter.printDividingLine();
            System.out.println("\tUnsupported date type, date search function will not be available on this task");
        } else {
            trimFrom = fromDate.format(DateTimeFormatter.ofPattern(DISPLAY_LOCAL_DATE_FORMAT));
            trimTo = toDate.format(DateTimeFormatter.ofPattern(DISPLAY_LOCAL_DATE_FORMAT));
        }

        Task t = new Event(content.trim(), trimFrom, trimTo);
        taskList.addTask(t);
        ui.printNewTaskAddedMessage(t, taskList);
        try {
            storage.saveTask(taskList.getTask(taskList.getSize() - 1));
        } catch (IOException e) {
            System.out.println("Can not save your Task!!!\n" + e.getMessage());
        }
    }

    public void prepareEventCommand(String unpreparedUserCommand) {
        if (unpreparedUserCommand.toLowerCase().startsWith(identity)) {
            userInputError.printNoTaskContentError(identity);
            setIfNoError(false);
            return;
        }
        setIfNoError(true);
        int fromIndex = unpreparedUserCommand.indexOf("/from");
        content = unpreparedUserCommand.substring(0, fromIndex);
        int toIndex = unpreparedUserCommand.indexOf("/to");
        from = unpreparedUserCommand.substring(fromIndex + 5, toIndex).trim();
        to = unpreparedUserCommand.substring(toIndex + 3).trim();
    }

    public LocalDateTime storeIfDateFormatIsAccepted(String possibleDate) {
        int errorCount = 0;
        LocalDateTime result = null;

        try {
            result = LocalDateTime.parse(possibleDate, DateTimeFormatter.ofPattern(ACCEPTED_LOCAL_DATE_FORMAT_1));
        } catch (DateTimeParseException e) {
            errorCount += 1;
        }
        try {
            result = LocalDateTime.parse(possibleDate, DateTimeFormatter.ofPattern(ACCEPTED_LOCAL_DATE_FORMAT_2));
        } catch (DateTimeParseException e) {
            errorCount += 1;
        }
        try {
            result = LocalDateTime.parse(possibleDate, DateTimeFormatter.ofPattern(ACCEPTED_LOCAL_DATE_FORMAT_3));
        } catch (DateTimeParseException e) {
            errorCount += 1;
        }
        if (errorCount == 3) {
            canSearchDate = false;
            return result;
        }
        canSearchDate = true;
        return result;
    }
}

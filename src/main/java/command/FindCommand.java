package command;

import errorhandle.UserInputErrorOutputHandler;
import task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static constant.NormalConstant.ACCEPTED_LOCAL_DATE_FORMAT;

/**
 * Represent a findCommand
 */
public class FindCommand extends Command {
    LocalDate taskAtTimeToFind;
    String taskContentToFind;
    UserInputErrorOutputHandler userInputError;

    public FindCommand(String userCommandText) {
        taskContentToFind = null;
        taskAtTimeToFind = null;

        userInputError = new UserInputErrorOutputHandler();

        int spaceIndex = userCommandText.indexOf(" ");
        if (spaceIndex == -1) {
            userInputError.printNoSpacingErrorForFind();
            setIfNoError(false);
        } else {
            prepareFindCommand(userCommandText.substring(spaceIndex + 1));
        }
    }

    /**
     * Execute findCommand, find the specified task in the taskList and show that list to the user
     *
     * @param taskList Instance of Class <code>TaskList</code>
     */
    @Override
    public void execute(TaskList taskList) {
        if (taskContentToFind != null) {
            taskList.printTaskListWithTaskContentCondition(taskContentToFind);
        } else if (taskAtTimeToFind != null){
            taskList.printTaskListWithTimeCondition(taskAtTimeToFind);
        }
    }

    /**
     * Make findCommand ready to execute
     *
     * @param unpreparedUserCommand user command that may have input error
     */
    public void prepareFindCommand(String unpreparedUserCommand) {
        unpreparedUserCommand = unpreparedUserCommand.trim();
        int atIndex = unpreparedUserCommand.indexOf("/at");

        if (atIndex == -1) {
            taskContentToFind = unpreparedUserCommand;
        } else {
            prepareFindAtCommand(unpreparedUserCommand.substring(atIndex + 3).trim());
        }
        setIfNoError(true);
    }

    public void prepareFindAtCommand(String unpreparedUserCommand) {
        int errorCount = 0;
        unpreparedUserCommand = unpreparedUserCommand.toUpperCase();

        for (String currentFormat : ACCEPTED_LOCAL_DATE_FORMAT) {
            try {
                taskAtTimeToFind =
                        LocalDate.parse(unpreparedUserCommand, DateTimeFormatter.ofPattern(currentFormat));
            } catch (DateTimeParseException e) {
                errorCount += 1;
            }
        }

        if(errorCount == ACCEPTED_LOCAL_DATE_FORMAT.length){
            setIfNoError(false);
            userInputError.printWrongDateFormatError();
            return;
        }
        setIfNoError(true);
    }
}

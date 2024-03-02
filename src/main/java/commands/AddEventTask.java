package commands;
import Exceptions.ThawException;
import FileManagerPackage.Storage;
import Tasks.*;
import PrintMessages.UI;
import UserInputs.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The AddEventTask class represents a command to add an event task to the task list.
 * It extends the Command class and requires the list of tasks and the user's input.
 */
public class AddEventTask extends Command  {

    private final int LEN_OF_SLASH_FROM = 6;

    private final int LEN_OF_DATE = 5;

    private final int LEN_OF_SLASH_TO = 4;

    private final int LEN_OF_DEADLINE = 6;

    /**
     * The name of the command class (used for error messages).
     */
    public final String CLASS_NAME = "event";

    /**
     * Constructs a new {@code AddEventTask} command with the specified list of tasks and user's input.
     *
     * @param taskList   The list of tasks to operate on.
     * @param usersInput The user's input for the command.
     * @throws ThawException Thrown if the user's input is empty or lacks the required "/from" and "/to" keywords.
     */
    public AddEventTask (ArrayList<Task> taskList, String usersInput) throws ThawException {
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + CLASS_NAME);
        } else if (!commandWithoutDescription(usersInput)  && correctDateTimeFormat(usersInput)) {
            int startIndex = usersInput.indexOf("/from");
            int endIndex = usersInput.indexOf("/to");

            LocalDate fromDate = Parser.processDate(usersInput.substring(startIndex + LEN_OF_SLASH_FROM,
                    endIndex - LEN_OF_DATE).strip());
            LocalTime fromTime = Parser.processTime(usersInput.substring(endIndex - LEN_OF_DATE, endIndex));
            LocalDate toDate = Parser.processDate(usersInput.substring(endIndex + LEN_OF_SLASH_TO,
                    usersInput.length() - LEN_OF_DATE).strip());
            LocalTime toTime = Parser.processTime(usersInput.substring(usersInput.length() - LEN_OF_SLASH_TO));

            taskList.add(new Event(usersInput.substring(LEN_OF_DEADLINE, startIndex - 1),
                    fromDate, fromTime, toDate, toTime));

            ui.printAcknowledgementMessage(taskList);
            Storage.saveData(taskList);

        }
    }
}

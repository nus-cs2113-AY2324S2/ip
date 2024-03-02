package commands;

import Exceptions.*;
import FileManagerPackage.Storage;
import Tasks.Deadline;
import UserInputs.Parser;
import Tasks.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The AddDeadlineTask class represents a command to add a deadline task to the task list.
 * It extends the Command class and requires the list of tasks and the user's input.
 */
public class AddDeadlineTask extends Command {

    private final int LEN_OF_SLASH_BY = 4;
    private final int LEN_OF_TIME = 4;

    private final int LEN_OF_DEADLINE = 9;

    /**
     * The name of the command class (used for error messages).
     */
    public final String CLASS_NAME = "deadline";

    /**
     * Constructs a new {@code AddDeadlineTask} command with the specified list of tasks and user's input.
     *
     * @param taskList   The list of tasks to operate on.
     * @param usersInput The user's input for the command.
     * @throws ThawException Thrown if the user's input is empty or lacks the required "/by" keyword.
     */
    public AddDeadlineTask(ArrayList<Task> taskList, String usersInput) throws ThawException {
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + CLASS_NAME);
        } else if (!commandWithoutDescription(usersInput)) {
            int startingIndex = usersInput.indexOf("/by");
            LocalDate date = Parser.processDate(usersInput.substring(startingIndex + LEN_OF_SLASH_BY,
                    usersInput.length() - LEN_OF_TIME));

            LocalTime time = Parser.processTime(usersInput.substring(usersInput.length() - LEN_OF_TIME));
            taskList.add(new Deadline(usersInput.substring(LEN_OF_DEADLINE, startingIndex - 1), date, time));
            ui.printAcknowledgementMessage(taskList);
            Storage.saveData(taskList);
        }
    }
}

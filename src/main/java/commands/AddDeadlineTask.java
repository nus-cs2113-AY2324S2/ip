package commands;

import Exceptions.*;
import FileManagerPackage.Storage;
import Tasks.Deadline;
import UserInputs.Parser;
import Tasks.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AddDeadlineTask extends Command {
    /**
     * The name of the command class (used for error messages).
     */
    public final String className = "deadline";
    public AddDeadlineTask(ArrayList<Task> taskList, String usersInput) throws ThawException {
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + className);
        } else if (!commandWithoutDescription(usersInput)) {
            int startingIndex = usersInput.indexOf("/by");
            LocalDate date = Parser.processDate(usersInput.substring(startingIndex + 4, usersInput.length() - 4));
            LocalTime time = Parser.processTime(usersInput.substring(usersInput.length() - 4));
            taskList.add(new Deadline(usersInput.substring(9, startingIndex - 1), date, time));
            ui.printAcknowledgementMessage(taskList);
            Storage.saveData(taskList);
        }
    }
}

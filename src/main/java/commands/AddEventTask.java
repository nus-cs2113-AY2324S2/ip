package commands;
import Exceptions.ThawException;
import FileManagerPackage.Storage;
import Tasks.*;
import PrintMessages.UI;
import UserInputs.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AddEventTask extends Command  {
    public final String className = "event";
    public AddEventTask (ArrayList<Task> taskList, String usersInput) throws ThawException {
        if (commandWithoutDescription(usersInput)) {
            throw new ThawException("Empty command " + className);
        } else if (!commandWithoutDescription(usersInput)  && correctDateTimeFormat(usersInput)) {
            int startIndex = usersInput.indexOf("/from");
            int endIndex = usersInput.indexOf("/to");
            LocalDate fromDate = Parser.processDate(usersInput.substring(startIndex + 6, endIndex - 6));
            LocalTime fromTime = Parser.processTime(usersInput.substring(endIndex - 5, endIndex));
            LocalDate toDate = Parser.processDate(usersInput.substring(endIndex + 4, usersInput.length() - 5));
            LocalTime toTime = Parser.processTime(usersInput.substring(usersInput.length() - 4));
            taskList.add(new Event(usersInput.substring(6, startIndex - 1),fromDate, fromTime, toDate, toTime));
            ui.printAcknowledgementMessage(taskList);
            Storage.saveData(taskList);
        }
    }
}

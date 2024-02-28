package commands;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Exceptions.ThawException;
import Tasks.*;
import UserInputs.Parser;

public class AddTask extends Command {
    public static void addTask(String usersInput, ArrayList<Task> list) throws ThawException {
        if (!commandWithoutDescription(usersInput)) {
            if (usersInput.startsWith("todo")) {
                list.add(new Todo(usersInput.substring(5), usersInput));
            } else if (usersInput.startsWith("deadline") && correctDateTimeFormat(usersInput)) {
                int startingIndex = usersInput.indexOf("/by");
                LocalDate date = Parser.processDate(usersInput.substring(startingIndex + 4, usersInput.length() - 4));
                LocalTime time = Parser.processTime(usersInput.substring(usersInput.length() - 4));
                list.add(new Deadline(usersInput.substring(9, startingIndex - 1), date, time));
            } else if (usersInput.startsWith("event") && correctDateTimeFormat(usersInput)) {
                int startIndex = usersInput.indexOf("/from");
                int endIndex = usersInput.indexOf("/to");
                LocalDate fromDate = Parser.processDate(usersInput.substring(startIndex + 6, endIndex - 6));
                LocalTime fromTime = Parser.processTime(usersInput.substring(endIndex - 5, endIndex));
                LocalDate toDate = Parser.processDate(usersInput.substring(endIndex + 4, usersInput.length() - 5));
                LocalTime toTime = Parser.processTime(usersInput.substring(usersInput.length() - 4));
                list.add(new Event(usersInput.substring(6, startIndex - 1),fromDate, fromTime, toDate, toTime));
            } else if (!correctDateTimeFormat(usersInput) && (usersInput.startsWith("deadline") || usersInput.startsWith("event"))) {
                throw new ThawException("Invalid date time format");
            }
        } else if (commandWithoutDescription(usersInput)){
            throw new ThawException("Empty command " + usersInput);
        }
    }
}

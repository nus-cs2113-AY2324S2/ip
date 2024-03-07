package helpy.task;

import helpy.exceptions.EventDateSequenceException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date & time.
 */
public class Event extends Task{
    protected String fromStr;
    protected String toStr;
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task object from the given command string.
     *
     * @param command The command string used to create the Event.
     * @throws EventDateSequenceException If the start time is not before the end time.
     */
    public Event(String command) throws EventDateSequenceException{
        super();
        String[] details = command.split("/from");
        taskName = details[0].trim();

        String[] eventPeriod = details[1].split("/to");
        fromStr = eventPeriod[0].trim();
        toStr = eventPeriod[1].trim();
        from = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        to = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        if (!from.isBefore(to)) {
            throw new EventDateSequenceException();
        }
    }

    /**
     * Returns a string representation of the Event.
     *
     * @return A string representation of the Event, including its status icon, name, start time, and end time.
     */
    @Override
    public String toString() {
        String fromDate = from.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
        String toDate = to.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
        return "[E]" + super.toString()
                + " (from: " + fromDate + " to: " + toDate + ")";
    }

    /**
     * Saves the Event to the specified file.
     *
     * @param filePath The path of the file to save the Event to.
     * @throws IOException If an error occurs while writing to the file.
     */
    @Override
    public void saveToFile(String filePath) throws IOException {
        FileWriter helpyWriter = new FileWriter(filePath, true);
        String isDone = isDone() ? "1" : "0";
        helpyWriter.write("E | " + isDone + " | " + taskName
                + " /from " + fromStr + " /to " + toStr + "\n");
        helpyWriter.close();
    }
}

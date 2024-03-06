package helpy.task;

import helpy.exceptions.EventDateSequenceException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String fromStr;
    protected String toStr;
    protected LocalDateTime from;
    protected LocalDateTime to;

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

    @Override
    public String toString() {
        String fromDate = from.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
        String toDate = to.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
        return "[E]" + super.toString()
                + " (from: " + fromDate + " to: " + toDate + ")";
    }

    @Override
    public void saveToFile(String filePath) throws IOException {
        FileWriter helpyWriter = new FileWriter(filePath, true);
        String isDone = isDone() ? "1" : "0";
        helpyWriter.write("E | " + isDone + " | " + taskName
                + " /from " + fromStr + " /to " + toStr + "\n");
        helpyWriter.close();
    }
}

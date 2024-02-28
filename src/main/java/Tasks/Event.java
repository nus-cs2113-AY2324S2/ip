package Tasks;

import Tasks.Task;
import UserInputs.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {
    protected LocalTime fromTime;
    protected LocalDate fromDate;
    protected LocalTime toTime;
    protected LocalDate toDate;

    protected String DateTimeString;

    public Event(String description, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        super(description);
        this.fromTime = fromTime;
        this.fromDate = fromDate;
        this.toTime = toTime;
        this.toDate = toDate;
        this.DateTimeString = "from: " + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                fromTime.format(DateTimeFormatter.ofPattern("h:mma")) + " to: " +
                toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + toTime.format(DateTimeFormatter.ofPattern("h:mma"));
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[E][X] " + super.description + " (" + DateTimeString + ")": "[E][ ] " + super.description + " (" + DateTimeString + ")"); // mark done task with X
    }

    @Override
    public String printFileFormat() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return super.printFileFormat() + " | " + fromDate.format(inputFormatter) + " " + fromTime.format(inputTimeFormatter) + " - " + toDate.format(inputFormatter) + " " + toTime.format(inputTimeFormatter);
    }
}

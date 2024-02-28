package Tasks;


import UserInputs.Parser;
import commands.Command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    protected LocalTime byTime;
    protected String deadLineString;

    public Deadline(String description, LocalDate by, LocalTime byTime) {
        super(description);
        this.by = by;
        this.byTime = byTime;
        this.deadLineString = by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + byTime.format(DateTimeFormatter.ofPattern("h:mma"));
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[D][X] " + super.description + " (by: " + deadLineString + ")": "[D][ ] " + super.description + " (by: " + deadLineString + ")"); // mark done task with X
    }

    @Override
    public String printFileFormat() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return super.printFileFormat() + " | " + this.by.format(inputFormatter) + " " + this.byTime.format(inputTimeFormatter);
    }
}
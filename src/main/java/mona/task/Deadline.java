package mona.task;

import mona.util.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;
    protected boolean isLocalDateTimeStored;
    protected DateTimeFormatter inputFormatter;
    protected DateTimeFormatter outputFormatter;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.isLocalDateTimeStored = true; // set to true by default, exceptions will set it to false.
        this.inputFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_INPUT_FORMAT);
        this.outputFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_OUTPUT_FORMAT);

        try {
            this.dateTime = LocalDateTime.parse(by, inputFormatter);
        } catch (DateTimeParseException e) {
            this.isLocalDateTimeStored = false;
        }
    }
    @Override
    public String toString() {
        String output = "[D]" + super.toString() + " " +  "(" + "by: ";

        if (!isLocalDateTimeStored) {
            output += this.by + ")";
        } else {
            output += this.dateTime.format(outputFormatter) + ")";
        }

        return output;
    }
    @Override
    public String getDescription() {
        return super.getDescription() + " "
                + "/by " + this.by;
    }
}

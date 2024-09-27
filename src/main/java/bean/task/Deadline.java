package bean.task;

import bean.command.exception.NoValueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate byDate;
    private String byString;

    public Deadline(String description, String by) throws NoValueException {
        super(description);
        if (by == null) {
            throw new NoValueException();
        }

        try{
            this.byDate = LocalDate.parse(by);
            this.byString = this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.byString = by;
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byString + ")";
    }

    public String toCommand() {
        return "deadline " + description + " /by " + byString + " /isDone " + isDone;
    }

}
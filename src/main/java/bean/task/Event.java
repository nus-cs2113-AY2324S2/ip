package bean.task;

import bean.command.exception.NoValueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String startString;
    private LocalDate startDate;
    private String endString;
    private LocalDate endDate;

    public Event(String description, String start, String end) throws NoValueException {
        super(description);
        if(start == null || end == null){
            throw new NoValueException();
        }

        try{
            this.startDate = LocalDate.parse(start);
            this.startString = this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.startString = start;
        }

        try{
            this.endDate = LocalDate.parse(end);
            this.endString = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.endString = end;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startString + " to: " + endString + ")";
    }

    public String toCommand() {
        return "event " + description + " /start " + startString + " /end " + endString + " /isDone " + isDone;
    }
}
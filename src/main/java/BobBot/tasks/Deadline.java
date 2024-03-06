package BobBot.tasks;

import java.time.format.DateTimeFormatter;

import BobBot.exceptions.InvalidDeadlineException;
import BobBot.parser.DateParser;

public class Deadline extends Task {

    protected String task;
    protected String by;
    protected String parsedDateString;

    public Deadline(String description) throws InvalidDeadlineException {
        super(description);

        if (!description.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        this.task = this.description.substring(
                "Deadline".length(),
                this.description.indexOf("/by")).trim();
        this.by = this.description.substring(
                this.description.indexOf("/by") + "/by".length()).trim();

        if (DateParser.containsValidDateString(this.by)) {
            String dateString = DateParser.detectDateFromByString(this.by);
            this.parsedDateString = DateParser.parseDateFromDateString(dateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.by = this.by.replace(dateString, this.parsedDateString);
        }

        if (this.task.length() == 0 || this.by.length() == 0) {
            throw new InvalidDeadlineException();
        }
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.task + " (by: " + by + ")";
    }

}

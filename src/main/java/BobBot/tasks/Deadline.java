package BobBot.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import BobBot.exceptions.InvalidDeadlineException;

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

        if (containsValidDateString(this.by)) {
            String dateString = detectDateFromByString(this.by);
            this.parsedDateString = parseDateFromDateString(dateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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

    public LocalDate parseDateFromDateString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }

    public String detectDateFromByString(String byString) {
        final int DATE_LENGTH = 10;
        int startingIndex = byString.indexOf("-") - "yyyy".length();
        int endingIndex = startingIndex + DATE_LENGTH;
        return byString.substring(startingIndex, endingIndex);
    }

    public boolean containsValidDateString(String byString) {
        String dateString = "";

        if (byString.contains("-") && byString.length() >= 10) {
            dateString = detectDateFromByString(byString);
        }

        String regexString = ".*\\d{4}-\\d{2}-\\d{2}.*";
        return dateString.matches(regexString);
    }
}

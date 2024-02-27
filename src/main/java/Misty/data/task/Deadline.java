package misty.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String by;
    private boolean isByDateFormat;
    private LocalDate byDate;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        try {
            byDate = LocalDate.parse(by);
            isByDateFormat = true;
        } catch (DateTimeParseException e) {
            isByDateFormat = false;
        }
    }

    public String getBy() {
        return by;
    }

    public LocalDate getByDate() {
        return byDate;
    }
    public String getFullByDate() {
        return byDate.getDayOfWeek() + ", " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public boolean getIsByDateFormat() {
        return isByDateFormat;
    }

    @Override
    public String toString() {
        return "[D]" + getStatus() + " " + getTaskName() + " (by: " + (isByDateFormat ? getFullByDate() : getBy()) + ")";
    }

    public boolean isSameDate(LocalDate localDate) {
        if (isByDateFormat) {
            return byDate.equals(localDate);
        }
        return false;
    }

}

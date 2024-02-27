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
    public String getByDate() {
        return byDate.getDayOfWeek() + ", " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + getStatus() + " " + getTaskName() + " (by: " + (isByDateFormat ? getByDate() : getBy()) + ")";
    }
}

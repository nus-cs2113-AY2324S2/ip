package misty.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Creates deadline.
 */
public class Deadline extends Task {
    /**
     * Due date of deadline.
     */
    private String by;
    private boolean isByDateFormat;
    /**
     * Due date as LocalDate type.
     */
    private LocalDate byDate;

    /**
     * Constructs Deadline object.
     *
     * @param taskName Name of deadline.
     * @param by Due date/time of deadline.
     */
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

    /**
     * Returns due date of deadline.
     *
     * @return Due date of deadline.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns due date of deadline as LocalDate.
     *
     * @return Due date of deadline as LocalDate.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Returns detailed date as a string in (dayOfWeek, mmm d yyy) format.
     *
     * @return Detailed date.
     */
    public String getFullByDate() {
        return byDate.getDayOfWeek() + ", "
                + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns whether due date has valid LocalDate value.
     *
     * @return True if due date has LocalDate value else false.
     */
    public boolean getIsByDateFormat() {
        return isByDateFormat;
    }

    /**
     * Returns string of how deadline should be printed to screen.
     *
     * @return String to be printed to screen.
     */
    @Override
    public String toString() {
        return "[D]" + getStatus() + " " + getTaskName() + " (by: "
                + (isByDateFormat ? getFullByDate() : getBy()) + ")";
    }

    /**
     * Returns whether date passed as parameter is the same as due date of deadline.
     *
     * @param localDate Date to be compared to deadline due date.
     * @return True if dates are the same else false.
     */
    public boolean isSameDate(LocalDate localDate) {
        if (isByDateFormat) {
            return byDate.equals(localDate);
        }
        return false;
    }
}
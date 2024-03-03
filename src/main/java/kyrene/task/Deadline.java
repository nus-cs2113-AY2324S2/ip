package kyrene.task;

import kyrene.exception.KyreneMissingTimeException;
import kyrene.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents a deadline task that contains task type as "D", task name,
 * done status, and a specific deadline.
 */
public class Deadline extends Task{
    protected static DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy");
    protected LocalDateTime deadline;

    public Deadline() {
        super(null);
    }

    /**
     * Construct a deadline task with a string that contains task name and deadline.
     * If deadline information is missing, then throws KyreneMissingTimeException.
     *
     * @param line String that contains task name and deadline.
     * @throws KyreneMissingTimeException If deadline information is missing.
     */
    public Deadline(String line) throws KyreneMissingTimeException {
        super(line);
        taskType = "D";
        int dividerIndex = line.indexOf("/by");
        if(dividerIndex == -1){
            throw new KyreneMissingTimeException();
        }
        String deadline = line.substring(dividerIndex + "/by ".length());
        setDeadline(deadline);
        taskName = line.substring(0, dividerIndex - " ".length());
        setTaskName(taskName);
    }

    /**
     * Returns a string representing deadline date and time of the task.
     *
     * @return String representing deadline date and time.
     */
    public String getDeadline() {
        return deadline.format(formatterOutput);
    }

    /**
     * Set deadline date and time for the task.
     * If the string format is invalid, it tries to set deadline with date only.
     *
     * @param deadline String that contains date and time information.
     */
    public void setDeadline(String deadline) {
        try {
            this.deadline = LocalDateTime.parse(deadline, formatterInput);
        } catch (DateTimeParseException e) {
            setDeadlineWithoutTime(deadline);
        }
    }

    /**
     * Set deadline date and time of 2359 for the task.
     * If the string format is invalid, it tries to set deadline with time only.
     *
     * @param deadline String that contains date information.
     */
    private void setDeadlineWithoutTime(String deadline) {
        try {
            this.deadline = LocalDateTime.parse(deadline + " 2359", formatterInput);
        } catch (DateTimeParseException e) {
            setDeadlineWithoutDate(deadline);
        }
    }

    /**
     * Set deadline as today's date and time for the task.
     * If the string format is invalid, it requires another input of correct format.
     *
     * @param deadline String that contains date and time information.
     */
    private void setDeadlineWithoutDate(String deadline) {
        try {
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.deadline = LocalDateTime.parse(today + " " + deadline, formatterInput);
        } catch (DateTimeParseException e) {
            Ui.showErrorInvalidDdlTimeFormat();
            deadline = Ui.readCommand();
            setDeadline(deadline);
        }
    }

    /**
     * Returns true if input time is before deadline, otherwise returns false.
     *
     * @param time String that contains date and time information.
     * @return True if input time is before deadline, otherwise false.
     */
    public boolean isBefore(LocalDateTime time) {
        if (deadline.isAfter(time)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representing the deadline task that is used for output purpose.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by %s)", taskType, doneSymbol, taskName, getDeadline());
    }

    /**
     * Returns a string representing the deadline task that is used for storage purpose.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String format() { return String.format("%b deadline %s /by %s\n", isDone, taskName, deadline.format(formatterInput));}

}

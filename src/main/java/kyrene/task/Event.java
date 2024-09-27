package kyrene.task;

import kyrene.exception.KyreneMissingTimeException;
import kyrene.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task that contains task type as "E", task name,
 * done status, and a specific start time and end time.
 */
public class Event extends Task{
    protected static DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy");
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event() {
        super(null);
    }

    /**
     * Construct an event task with a string that contains task name and time information.
     * If time information is missing, then throws KyreneMissingTimeException.
     *
     * @param line String that contains task name and time information.
     * @throws KyreneMissingTimeException If start time or end time information is missing.
     */
    public Event(String line) throws KyreneMissingTimeException {
        super(line);
        taskType = "E";
        int startDividerIndex = line.indexOf("/from");
        if(startDividerIndex == -1){
            throw new KyreneMissingTimeException();
        }
        int endDividerIndex = line.indexOf("/to");
        if(endDividerIndex == startDividerIndex){
            throw new KyreneMissingTimeException();
        }
        String startTime = line.substring(startDividerIndex + "/from ".length(), endDividerIndex - " ".length());
        String endTime = line.substring(endDividerIndex + "/to ".length());
        String taskName = line.substring(0, startDividerIndex - " ".length());
        setStartTime(startTime);
        setEndTime(endTime);
        setTaskName(taskName);
    }

    /**
     * Returns a string representing start date and time of the task.
     *
     * @return String representing start date and time.
     */
    public String getStartTime() {
        return startTime.format(formatterOutput);
    }

    /**
     * Returns a string representing end date and time of the task.
     *
     * @return String representing end date and time.
     */
    public String getEndTime() {
        return endTime.format(formatterOutput);
    }

    /**
     * Set start date and time for the task.
     * If the string format is invalid, it tries to set start time with date only.
     *
     * @param startTime String that contains date and time information.
     */
    public void setStartTime(String startTime) {
        try {
            this.startTime = LocalDateTime.parse(startTime, formatterInput);
        } catch (DateTimeParseException e) {
            setStartTimeWithoutTime(startTime);
        }
    }

    /**
     * Set start date and time of 0000 for the task.
     * If the string format is invalid, it tries to set start time with time only.
     *
     * @param startTime String that contains date information.
     */
    private void setStartTimeWithoutTime(String startTime) {
        try {
            this.startTime = LocalDateTime.parse(startTime + " 0000", formatterInput);
        } catch (DateTimeParseException e) {
            setStartTimeWithoutDate(startTime);
        }
    }

    /**
     * Set start date as today's date and time for the task.
     * If the string format is invalid, it requires another input of correct format.
     *
     * @param startTime String that contains time information.
     */
    private void setStartTimeWithoutDate(String startTime) {
        try {
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.startTime = LocalDateTime.parse(today + " " + startTime, formatterInput);
        } catch (DateTimeParseException e) {
            Ui.showErrorInvalidEventStartTimeFormat();
            startTime = Ui.readCommand();
            setStartTime(startTime);
        }
    }

    /**
     * Set end date and time for the task.
     * If the string format is invalid, it tries to set end time with date only.
     *
     * @param endTime String that contains date and time information.
     */
    public void setEndTime(String endTime) {
        try {
            this.endTime = LocalDateTime.parse(endTime, formatterInput);
        } catch (DateTimeParseException e) {
            setEndTimeWithoutTime(endTime);
        }
    }

    /**
     * Set end date and time of 0000 for the task.
     * If the string format is invalid, it tries to set end time with time only.
     *
     * @param endTime String that contains date information.
     */
    private void setEndTimeWithoutTime(String endTime) {
        try {
            this.endTime = LocalDateTime.parse(endTime + " 2359", formatterInput);
        } catch (DateTimeParseException e) {
            setEndTimeWithoutDate(endTime);
        }
    }

    /**
     * Set end date as today's date and time for the task.
     * If the string format is invalid, it requires another input of correct format.
     *
     * @param endTime String that contains time information.
     */
    private void setEndTimeWithoutDate(String endTime) {
        try {
            String eventDay = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.endTime = LocalDateTime.parse(eventDay + " " + endTime, formatterInput);
        } catch (DateTimeParseException exc) {
            Ui.showErrorInvalidEventEndTimeFormat();
            endTime = Ui.readCommand();
            setEndTime(endTime);
        }
    }

    /**
     * Returns true if input date is within the event duration, otherwise returns false.
     *
     * @param date String that contains date information.
     * @return True if input date is within the event duration, otherwise false.
     */
    public boolean isAt(LocalDate date) {
        if (startTime.toLocalDate().isAfter(date) || endTime.toLocalDate().isBefore(date)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representing the event task that is used for output purpose.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString(){
        return String.format("[%s][%s] %s (from: %s to: %s)", taskType, doneSymbol, taskName, getStartTime(), getEndTime());
    }

    /**
     * Returns a string representing the event task that is used for storage purpose.
     *
     * @return A string representing the event task.
     */
    @Override
    public String format() {
        String start = startTime.format(formatterInput);
        String end = endTime.format(formatterInput);
        return String.format("%b event %s /from %s /to %s\n", isDone, taskName, start, end);
    }

}

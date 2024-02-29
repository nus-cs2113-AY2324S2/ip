package kyrene.task;

import kyrene.exception.KyreneMissingTimeException;
import kyrene.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected static DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy");
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event() {
        super(null);
    }

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

    public String getStartTime() {
        return startTime.format(formatterOutput);
    }

    public String getEndTime() {
        return endTime.format(formatterOutput);
    }

    public void setStartTime(String startTime) {
        try {
            this.startTime = LocalDateTime.parse(startTime, formatterInput);
        } catch (DateTimeParseException e) {
            setStartTimeWithoutTime(startTime);
        }
    }

    private void setStartTimeWithoutTime(String startTime) {
        try {
            this.startTime = LocalDateTime.parse(startTime + " 0000", formatterInput);
        } catch (DateTimeParseException e) {
            setStartTimeWithoutDate(startTime);
        }
    }

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

    public void setEndTime(String endTime) {
        try {
            this.endTime = LocalDateTime.parse(endTime, formatterInput);
        } catch (DateTimeParseException e) {
            setEndTimeWithoutTime(endTime);
        }
    }

    private void setEndTimeWithoutTime(String endTime) {
        try {
            this.endTime = LocalDateTime.parse(endTime + " 2359", formatterInput);
        } catch (DateTimeParseException e) {
            setEndTimeWithoutDate(endTime);
        }
    }

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

    public boolean isAt(LocalDate date) {
        if (startTime.toLocalDate().isAfter(date) || endTime.toLocalDate().isBefore(date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return String.format("[%s][%s] %s (from: %s to: %s)", taskType, doneSymbol, taskName, getStartTime(), getEndTime());
    }

    @Override
    public String format() { return String.format("%b event %s /from %s /to %s\n", isDone, taskName, startTime.format(formatterInput), endTime.format(formatterInput));}

}

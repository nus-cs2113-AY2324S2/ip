package huan.task;

import huan.main.UI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for tasks with a specific deadline
 */
public class DeadlineTask extends Task{
    private String ddlTime;
    private LocalDateTime dateTime;

    /**
     * Constructor method for DeadlineTask
     * @param name task name
     * @param ddlTime the time of the deadline
     * @param isDone whether the task is marked as finished
     */
    public DeadlineTask(String name, String ddlTime, Boolean isDone) {
        setName(name);
        this.ddlTime = ddlTime;
        setIsDone(isDone);
        setTaskType(3);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime = LocalDateTime.parse(ddlTime, formatter);
            UI.displayDateTimeParseSuccess();

        } catch(DateTimeParseException e) {
            dateTime = null;
            UI.displayDateTimeParseError();
        }
    }

    /**
     * Method for checking whether the deadline is before a specific time
     * @param dateTime the given time
     * @return whether the deadline is before a specific time
     */
    public Boolean isBefore(LocalDateTime dateTime) {
        if (this.dateTime == null) {
            return false;
        }
        return this.dateTime.isBefore(dateTime);
    }

    /**
     * Method for printing an DeadlineTask
     */
    @Override
    public void printTask() {
        System.out.println("[D][" + (getIsDone() ? "X" : " ") + "] " + getName() + " (by: " + ddlTime + ")");
    }

    /**
     * Method for writing the DeadlineTask to file
     * @return the line to be written to ile
     */
    @Override
    public String writeLine() {
        return "D" + (getIsDone() ? "T" : "F") + " " + getName() + " /by " + ddlTime;
    }

    /**
     * Get method for the ddlTime attribute
     * @return the ddlTime attribute
     */
    public String getDdlTime() {
        return ddlTime;
    }

    /**
     * Set method for the ddlTime attribute
     * @param ddlTime the ddlTime attribute
     */
    public void setDdlTime(String ddlTime) {
        this.ddlTime = ddlTime;
    }

}

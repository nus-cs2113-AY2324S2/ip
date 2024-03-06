package huan.task;

import huan.main.UI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class DeadlineTask extends Task{
    private String ddlTime;
    private LocalDateTime dateTime;
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

    public Boolean isBefore(LocalDateTime dateTime) {
        return this.dateTime.isBefore(dateTime);
    }

    @Override
    public void printTask() {
        System.out.println("[D][" + (getIsDone() ? "X" : " ") + "] " + getName() + " (by: " + ddlTime + ")");
    }

    @Override
    public String writeLine() {
        return "D" + (getIsDone() ? "T" : "F") + " " + getName() + " /by " + ddlTime;
    }
    public String getDdlTime() {
        return ddlTime;
    }

    public void setDdlTime(String ddlTime) {
        this.ddlTime = ddlTime;
    }

}

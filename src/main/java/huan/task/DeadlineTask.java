package huan.task;

import java.util.Objects;

public class DeadlineTask extends Task{
    private String ddlTime;

    public DeadlineTask(String name, String ddlTime, Boolean isDone) {
        setName(name);
        this.ddlTime = ddlTime;
        setIsDone(isDone);
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

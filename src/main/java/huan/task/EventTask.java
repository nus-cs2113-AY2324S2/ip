package huan.task;

import java.util.Objects;

public class EventTask extends Task{
    private String startTime, endTime;

    public EventTask(String name, String startTime, String endTime, Boolean isDone) {
        setName(name);
        this.startTime = startTime;
        this.endTime = endTime;
        setIsDone(isDone);
    }

    public void printTask() {
        System.out.println("[E][" + (getIsDone() ? "X" : " ") + "] " + getName() + " (from: " + startTime + " to: " + endTime + ")");
    }

    @Override
    public String writeLine() {
        return "E" + (getIsDone() ? "T" : "F") + " " + getName() + " /from " + startTime + " /to " + endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }
}

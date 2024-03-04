package taskPackage;

import taskPackage.Task;

public class Events extends Task {

    private String fromDate;
    private String toDate;

    public Events(String description, String fromDate, String toDate,boolean isDone) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription(){

        return super.getDescription() + " (from: " + fromDate + " to: " + toDate + ")";
    }

    public String getFromDate() {

        return fromDate;
    }

    public String getToDate() {

        return toDate;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (from: " + getFromDate() + " to: " + getToDate() + ")";
    }
}
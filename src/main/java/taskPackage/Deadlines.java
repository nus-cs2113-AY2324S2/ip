package taskPackage;

import taskPackage.Task;

public class Deadlines extends Task {

    protected String by;

    public Deadlines(String description, String by, boolean isDone) {

        super(description, isDone);
        this.by = by;
    }

    public String getByDate(){

        return by;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String getDescription(){

        return super.getDescription() + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + getByDate() + ")";
    }
}

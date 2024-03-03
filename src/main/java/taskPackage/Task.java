package taskPackage;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String toFileString() {

        if (this instanceof ToDos) {
            return "T | " + (isDone ? "1" : "0") + " | " + getDescription();
        } else if (this instanceof Deadlines) {
            Deadlines deadline = (Deadlines) this;
            String description = getDescription();
            String byDate = deadline.getByDate();

            if (description.contains(" (by: ")) {
                description = description.substring(0, description.indexOf(" (by: "));
            }
            return "D | " + (isDone ? "1" : "0") + " | " + description
                    + (byDate.isEmpty() ? "" : " | " + byDate);
        } else if (this instanceof Events) {
            Events event = (Events) this;
            String description = getDescription();
            String fromDate = event.getFromDate();
            String toDate = event.getToDate();

            if (description.contains(" (from: ")) {
                description = description.substring(0, description.indexOf(" (from: "));
            }
            return "E | " + (isDone ? "1" : "0") + " | " + description + (fromDate.isEmpty() ? "" : " " +
                    "| from " + fromDate + (toDate.isEmpty() ? "" : " to " + toDate));
        } else {
            return "Unknown task";
        }
    }

}

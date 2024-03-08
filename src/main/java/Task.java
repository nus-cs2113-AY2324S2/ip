public abstract class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getType(){
        return "";
    }
    @Override //converts hexadecimal output to string
    public String toString() {
        return "[" + getType() + "]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }
    public abstract String toFileString();
}
class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
class DeadlineTask extends Task{
    private String deadline;
    public DeadlineTask(String description, String deadline){
        super(description);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + (isDone ? "X" : " ") + "] " + description + " (by: " + deadline + ")";
    }

    @Override
    public String getType() {
        return "D";
    }
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }
}

class EventTask extends Task {
    private String fromDate, toDate;
    public EventTask(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    @Override
    public String getType() {
        return "E";
    }
    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + (isDone ? "X" : " ") + "] " + description + " (from: " + fromDate + " to: " + toDate + ")";
    }
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + fromDate + " | " + toDate;
    }
}

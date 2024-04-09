/**
 * Represents an abstract task with a description and a completion status.
 */
public abstract class Task {
    public String description;
    public boolean isDone;
    /**
     * Constructs a new Task with a specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }
    /**
     * Returns the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns the type of the task as a String.
     * The specific task type is defined in subclasses.
     *
     * @return The task type.
     */
    public String getType(){
        return "";
    }
    /**
     * Returns the string representation of the task, including its type, status, and description.
     *
     * @return The string representation of the task.
     */
    @Override //converts hexadecimal output to string
    public String toString() {
        return "[" + getType() + "]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }
    /**
     * Converts the task to a format to be saved in text file.
     *
     * @return The string representation of the task.
     */
    public abstract String toFileString();
}

/**
 * Represents a todo task without any date/time.
 */
class TodoTask extends Task {
    /**
     * Constructs a new TodoTask with a specified description.
     *
     * @param description The description of the todo task.
     */
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

/**
 * Represents a deadline task with a specific due date.
 */
class DeadlineTask extends Task{
    private String deadline;
    /**
     * Constructs a new DeadlineTask with a specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline of the task.
     */
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
/**
 * Represents an event task with a start and end time.
 */
class EventTask extends Task {
    private String fromDate, toDate;
    /**
     * Constructs a new EventTask with a specified description, start date/time, and end date/time.
     *
     * @param description The description of the event task.
     * @param fromDate The start date/time of the event.
     * @param toDate The end date/time of the event.
     */
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

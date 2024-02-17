package bob.task;

public class Event extends Task {
    private final String startDate;
    private final String endDate;

    private Event(String taskName, boolean isCompleted, String startDate, String endDate) {
        super(taskName, isCompleted);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String taskName, String startDate, String endDate) {
        super(taskName, false);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public Task markTaskAsComplete() {
        return new Event(this.taskName, true, this.startDate, this.endDate);
    }

    public Task markTaskAsIncomplete() {
        return new Event(this.taskName, false, this.startDate, this.endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.startDate, this.endDate);
    }
}

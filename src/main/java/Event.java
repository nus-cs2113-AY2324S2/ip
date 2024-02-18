public class Event extends Task {
    private final String startTime;
    private final String endTime;
    Event(String description, String startTime, String endTime){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        if (this.isDone){
            return "[E][X] " + this.taskName + String.format(" (from: %s to: %s)", startTime, endTime );
        }
        else{
            return "[E][ ] " + this.taskName + String.format(" (from: %s to: %s)", startTime, endTime );
    }

    }
}

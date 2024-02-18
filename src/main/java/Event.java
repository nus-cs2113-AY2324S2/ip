public class Event extends Task{
    protected String startTime;
    protected String endTime;

    @Override
    public String getDescription(){
        return ("[E][" + this.getStatusIcon() + "] " + this.description + " (from: " + startTime + " to: " + endTime +
                ")");
    }
    public Event(String description, String start, String ending, boolean isCompleted) {
        super(description);
        this.startTime = start;
        this.endTime = ending;
        this.isDone = isCompleted;
    }
    public String getStartTime() {
        return this.startTime;
    }
    public void setStartTime(String time) {
        this.startTime = time;
    }

    public String getEndTime() {
        return this.endTime;
    }
    public void setEndTime(String time) {
        this.endTime = time;
    }

}

public class Event extends Task{
    protected String TYPE = "event";
    protected String startTime;
    protected String endTime;


    public String getDescription(){
        return ("[E][" + this.getStatusIcon() + "] " + this.description + " (from: " + startTime + " to: " + endTime +
                ")");
    }
    public Event(String description, String start, String ending) {
        super(description);
        this.startTime = start;
        this.endTime = ending;
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

    public String getTYPE() {
        return this.TYPE;
    }

}

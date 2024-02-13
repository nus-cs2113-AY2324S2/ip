public class Event extends Task {
    private String startTime;
    private String endTime;
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString(){
        String duration = "(from: " + this.getStartTime() + " to: " + this.getEndTime() + ")";
        return "[E]" + super.toString() + duration;
    }
}

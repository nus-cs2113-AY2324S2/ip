package Sinep;
public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;
    public Event(String description) {
        super(description);
        try {
            String[] givenTimeline = description.split("/from ", 2);
            //this.description = description;
            this.description = givenTimeline.length >= 1 ? givenTimeline[0].trim() : "";
            if (givenTimeline.length > 1) {
                String[] startEnd = givenTimeline[1].split(" /to ", 2);
                this.startDateTime = startEnd.length > 0 ? startEnd[0].trim() : "";
                this.endDateTime = startEnd.length > 0 ? startEnd[1].trim() : "";
            } else {
                this.startDateTime = "";
                this.endDateTime = "";
            }
        } catch (Exception e) {
            this.startDateTime = "";
            this.endDateTime = "";
        }
    }

    @Override
    public String toString() {
            return "[E]" + getStatusIcon() + " " + description + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }
}

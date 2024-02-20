public class Event extends Task{

  private String startDateTime;
  private String endDateTime;

  public Event(String description, String from, String to){
    super(description);
    this.startDateTime=from;
    this.endDateTime=to;
  }

  public String getStartDateTime() {
    return this.startDateTime;
  }

  public String getEndDateTime() {
    return this.endDateTime;
  }

  public void setStartDateTime(String startDateTime) {
    this.startDateTime=startDateTime;
  }
  public void setEndDateTime(String endDateTime) {
    this.endDateTime=endDateTime;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + this.startDateTime + " to: "+this.endDateTime +")";
  }


}

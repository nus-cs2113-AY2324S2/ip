public class Event extends Task{
    private String fromTime;
    private String toTime;
    public Event(String description, int arrayIndex) {
        super(description, arrayIndex);
        int fromIndex = description.indexOf("/from");
        int toIndex = description.indexOf("/to");
        this.description = description.substring(0,fromIndex).trim();
        this.fromTime = description.substring(fromIndex + 5, toIndex).trim();
        this.toTime = description.substring(toIndex+3).trim();
        this.taskType = Type.Event;
    }

    @Override
    public String toString() {
        return this.getIndex()+ ". [E] ["+ this.getCheckMark()+"] "
                +this.getDescription()+ " (from: " +fromTime+ " to: " +toTime+ ")";
    }
}

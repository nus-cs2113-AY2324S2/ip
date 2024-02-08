public class Event extends Task{
    private String startTime;
    private String endTime;
    public Event(String description, int arrayIndex) {
        super(description, arrayIndex);
        this.taskType = Type.Event;
    }

    @Override
    public String toString() {
        return this.getIndex()+ ". [E] ["+ this.getCheckMark()+"] " +this.getDescription();
    }
}

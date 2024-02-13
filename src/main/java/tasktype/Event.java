package tasktype;

public class Event extends Task {
    private final static String ICON_TYPE = "[E]";
    protected String startDate;
    protected String endDate;
    public Event(String description, String fromDate, String toDate){
        super(description);
        this.startDate = fromDate;
        this.endDate = toDate;
    }
    public String toString(){
        return ICON_TYPE + super.toString() + "(from:" + startDate + " to:"+ endDate+")";
    }
}

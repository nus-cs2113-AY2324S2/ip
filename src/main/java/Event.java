public class Event extends Todo{
    protected String startDate;
    protected String endDate;
    public Event(String description, String startDate, String endDate){
        super(description);
        this.tag = "E";
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString(){
        return super.toString() + " (from: " + this.startDate
                + " to: " + this.endDate + ")";
    }
}
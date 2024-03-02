package porato.tasks;

/**
 * Represents the event tasks
 * It has a start date and end date
 */
public class Events extends Task{
    private String startDate;
    private String endDate;
    public Events(String line, String startDate, String endDate) {
        super(line);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTask(){
        return super.getTask() + "/" + startDate + "/" + endDate;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from:" + startDate  + "to:" + endDate + ")";
    }
}

package duke.tasks;

public class Events extends Task{
    /** Task start date */
    private String startDate;
    /** Task end date */
    private String endDate;
    public Events(String line, String startDate, String endDate) {
        super(line);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from:" + startDate  + "to:" + endDate + ")";
    }
}

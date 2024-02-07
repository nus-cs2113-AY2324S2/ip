public class Event extends Task{

    public String taskType = "E";
    private String startDate;
    private String endDate;

    public Event(String Description, String startDate, String endDate){
        super(Description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String toString() {
        return "[" + this.getTaskType() + "]" + super.toString()
                + " (from: " +this.getStartDate() + " to: " + this.getEndDate() + " )";
    }

}

public class Deadline extends Task{

    public String taskType = "D";
    private String endDate;
    public Deadline(String Description, String Date){
        super(Description);
        this.endDate = Date;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String date) {
        endDate = date;
    }

    public String toString() {
        return "[" + this.getTaskType() + "]" + super.toString() + (" (by: " + this.getEndDate() + ")");
    }
}

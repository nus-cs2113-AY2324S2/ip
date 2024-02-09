public class Deadline extends Task {
    protected String deadline;
    public Deadline (int order,boolean marked, String deadlineName, String deadline) {
        super(order,marked,deadlineName);
        this.deadline = deadline;
    }
    @Override
    public String getEndTime(){
        return deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String displayCurrentTask () {
        String typeCheckbox = "[D]";
        return typeCheckbox + " " + this.doneCheckbox + this.taskName;
    }

}

public class Deadline extends Task{
    private String deadline;
    public Deadline(String description, int arrayIndex) throws EmptyTaskDescriptionException {
        super(description, arrayIndex);
        int deadlineIndex = description.indexOf("/by");
        this.description = description.substring(0,deadlineIndex).trim();
        this.deadline = description.substring(deadlineIndex + 3).trim();
        if (this.description.isEmpty() || this.deadline.isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }
        this.taskType = Type.Deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return this.getIndex()+ ". [D] ["+ this.getCheckMark()+"] "
                +this.getDescription()+ "(by: " +this.deadline+ ")";
    }
}

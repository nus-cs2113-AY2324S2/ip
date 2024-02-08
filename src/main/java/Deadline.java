public class Deadline extends Task{
    public Deadline(String description, int arrayIndex) {
        super(description, arrayIndex);
        this.taskType = Type.Deadline;
    }

    @Override
    public String toString() {
        return this.getIndex()+ ". [D] ["+ this.getCheckMark()+"] " +this.getDescription();
    }
}

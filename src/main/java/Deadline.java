public class Deadline extends Task{
    private final String deadlineString;

    Deadline(String description, String deadline){
        super(description);
        this.deadlineString = deadline;
    }

    Deadline(String description, String deadline, boolean isDone){
        super(description, isDone);
        this.deadlineString = deadline;
    }
    @Override
    public java.lang.String toString() {
        if (this.isDone){
            return "[D][X] " + this.taskName + String.format(" (by: %s)", deadlineString );
        }
        else{
            return "[D][ ] " + this.taskName + String.format(" (by: %s)", deadlineString );
        }
    }

    @Override
    public String toSerial() {
        return ("deadline," + super.toSerial() + ","+ this.deadlineString);
    }
}

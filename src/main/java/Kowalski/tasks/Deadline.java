package Kowalski.tasks;

public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, String by) {
        super(description);
        this.deadline = by;
    }

    /**
     * This function returns the deadline of the "deadline" event
     * @return String of the Deadline
     */
    public String getDeadline() {
        return deadline;
    }

    @Override
    public String textFileInputString(){
        return String.format("D | %s | %s | %s",
                isDone? "X" : "0",
                getDescription().trim(),
                getDeadline());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}

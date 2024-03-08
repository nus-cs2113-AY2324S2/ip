package tasks;

import customexceptions.IncompletePromptException;

import java.time.format.DateTimeParseException;

/** Type of task with a single date */
public class Deadline extends Task {
    /**
     * Constructs a deadline object, initially does not have a deadline yet.
     *
     * @param task The deadline task
     */
    public Deadline(String task) {
        super(task);
        this.haveDeadline = false;
        setTaskType("D");
    }
    protected boolean haveDeadline;
    protected String deadline;

    /**
     * Returns the deadline associated with the task.
     *
     * @return Deadline.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline based on "d MMM yyyy" format and indicates that the task is a deadline as well.
     *
     * @param deadline Deadline associated with task.
     */
    public void setDeadline(String deadline) throws IncompletePromptException {
        try {
            this.deadline = parseStringToDate(deadline);
        } catch (DateTimeParseException e) {
            System.out.println("Sorry, the deadline date inputted could not be parsed.");
            throw new IncompletePromptException(false);
        }
        this.haveDeadline = true;
    }

    /** Prints the deadline information such as the description and deadline when presented by UI. */
    @Override
    public void print() {
        String additionalInfo;
        if (haveDeadline) {
            additionalInfo = " (by: " + deadline + ")";
        } else {
            additionalInfo = "";
        }
        System.out.print('[' + getTaskType() + ']');
        System.out.print(isMarked() ? "[X] " : "[ ] ");
        System.out.println(getTaskDescription() + additionalInfo);
    }
}

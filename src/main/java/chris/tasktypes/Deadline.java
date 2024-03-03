package chris.tasktypes;

import chris.customexceptions.illegalDeadlineInput;

public class Deadline extends Task {
    protected String by;

    public Deadline(String[] deadlineInfo, boolean isDone) throws illegalDeadlineInput {
        super(deadlineInfo[0]);
        if (deadlineInfo.length != 2) {
            throw new illegalDeadlineInput();
        }
        if (deadlineInfo[0].trim().isEmpty() | deadlineInfo[1].trim().isEmpty()) {
            throw new illegalDeadlineInput();
        }
        this.by = deadlineInfo[1].trim();
        this.isDone = isDone;
    }

    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    public String saveString() { return "D|" + super.getStatusIcon() + "|" + super.saveString() + "|" + by; }
}
package kyrene.task;

import kyrene.exception.KyreneMissingTimeException;

public class Deadline extends Task{

    protected String deadline;

    public Deadline() {
        super(null, false);
    }

    public Deadline(String line, boolean isDone) throws KyreneMissingTimeException {
        super(line, isDone);
        taskType = "D";
        int dividerIndex = line.indexOf("/by");
        if(dividerIndex == -1){
            throw new KyreneMissingTimeException();
        }
        String deadline = line.substring(dividerIndex + 4);
        setDeadline(deadline);
        taskName = line.substring(0, dividerIndex - 1);
        setTaskName(taskName);
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by %s)", taskType, doneSymbol, taskName, deadline);
    }

    @Override
    public String format() { return String.format("%b deadline %s /by %s\n", isDone, taskName, deadline);}

}

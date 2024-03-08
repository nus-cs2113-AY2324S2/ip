package anonbot.task;

import anonbot.misc.Parser;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates a new Deadline task. The `by` parameter is optional.
     *
     * @param deadlineDesc The description of the deadline task, which includes the by` parameter.
     * @param taskNumber The task number to assign to this deadline task.
     */
    public Deadline(String deadlineDesc, int taskNumber) {
        super("", taskNumber, TaskType.DEADLINE);
        String[] formattedDeadlineDesc = Parser.parseDeadlineDescription(deadlineDesc);
        setTaskDescription(formattedDeadlineDesc[0]);
        setBy(formattedDeadlineDesc[1]);
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public void printTask() {
        System.out.format("%d.[D][%c] %s (by: %s)" + System.lineSeparator(),
                getTaskNumber(),
                isTaskDone() ? 'X' : ' ',
                getTaskDescription(),
                getBy());
    }
}

package anonbot.task;

import anonbot.misc.Parser;

public class Deadline extends Task {
    private String by;

    public Deadline(String deadlineDesc, int taskNumber) {
        super("", taskNumber, TaskType.DEADLINE);
        String[] formattedDeadlineDesc = Parser.parseDeadlineDescription(deadlineDesc);
        setTaskDescription(formattedDeadlineDesc[0]);
        setBy(formattedDeadlineDesc[1]);
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
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

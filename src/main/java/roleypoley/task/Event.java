package roleypoley.task;

import roleypoley.exception.RoleyPoleyParseException;

/**
 * Represents Event tasks to be completed
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Assumption: Every field must be present and not NULL
     */
    public Event(String description, boolean isDone) throws RoleyPoleyParseException {
        super(getTask(description), isDone);
        this.from = getFrom(description);
        this.to = getTo(description);
    }

    private static String getTask(String description) {
        String[] split = description.split("\\(from|/from");
        return split[0];
    }

    /**
     * Returns the start time of the task
     * @param description task description
     * @return start time of task
     * @throws RoleyPoleyParseException if missing from: or /from
     */
    private static String getFrom(String description) throws RoleyPoleyParseException {
        String[] split = description.split("\\(from: |to: |/from |/to ");
        if (split.length == 1) {
            throw new RoleyPoleyParseException("eventError");
        }
        return split[1];
    }

    /**
     * Returns the end time of the task
     * @param description task description
     * @return end time of task
     * @throws RoleyPoleyParseException if missing /to or to:
     */
    private static String getTo(String description) throws RoleyPoleyParseException {
        String[] split = description.split("\\(from: |to: |/from |/to ");
        if (split.length == 2) {
            throw new RoleyPoleyParseException("deadlineError");
        }
        int endIndex = split[2].length();
        if (description.contains(")")) {
            endIndex -= 1;
        }
        return split[2].substring(0, endIndex );
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(from: " + from + "to: " + to + ")";
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    /**
     * Set event task as completed
     * Prints out reply upon setting status of event task
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description +
                "(from: " + this.from + "to: " + this.to + ")") ;
    }

    /**
     * Set event task as not completed
     * Prints out reply upon setting status of event task
     */
    @Override
    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description +
                "(from: " + this.from + "to: " + this.to + ")") ;
    }
}

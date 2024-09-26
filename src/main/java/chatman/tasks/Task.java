package chatman.tasks;

/**
 * Implements template with behaviours required of any task, regardless of subclass.
 *
 * @author LWachtel1
 * */
public class Task {

    private String description;
    private boolean isDone;

    private String command;

    /**
     * Constructor for Task.
     *
     * @param description Description of task.
     * @param command Command entered by user to add task.
     */
    public Task(String description, String command) {
        this.description = description;
        isDone = false;
        this.command=command;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     *  Returns checkbox representation completion status of task stored in task list.
     *
     * @return String "[X]" or "[ ]" depending on whether instance member isDone contains true or false.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // marks completed task (i.e., isDone is set to true) with [X]
    }


    /**
     * Returns completion checkbox and task description as 1 String
     *
     * @return [X] or [ ], task description
     */
    public String toString() {
        return getStatusIcon() + getDescription();
    }

}
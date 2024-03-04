package kvothe.task;

/**
 * Represents a task with a deadline.
 * Contains a description and a deadline.
 * Inherits from Task.
 */
public class Deadline extends Task {
    protected String by;
    public static final String[] args = {"/by"};

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the task to be saved in the file.
     * @return the string representation of the task to be saved in the file.
     */
    public String toFileString(){
        return "D" + super.toFileString() + this.by + "|";
    }

    public String getBy(){
        return this.by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

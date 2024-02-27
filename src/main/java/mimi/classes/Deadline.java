package mimi.classes;

import static mimi.helper.Storage.FILE_DELIMITER;

/**
 * This class represents a Deadline task, which is a task that has a specific deadline.
 * It inherits from the Task class and overrides the toString and toFileString method.
 */
public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;

    }
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * This method overrides the toString method in the Task class.
     * It returns a string representation of the Deadline task.
     * e.g. [D][✘] return book (by: 2021-08-21)
     * @return a string representation of the Deadline task
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + this.getDeadline() + ")";
    }

    /**
     * This method overrides the toFileString method in the Task class.
     * It returns a string representation of the Deadline task to be written to the file.
     * e.g. D|false|return book|2021-08-21
     * @return a string representation of the Deadline task to be written to the file
     */
    @Override
    public String toFileString(){
        return "D" + FILE_DELIMITER + super.toFileString() + FILE_DELIMITER + this.getDeadline();
    }

}

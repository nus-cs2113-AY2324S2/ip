package mimi.classes;
import static mimi.helper.Storage.FILE_DELIMITER;

/**
 * This class represents a task, which is the parent class of all the other task types.
 * It has a name and a boolean to check if the task is done.
 */
public class Task {
    private String name;
    private boolean isDone;



    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        setDone(true);
    }

    public void markAsUndone() {
        setDone(false);
    }

    /**
     * This method returns a string representation of the task.
     * e.g. [✘] return book
     * @return a string representation of the task
     */
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getName();
    }

    /**
     * This method returns a string representation of the task to be written to the file.
     * e.g. false|return book
     * @return a string representation of the task to be written to the file
     */
    public String toFileString(){
        return  this.isDone + FILE_DELIMITER + this.getName();
    }


}

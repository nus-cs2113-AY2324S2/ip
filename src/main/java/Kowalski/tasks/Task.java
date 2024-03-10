package Kowalski.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method provides the description of a certain task
     * @return details of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method returns the symbol to store the status of a task in the text file
     * @return "X" or " " depending on whether the task is done
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as incomplete
     */
    public void markAsNotDone(){

        this.isDone = false;
    }

    /**
     * Marks a task as complete
     */
    public void markAsDone(){

        this.isDone = true;
    }

    /**
     * Returns the information of the task in a proper format to be stored in the text file
     * @return various information regarding a task in the proper format
     */
    public String textFileInputString() {
        return String.format("%s | %s",
                isDone? "X" : "0",
                getDescription().trim());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

package taskmanager;

import newexceptions.InvalidGetException;

/**
 * Represents a task in the task list through its attributes and methods available to access the attributes
 */

public class Task {
    protected String description = "";
    protected boolean isDone = false;
    protected String startDate = "";
    protected String endDate = "";
    protected String taskType = "";
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the status of whether the task is completed or not
     *
     * @return "X" if the task is completed and " " if it is not
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the task description
     *
     * @return The task description
     */

    public String getDescription() {
        try {
            if (this.description.isEmpty() || this.description == null) {
                throw new InvalidGetException();
            } else {
                return this.description;
            }
        } catch (InvalidGetException e){
            Ui.printInvalidTaskAttributeMessage();
            return this.description;
        }
    }

    /**
     * Changes attribute isDone to true
     */

    public void markAsDone() { // mark done task with X
        this.isDone = true;
    }

    /**
     * Changes attribute isDone to false
     */

    public void markAsUndone() { // unmark task by removing X
        this.isDone = false;
    }

    /**
     * Set the time that the user have to start working on the task
     *
     * @param startDate The time that the user have to start working on the task
     */

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the startDate attribute
     *
     * @return The time that the user have to start working on the task
     */

    public String getStartDate() {
        try {
            if (this.startDate.isEmpty() || this.startDate == null) {
                throw new InvalidGetException();
            } else {
                return this.startDate;
            }
        } catch (InvalidGetException e){
            Ui.printInvalidTaskAttributeMessage();
            return this.startDate;
        }
    }

    /**
     * Set the time that the user have to finish working on the task
     *
     * @param endDate The time that the user have to finish working on the task
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the endDate attribute
     *
     * @return The time that the user have to finish working on the task
     */

    public String getEndDate() {
        try {
            if (this.endDate.isEmpty() || this.endDate == null) {
                throw new InvalidGetException();
            } else {
                return this.endDate;
            }
        } catch (InvalidGetException e){
            Ui.printInvalidTaskAttributeMessage();
            return this.endDate;
        }
    }

    /**
     * Set the task as a todo, deadline or event type of task based on the user command
     *
     * @param status Fragment of user input that indicate if the task is a todo, deadline or event
     */
    public void setTaskType(String status) {
        switch(status) {
            case "todo":
                this.taskType = "T";
                break;
            case "deadline":
                this.taskType = "D";
                break;
            case "event":
                this.taskType = "E";
                break;
            default:
                Ui.printInvalidTaskTypeMessage();
                break;
        }
    }

    /**
     * Returns the task type
     *
     * @return The task type
     */

    public String getTaskType() {
        try {
            if (this.taskType.isEmpty() || this.taskType == null) {
                throw new InvalidGetException();
            } else {
                return this.taskType;
            }
        } catch (InvalidGetException e){
            Ui.printInvalidTaskAttributeMessage();
            return this.taskType;
        }
    }
}

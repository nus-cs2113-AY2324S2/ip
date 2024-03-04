package humi;

/**
 * Represents the deadline task containing task type, mark, description,
 * and deadline
 */
public class DeadlineTask extends Task {
    public String deadline;
    DeadlineTask(String description, String deadline) {
        this.taskType = TaskType.DEADLINE;
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
        Ui.printAddDeadline(description, deadline);
    }

    DeadlineTask(String description, String deadline, boolean isDone) {
        this.taskType = TaskType.DEADLINE;
        this.description = description;
        this.isDone = isDone;
        this.deadline = deadline;
    }

    /**
     * Prints the full format of the deadline task
     */
    @Override
    public void print() {
        printTaskType();
        printMark();
        System.out.println(description + " by: " + this.deadline);
    }
}

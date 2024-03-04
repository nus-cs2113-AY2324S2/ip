package humi;

/**
 * Represents the task containing task type, mark, and description
 */
public class Task {
    public String description;
    public Boolean isDone;
    public TaskType taskType;

    Task() {
        this.description = "";
        this.isDone = false;
    }

    /**
     * Prints the formatted task type
     */
    public void printTaskType() {
        switch (taskType) {
        case TODO:
            System.out.print("[T]");
            break;
        case DEADLINE:
            System.out.print("[D]");
            break;
        case EVENT:
            System.out.print("[E]");
            break;
        }
    }

    /**
     * Prints the formatted mark
     */
    public void printMark() {
        String mark = (isDone) ? "[X] " : "[ ] ";
        System.out.print(mark);
    }

    /**
     * Prints the full format of the task
     */
    public void print() {
        printTaskType();
        printMark();
        System.out.println(description);
    }

    /**
     * Mark the task as done and print the message
     */
    public void mark() {
        isDone = true;
        System.out.println(Ui.LINE);
        System.out.print("     Nice! I've marked this task as done:\n     ");
        printTaskType();
        printMark();
        System.out.println(description);
        System.out.println(Ui.LINE);
    }

    /**
     * Mark the task as not done and print the message
     */
    public void unmark() {
        isDone = false;
        System.out.println(Ui.LINE);
        System.out.print("     OK, I've marked this task as not done yet:\n     ");
        printTaskType();
        printMark();
        System.out.println(description);
        System.out.println(Ui.LINE);
    }
}

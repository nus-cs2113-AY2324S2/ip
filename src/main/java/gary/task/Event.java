package gary.task;

/**
 * Event class deals with task with event as the type, performing functions specifically
 * for an event task.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.taskType = TaskType.EVENT;
        this.from = from;
        this.to = to;
    }

    /**
     * Take the task and get its event start to be returned.
     *
     * @return event starting (from).
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Take the task and get its event end to be returned.
     *
     * @return event end (to).
     */
    public String getTo() {
        return this.to;
    }

    /**
     * print message when user adds a task with event type.
     *
     * @param tasksCount number of tasks in the array list.
     */
    @Override
    public void printAdd(int tasksCount) {
        System.out.println("Got it! I've added this task: ");
        System.out.println("  [E][ ] "
                + this.getTaskDescription()
                + "(from: " + this.from + "to: " + this.to + ")");
        System.out.println("Now you have " + tasksCount + " tasks in your list.");
    }

    /**
     * print task in a certain format with its details, including the event description,
     * start (from), and end (to).
     *
     * @param taskNumber task number in the array list.
     */
    @Override
    public void printTask(int taskNumber) {
        System.out.println((taskNumber + 1)
                + ".[E]"
                + "[" + (this.getTaskStatus() ? "X" : " ") + "] "
                + this.getTaskDescription()
                + "(from: " + this.from + "to: " + this.to + ")");
    }
}

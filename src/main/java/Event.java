public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.taskType = TaskType.EVENT;
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    //function to print when user adds a task
    @Override
    public void printAdd(int todosCount) {
        System.out.println("Got it! I've added this task: ");
        System.out.println("  [E][ ] "
                + this.getTaskDescription()
                + "(from: " + this.from + "to: " + this.to + ")");
        System.out.println("Now you have " + todosCount + " tasks in your list.");
    }

    //function to print when user list tasks
    @Override
    public void printTask(int todoCount) {
        System.out.println((todoCount + 1)
                + ".[E]"
                + "[" + (this.getTaskStatus() ? "X" : " ") + "] "
                + this.getTaskDescription()
                + "(from: " + this.from + "to: " + this.to + ")");
    }
}

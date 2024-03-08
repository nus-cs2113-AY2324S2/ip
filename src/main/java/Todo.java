public class Todo extends Task{
    private boolean isComplete;
    public Todo(String task)
    {
        super(task);
    }
    public Todo(String task,boolean isComplete)
    {
        super(task,isComplete);
    }

    public String toString()
    {
        return "[T]" + super.toString();
    }

    /**
     * get the icon of TodoTask
     * @return Char of the task type icon
     */
    protected String getTaskType()
    {
        return "T";
    }

    /**
     * transfer todoTask to txt save format
     * @return String of todoTask
     */
    public String toFileFormat() {
        return String.format("%s | %d | %s", getTaskType(), this.isComplete() ? 1 : 0, this.getTask());
    }
}

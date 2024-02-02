public class DeadLineArguments extends CommandArguments {
    public String taskName;
    public String by;

    public DeadLineArguments(String taskName, String by) {
        this.taskName = taskName;
        this.by = by;
    }
}

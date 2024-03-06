public class UnmarkCommand implements Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.getTask(index).unmarkList();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(taskList.getTask(index).getStatusIcon() + " " + taskList.getTask(index).getDescription());
    }
}


public class MarkCommand implements Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.getTask(index).markList();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskList.getTask(index).getStatusIcon() + " " + taskList.getTask(index).getDescription());
    }
}


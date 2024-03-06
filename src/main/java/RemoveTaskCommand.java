public class RemoveTaskCommand implements Command{
    private final int index;

    public RemoveTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) {
        Task currentTask = taskList.getTask(index);
        taskList.removeTask(index);
        System.out.println("Noted. I've removed this task:\n" + " " + currentTask +
                "\nNow you have " + taskList.size() +" tasks " + "in the list.\n");
    }
}


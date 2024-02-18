public class UnmarkCommand implements Command {
    private final String INDEX_TASK;

    public UnmarkCommand(String indexTask) {
        this.INDEX_TASK = indexTask;
    }

    @Override
    public void execute (String task, TaskList taskList) {
        int index = Integer.parseInt(INDEX_TASK) - 1;
        Task taskToMark = taskList.taskList.get(index);
        taskToMark.markAsNotDone();
        Ui.printUnmarkTask(index, taskList);
    }
}

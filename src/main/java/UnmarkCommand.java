public class UnmarkCommand implements Command {
    private String indexTask;

    public UnmarkCommand(String indexTask) {
        this.indexTask = indexTask;
    }

    @Override
    public void execute (String task, TaskList taskList) {
        int index = Integer.parseInt(indexTask) - 1;
        Task taskToMark = taskList.taskList.get(index);
        taskToMark.markAsNotDone();
        Ui.printUnmarkTask(index, taskList);
    }
}

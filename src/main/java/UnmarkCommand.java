public class UnmarkCommand implements Command {
    private String indexTask;

    public UnmarkCommand(String indexTask) {
        this.indexTask = indexTask;
    }

    @Override
    public void execute (String task, Task[] taskList) {
        int index = Integer.parseInt(indexTask) - 1;
        Task taskToMark = taskList[index];
        taskToMark.markAsNotDone();
        Ui.printMarkTask(index, taskList);
    }
}

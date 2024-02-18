public class MarkCommand implements Command {

    private int indexTask;

    public MarkCommand(int indexTask) {
        this.indexTask = indexTask;
    }

    @Override
    public void execute (String task, TaskList taskList) {
        int index = indexTask - 1;
        Task taskToMark = taskList.taskList.get(index);
        taskToMark.markAsDone();
        Ui.printMarkTask(index, taskList);
    }
}

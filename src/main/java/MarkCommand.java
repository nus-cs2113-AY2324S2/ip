public class MarkCommand implements Command {

    private String indexTask;

    public MarkCommand(String indexTask) {
        this.indexTask = indexTask;
    }

    @Override
    public void execute (String task, Task[] taskList) {
        int index = Integer.parseInt(indexTask) - 1;
        Task taskToMark = taskList[index];
        taskToMark.markAsDone();
        Ui.printMarkTask(index, taskList);
    }
}

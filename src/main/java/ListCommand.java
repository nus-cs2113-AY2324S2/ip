public class ListCommand implements Command {
    @Override
    public void execute(String task, Task[] taskList) {
        Ui.listTask(taskList);
    }
}

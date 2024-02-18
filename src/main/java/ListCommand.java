public class ListCommand implements Command {
    @Override
    public void execute(String task, TaskList taskList) {
        Ui.listTask(taskList);
    }
}

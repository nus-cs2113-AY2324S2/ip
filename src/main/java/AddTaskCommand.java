public class AddTaskCommand implements Command {
    private final String[] taskDescriptions;

    public AddTaskCommand(String[] taskDescriptions) {
        this.taskDescriptions = taskDescriptions;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(new TaskGenerator().apply(taskDescriptions).orElseThrow());
        System.out.println("added: " + tasks.getTask(tasks.size()));
    }
}

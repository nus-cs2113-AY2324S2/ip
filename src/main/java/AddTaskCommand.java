public class AddTaskCommand implements Command {
    private final String[] taskDescriptions;

    public AddTaskCommand(String[] taskDescriptions) {
        this.taskDescriptions = taskDescriptions;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(new TaskGenerator().apply(taskDescriptions).orElseThrow());
        System.out.println(Message.ADD_TASK_MESSAGE_FRONT
                + tasks.getTask(tasks.size())
                + Message.ADD_TASK_MESSAGE_MIDDLE
                + tasks.size()
                + Message.ADD_TASK_MESSAGE_END);
    }
}

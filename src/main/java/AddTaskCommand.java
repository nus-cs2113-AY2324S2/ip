public class AddTaskCommand implements Command {
    private final Parser token;
    private final String[] taskDescriptions;

    public AddTaskCommand(Parser token, String[] taskDescriptions) {
        this.token = token;
        this.taskDescriptions = taskDescriptions;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(token, taskDescriptions);
        System.out.println(Message.ADD_TASK_MESSAGE_FRONT
                + tasks.getTask(tasks.size())
                + Message.ADD_TASK_MESSAGE_MIDDLE
                + tasks.size()
                + Message.ADD_TASK_MESSAGE_END);
    }
}

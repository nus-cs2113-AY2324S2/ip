public class AddTaskCommand implements Command {
    private final String[] taskDescriptions;

    public AddTaskCommand(String[] taskDescriptions) {
        this.taskDescriptions = taskDescriptions;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(TaskGenerator.generate(taskDescriptions));
        System.out.println(Message.ADD_TASK_MESSAGE_FRONT
                + tasks.getTask(tasks.size())
                + Message.ADD_TASK_MESSAGE_MIDDLE
                + tasks.size()
                + Message.ADD_TASK_MESSAGE_END);
    }
}

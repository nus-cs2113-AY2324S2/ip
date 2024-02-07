public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks) {
        System.out.println(Message.LIST_MESSAGE_FRONT);
        tasks.displayAll();
        System.out.println(Message.LIST_MESSAGE_END);
    }
}

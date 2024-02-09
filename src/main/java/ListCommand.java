public class ListCommand implements Command {
    @Override
    public boolean execute(TaskList tasks) {
        if (tasks.size() > 0) {
            System.out.println(Message.LIST_MESSAGE_FRONT);
            tasks.displayAll();
            System.out.println(Message.LIST_MESSAGE_END);
        }
        return false;
    }
}

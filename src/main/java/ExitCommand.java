public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks) {
        System.out.print(Message.EXIT_MESSAGE);
        System.exit(0);
    }
}

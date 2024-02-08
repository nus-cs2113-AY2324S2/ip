public class ToggleStatusCommand implements Command {
    private final int index;
    private final String command;

    public ToggleStatusCommand(String input) {
        String[] splitInput = input.split(" ");
        this.command = splitInput[0];
        this.index = Integer.parseInt(splitInput[1]);
    }

    @Override
    public void execute(TaskList tasks) {
        if (index > 0 && index <= tasks.size()) {
            tasks.getTask(index).setIsDone(command.startsWith("mark"));
            System.out.println(toggleMessage(tasks.getTask(index)));
        }
    }

    private String toggleMessage(Task task) {
        String message = "";

        message += task.isDone() ? Message.MARK_MESSAGE : Message.UNMARK_MESSAGE;
        message += "   " + task;

        return message;
    }
}

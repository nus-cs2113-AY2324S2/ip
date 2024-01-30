public class ToggleStatusCommand implements Command {
    private final int index;

    public ToggleStatusCommand(String input) {
        this.index = Integer.parseInt(input.split(" ")[1]);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.getTask(index).toggleIsDone();
        System.out.println(toggleMessage(tasks.getTask(index)));
    }

    private String toggleMessage(Task task) {
        String message = "";

        if (task.isDone) {
            message += "Nice! I've marked this task as done:\n";
        } else {
            message += "OK, I've marked this task as not done yet:\n";
        }
        message += "   [" + task.getStatusIcon() + "] ";
        message += task.description;

        return message;
    }
}

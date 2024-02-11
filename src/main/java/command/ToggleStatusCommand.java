package command;

import task.Task;
import task.TaskList;
import ui.Message;

public class ToggleStatusCommand implements Command {
    private final int index;
    private final String command;

    public ToggleStatusCommand(String[] inputArguments) {
        this.command = inputArguments[0];
        this.index = Integer.parseInt(inputArguments[1]);
    }

    @Override
    public boolean execute(TaskList tasks) {
        if (index > 0 && index <= tasks.size()) {
            tasks.getTask(index).setIsDone(command.equals("mark"));
            System.out.println(toggleMessage(tasks.getTask(index)));
        }
        return false;
    }

    private String toggleMessage(Task task) {
        return (task.isDone() ? Message.MARK_MESSAGE : Message.UNMARK_MESSAGE)
                + "   " + task;
    }
}

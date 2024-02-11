package command;

import exception.AdamException;
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
    public boolean execute(TaskList tasks) throws AdamException {
        try {
            tasks.getTask(index).setIsDone(command.equals("mark"));
            System.out.println(toggleMessage(tasks.getTask(index)));
        } catch (IndexOutOfBoundsException error) {
            throw new AdamException(Message.getToggleErrorMessage(tasks.size()));
        }

        return false;
    }

    private String toggleMessage(Task task) {
        return (task.isDone() ? Message.MARK_MESSAGE : Message.UNMARK_MESSAGE)
                + "   " + task;
    }
}

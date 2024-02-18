package command;

import exception.AdamException;
import task.TaskList;
import ui.Message;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(String[] inputArguments) {
        this.index = Integer.parseInt(inputArguments[0]);
    }

    @Override
    public boolean execute(TaskList tasks) throws AdamException {
        try {
            System.out.println(Message.getDeleteMessage(tasks.size() - 1, tasks.getTask(index).toString()));
            tasks.deleteTask(index);
        } catch (IndexOutOfBoundsException error) {
            throw new AdamException(Message.getListInquiryErrorMessage(tasks.size()));
        }
        return false;
    }
}

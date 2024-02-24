package command;

import commandexceptions.InvalidDeadlineCommandException;
import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.Deadline;
import tasktype.Task;
import tasktype.TaskList;
import ui.Ui;

public class DeadlineCommand implements Command {
    private final String userInput;

    public DeadlineCommand(String description) {
        userInput = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions {
        String[] deadlineDescription = userInput.split("/by");
        if(deadlineDescription.length != 2) {
            String invalidDeadline = "Use: deadline (Deadline description) + /by + (date)";
            throw new JingHaoExceptions(invalidDeadline);
        }
        String description = deadlineDescription[0];
        String date = deadlineDescription[1];
        Task newDeadline = new Deadline(description, date);
        taskList.add(newDeadline);
        System.out.println("Got it. I've added this task:\n " + newDeadline);
        ui.printTotalTask(taskList.size());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

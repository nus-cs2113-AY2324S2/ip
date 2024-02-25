package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.Deadline;
import tasktype.Task;
import tasktype.TaskList;
import ui.Ui;

/**
 * Represents the command to add a deadline task.
 */
public class DeadlineCommand implements Command {
    private final String userInput;

    /**
     * Constructs a DeadlineCommand with the user's input.
     *
     * @param description The description of the task from the user.
     */
    public DeadlineCommand(String description) {
        userInput = description;
    }

    /**
     * Executes the command by creating a Deadline object and adding the deadline task to the existing task list.
     * Displays deadline task to be added and the corresponding status on the screen after executing the command.
     *
     * @param taskList The list of task in the JingHao chatbot.
     * @param ui The user interface of the JingHao chatbot.
     * @param storage The file storage of the JingHao chatbot.
     * @throws JingHaoExceptions If user input does not satisfy the required format.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions {
        boolean isFromFile = false;
        String[] deadlineDescription = userInput.split("/by");
        if(deadlineDescription.length != 2) {
            String invalidDeadline = "Use: deadline (Deadline description) + /by + (YYYY-MM-DD)";
            throw new JingHaoExceptions(invalidDeadline);
        }
        String description = deadlineDescription[0].trim();
        String date = deadlineDescription[1].trim();
        Task newDeadline = new Deadline(description, date, isFromFile);
        taskList.add(newDeadline);
        System.out.println("Got it. I've added this task:\n " + newDeadline);
        ui.printTotalTask(taskList.size());
    }

    /**
     * Determines whether the command is an exit command.
     *
     * @return Returns false since this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

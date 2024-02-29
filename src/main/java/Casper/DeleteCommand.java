package Casper;


/**
 * An implementation of <code>Command</code> focusing on commands that deletes a task from the list.
 */
public class DeleteCommand extends Command{
    private String userInput;
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    public DeleteCommand(boolean isRunning, String userInput){
        super(isRunning);
        this.userInput = userInput;
    }

    /**
     * Executes deleting a task to the given <code>TaskList</code>, and updates the save file.
     *
     * @param ui An instance of <code>Ui</code>.
     * @param tasks An instance of <code>TaskList</code>.
     * @param storage An instance of <code>Storage</code>.
     */
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;

        deleteTask(userInput);
        storage.handleSaveFile(tasks);
    }

    private void deleteTask(String userInput){
        try{
            int targetTaskNumber = tasks.validateTargetedInput(userInput);
            Task taskToRemove = tasks.getTask(targetTaskNumber-1);
            tasks.removeTask(targetTaskNumber-1);
            ui.echoDeleteTask(taskToRemove, tasks.getTaskNumber());
        } catch (CasperInvalidInputException e){
            ui.wrapEchoMessage(e.getExceptionNote());
        }
    }
}

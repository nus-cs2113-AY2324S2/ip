package Casper;

public class DeleteCommand extends Command{
    private String userInput;
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    public DeleteCommand(boolean isRunning, String userInput){
        super(isRunning);
        this.userInput = userInput;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;

        deleteTask(userInput);
        storage.handleSaveFile(tasks);
    }

    public void deleteTask(String userInput){
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

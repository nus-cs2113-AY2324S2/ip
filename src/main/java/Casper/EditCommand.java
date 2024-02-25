package Casper;

/**
 * An implementation of <code>Command</code> focusing on commands that edits the status of tasks or
 * lists tasks (i.e. list, find).
 */
public class EditCommand extends Command{
    private String prefix;
    private String userInput;
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    public EditCommand(boolean isRunning, String prefix, String userInput){
        super(isRunning);
        this.prefix = prefix;
        this.userInput = userInput;
    }

    /**
     * Executes the marking/un-marking of tasks, or any task that involves listing out tasks.
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
        switch(prefix){
        case "mark":
        case "unmark":
            editTaskStatus();
            break;
        case "list":
            echoTasks();
            break;
        case "find":
            handleFind();
            break;
        }
        storage.handleSaveFile(tasks);
    }

    private void handleFind(){
        String keyword = getFindKeyword(userInput);
        TaskList matchingTasks = new TaskList();
        for(Task task : tasks.getTaskList()){
            if(task.description.contains(keyword)){
                matchingTasks.addTask(task);
            }
        }
        ui.printLine();
        matchingTasks.echoFoundTasks();
        ui.printLine();
    }

    private static String getFindKeyword(String userInput) {
        int keywordIndex = userInput.indexOf("find") + "find".length();
        return userInput.substring(keywordIndex).trim();
    }

    private void editTaskStatus(){
        try{
            tasks.handleMarkTask(userInput);
        } catch (CasperInvalidInputException e){
            ui.wrapEchoMessage(e.getExceptionNote());
        }
    }

    private void echoTasks(){
        ui.printLine();
        tasks.echoTaskList();
        ui.printLine();
    }
}

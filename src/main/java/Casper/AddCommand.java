package Casper;

public class AddCommand extends Command{
    private String userInput;
    private String prefix;
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    public AddCommand(boolean isRunning, String prefix, String userInput){
        super(isRunning);
        this.userInput = userInput;
        this.prefix = prefix;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
        switch (prefix){
            case "event":
                handleEvent(userInput);
                break;
            case "deadline":
                handleDeadline(userInput);
                break;
            case "todo":
                handleTodo(userInput);
                break;
        }
        storage.handleSaveFile(tasks);
    }
    private static Task getEvent(String userInput) throws StringIndexOutOfBoundsException {
        int fromIndex = userInput.indexOf("/from") + "/from".length();
        int toIndex = userInput.indexOf("/to") + "/to".length();
        int descIndex = userInput.indexOf("event")+"event".length();

        boolean isValidEventInput = fromIndex < descIndex || toIndex < descIndex;
        if (isValidEventInput) {
            throw new StringIndexOutOfBoundsException();
        }

        String eventDesc = userInput.substring(descIndex, userInput.indexOf("/from")).trim();
        String from = userInput.substring(fromIndex, userInput.indexOf("/to")).trim();
        String to = userInput.substring(toIndex).trim();
        return new Event(eventDesc, from, to);
    }

    public void handleEvent(String userInput) {
        try{
            Task newTask = getEvent(userInput);
            tasks.addTask(newTask);
            ui.echoAddedTask(newTask, tasks.getTaskNumber());
        } catch (StringIndexOutOfBoundsException e){
            ui.echoUnrecognizedEvent();
        }
    }
    private static Task getDeadline(String userInput) throws StringIndexOutOfBoundsException {
        int byIndex = userInput.indexOf("/by") + "/by".length();
        int descIndex = userInput.indexOf("deadline")+"deadline".length();

        boolean isValidDeadlineInput = byIndex < descIndex;
        if (isValidDeadlineInput) {
            throw new StringIndexOutOfBoundsException();
        }

        String deadlineDesc = userInput.substring(descIndex, userInput.indexOf("/by")).trim();
        String by = userInput.substring(byIndex).trim();
        return new Deadline(deadlineDesc, by);
    }

    public void handleDeadline(String userInput){
        try{
            Task newTask = getDeadline(userInput);
            tasks.addTask(newTask);
            ui.echoAddedTask(newTask, tasks.getTaskNumber());
        } catch (StringIndexOutOfBoundsException e) {
            ui.echoUnrecognizedDeadline();
        }
    }

    private static Task getTodo(String userInput) throws CasperEmptyTodoException {
        int descIndex = userInput.indexOf("todo")+"todo".length();
        String todoDesc = userInput.substring(descIndex).trim();
        if(todoDesc.isEmpty()){
            throw new CasperEmptyTodoException();
        }
        return new Todo(todoDesc);
    }

    public void handleTodo(String userInput) {
        try{
            Task newTask = getTodo(userInput);
            tasks.addTask(newTask);
            ui.echoAddedTask(newTask, tasks.getTaskNumber());
        } catch (CasperEmptyTodoException e){
            ui.echoUnrecognizedTodo();
        }
    }
}

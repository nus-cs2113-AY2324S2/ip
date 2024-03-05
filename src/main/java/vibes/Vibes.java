package vibes;

import vibes.exception.CommandNotFoundException;
import vibes.exception.InvalidArgumentException;
import vibes.parser.Parser;
import vibes.storage.Storage;
import vibes.task.TaskList;
import vibes.ui.TextUi;

import java.io.FileNotFoundException;

public class Vibes {

    public static final String INVALID_COMMAND_MESSAGE = "\t Invalid Command. Please choose between: todo, deadline, event, mark, unmark, delete, and bye";
    //    private Storage storage;
    private final TaskList taskList;
    private final TextUi ui;
    private boolean isExit;

    public Vibes(String filePath){
        this.ui = new TextUi();
//        storage = new Storage(filePath);
//        try{
//            taskList = new TaskList(storage.load());
//        } catch (VibesException e){
//            ui.showLoadingError();
//            taskList = new TaskList();
//        }
        this.taskList = new TaskList();
        this.isExit = false;
        try {
            Storage.loadTasks(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }
    }

    private void run() {
        ui.showWelcomeMessage();
        while (!isExit){
            try {
                String userInput = ui.readCommand();
                ui.showLine();
                String commandToExecute = Parser.parseCommand(userInput);
                executeCommand(commandToExecute, userInput);
            } catch (CommandNotFoundException e) {
                ui.showError(INVALID_COMMAND_MESSAGE);
            } catch (InvalidArgumentException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public void executeCommand(String commandToExecute, String userInput) throws CommandNotFoundException, InvalidArgumentException {
        switch (commandToExecute) {
        case "bye":
            System.out.println("\t Bye. Hope to see you again soon!");
            isExit = true;
            break;
        case "list":
            ui.listTasks(taskList.tasks);
            break;
        case "mark":
            taskList.setAsDone(Parser.parseTaskNumber(userInput), ui);
            break;
        case "unmark":
            taskList.setAsNotDone(Parser.parseTaskNumber(userInput), ui);
            break;
        case "todo":
            taskList.addTodo(Parser.parseTodo(userInput));
            ui.showTaskAddedMessage(taskList.tasks);
            break;
        case "event":
            taskList.addEvent(Parser.parseEvent(userInput));
            ui.showTaskAddedMessage(taskList.tasks);
            break;
        case "deadline":
            taskList.addDeadline(Parser.parseDeadline(userInput));
            ui.showTaskAddedMessage(taskList.tasks);
            break;
        case "delete":
            taskList.deleteTask(Parser.parseTaskNumber(userInput), ui);
            break;
        default:
            throw new CommandNotFoundException();
        }
        Storage.saveTask(taskList);
    }

    public static void main(String[] args) {
        new Vibes ("").run();
    }
}
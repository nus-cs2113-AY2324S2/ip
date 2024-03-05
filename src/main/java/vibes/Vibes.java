package vibes;

import vibes.common.CommandTypes;
import vibes.common.Messages;
import vibes.exception.CommandNotFoundException;
import vibes.exception.InvalidArgumentException;
import vibes.parser.Parser;
import vibes.storage.Storage;
import vibes.task.TaskList;
import vibes.ui.TextUi;
import java.io.FileNotFoundException;

public class Vibes {

    private final Storage storage;
    private TaskList taskList;
    private final TextUi ui;
    private boolean isExit;

    public Vibes(){
        this.ui = new TextUi();
        this.storage = new Storage();
        this.isExit = false;
        try{
            this.taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e){
            ui.showError(Messages.FILE_NOT_FOUND_MESSAGE);
            this.taskList = new TaskList();
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
                ui.showError(Messages.INVALID_COMMAND_MESSAGE);
            } catch (InvalidArgumentException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public void executeCommand(String commandToExecute, String userInput) throws CommandNotFoundException, InvalidArgumentException {
        switch (commandToExecute) {
        case CommandTypes.COMMAND_BYE:
            ui.showByeMessage();
            isExit = true;
            break;
        case CommandTypes.COMMAND_LIST:
            ui.listTasks(taskList.tasks);
            break;
        case CommandTypes.COMMAND_MARK:
            taskList.setAsDone(Parser.parseTaskNumber(userInput), ui);
            break;
        case CommandTypes.COMMAND_UNMARK:
            taskList.setAsNotDone(Parser.parseTaskNumber(userInput), ui);
            break;
        case CommandTypes.COMMAND_TODO:
            taskList.addTodo(Parser.parseTodo(userInput));
            ui.showTaskAddedMessage(taskList.tasks);
            break;
        case CommandTypes.COMMAND_EVENT:
            taskList.addEvent(Parser.parseEvent(userInput));
            ui.showTaskAddedMessage(taskList.tasks);
            break;
        case CommandTypes.COMMAND_DEADLINE:
            taskList.addDeadline(Parser.parseDeadline(userInput));
            ui.showTaskAddedMessage(taskList.tasks);
            break;
        case CommandTypes.COMMAND_DELETE:
            taskList.deleteTask(Parser.parseTaskNumber(userInput), ui);
            break;
        default:
            throw new CommandNotFoundException();
        }
        storage.saveTask(taskList);
    }

    public static void main(String[] args) {
        new Vibes().run();
    }
}
package dukeRobot.Commands;
import dukeRobot.Tools.*;
import dukeRobot.Tasks.*;
import java.io.*;
import java.util.InputMismatchException;

public class CommandBinding {
    private Command command;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    public CommandBinding(Parser parser,TaskList tasks, Ui ui, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
        String keyWord = parser.getKeyWord();
        try {
            switch (keyWord) {
                case "deadline":
                    this.command = new AddCommand(parser);
                    break;
                case "event":
                    this.command = new AddCommand(parser);
                    break;
                case "todo":
                    this.command = new AddCommand(parser);
                    break;
                case "list":
                    this.command = new StatusCommand(parser);
                    break;
                case "mark":
                    this.command = new StatusCommand(parser);
                    break;
                case "unmark":
                    this.command = new StatusCommand(parser);
                    break;
                case "find":
                    this.command = new StatusCommand(parser);
                    break;
                case "delete":
                    this.command = new DeleteCommand(parser);
                    break;
                case "bye":
                    this.command = new ExitCommand(parser);
                    break;
                default:
                    this.command = null;
                    throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
                ui.inputMismatchError();
        }
    }

    public void execute() throws IOException {
        this.command.execute(tasks, ui, storage);
    }

    public Command getCommand() {
        return this.command;
    }
}



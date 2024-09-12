package dukeRobot.Commands;
import dukeRobot.Tools.*;
import dukeRobot.Tasks.*;
import java.io.*;
import java.util.InputMismatchException;

/**
 * represent the allocation of commands given different keywords provided by the parser.
 * A<code>CommandBinding</code> represent command that is binded and will be executed.
 */
public class CommandBinding {
    private Command command;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     *
     * @param parser parses the commands, find the keyword of each command
     * @param tasks record the task up-to-date
     * @param ui manipulates the user interactions
     * @param storage manipulates the file loading and updating.
     */
    public CommandBinding(Parser parser,TaskList tasks, Ui ui, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
        String keyWord = parser.getKeyWord();
        switch (keyWord) {
            case "deadline":
            case "todo":
            case "event":
                this.command = new AddCommand(parser);
                break;

            case "list":
            case "help":
            case "find":
            case "unmark":
            case "mark":
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
        }
    }

    public void execute() throws NullPointerException {
        this.command.execute(tasks, ui, storage);
    }

    public Command getCommand() {
        return this.command;
    }
}



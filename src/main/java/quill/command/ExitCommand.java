package quill.command;

import quill.storage.Save;
import quill.task.TaskList;
import quill.ui.TextUi;

/**
 * The ExitCommand Class represents a command for exiting from Quill application.
 * It extends the Command class and includes specific behavior exiting the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs the ExitCommand object with the specified commandWord and parameter.
     *
     * @param commandWord The command word.
     * @param parameter The user input excluding the command word.
     */
    public ExitCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    /**
     * Executes the ExitCommand to exit the application.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        Save.writeToFile(tasks);
        TextUi.showGoodbyeMessage();
    }

    /**
     * Checks if the command is ExitCommand.
     *
     * @param command The command word.
     * @return True if command is ExitCommand, false otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

}

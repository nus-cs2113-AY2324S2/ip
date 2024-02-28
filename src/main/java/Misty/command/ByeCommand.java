package misty.command;

import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;

/**
 * Exits chatbot.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_STRING = "bye";

    /**
     * Constructs ByeCommand object.
     */
    public ByeCommand() {
    }

    /**
     * Executes bye command and exits program.
     *
     * @param taskList TaskList object containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        userUi.printByeMessage();
        userUi.printMessageBorder();
        System.exit(0);
    };
}
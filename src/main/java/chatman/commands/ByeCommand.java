package chatman.commands;

import chatman.exceptions.UnnecessaryValueException;

/**
 * Implements functionality to enable ChatMan response to user-entered "bye" command.
 *
 * @author LWachtel1
 * */
public class ByeCommand extends Command{

    /**
     * Constructor for ByeCommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from CommandParser object) to use in perform()
     * method.
     * */
    public ByeCommand(String userCommand) {
        super(userCommand);
    }

    /**
     * Prints goodbye message for ChatMan user; called if they enter "bye" command with no arguments.
     *
     * @throws UnnecessaryValueException If "bye" command entered with any additional arguments.
     */
    @Override
    public void perform() throws UnnecessaryValueException {
        String[] fullCommand= userCommand.split(" ");
        if (fullCommand.length > 1) {
            throw new UnnecessaryValueException();
        }

        System.out.printf("%s%n", "____________________________________________________________");
        System.out.printf("%s%n", "Bye. Hope to see you again soon!");
        System.out.printf("%s%n", "____________________________________________________________");
    }
}

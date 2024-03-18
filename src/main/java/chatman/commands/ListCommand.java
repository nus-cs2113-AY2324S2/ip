package chatman.commands;

import chatman.ChatMan;
import chatman.exceptions.UnnecessaryValueException;

/**
 * Implements ChatMan's ability to list all currently stored tasks.
 *
 * @author LWachtel1
 * */
public class ListCommand extends Command{

    /**
     * Constructor for ListCommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from CommandParser object) to use in perform()
     * method.
     * */
    public ListCommand(String userCommand) {
        super(userCommand);
    }


    /**
     * Prints String returned by object's respective toString() method call for each object in task arraylist.
     *
     * @throws UnnecessaryValueException If ListCommand object provided with any additional arguments.
     * */
    @Override
    public void perform() throws UnnecessaryValueException {
        String[] fullCommand = userCommand.split(" ");
        if (fullCommand.length > 1) {
            throw new UnnecessaryValueException();
        }

        System.out.printf("%s%n", "____________________________________________________________");
        for (int i = 0; i < ChatMan.storedTasks.size(); i++) {
            System.out.printf("%d.%s%n", (i + 1), ChatMan.storedTasks.get(i).toString());
        }
    }
}

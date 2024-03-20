package chatman.commands;

import chatman.ChatMan;
import chatman.exceptions.EmptyListException;
import chatman.exceptions.IncorrectArgumentNumException;

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
     * @throws IncorrectArgumentNumException If ListCommand object provided with any additional arguments.
     * @throws EmptyListException If list of stored tasks is currently empty.
     * */
    @Override
    public void perform() throws IncorrectArgumentNumException, EmptyListException {
        String[] fullCommand = userCommand.split(" ");
        if (fullCommand.length > 1) {
            String erroneousInput = userCommand.split(" ",2)[1];
            throw new IncorrectArgumentNumException("LIST", erroneousInput);
        }

        if (ChatMan.accessTasks().isEmpty()) {
            throw new EmptyListException("LIST", userCommand);
        }

        System.out.printf("%s%n", "____________________________________________________________");
        for (int i = 0; i < ChatMan.accessTasks().size(); i++) {
            System.out.printf("%d.%s%n", (i + 1), ChatMan.accessTasks().get(i).toString());
        }
    }
}

package chatman.commands;

import chatman.exceptions.EmptyListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.utility.Tasklist;
import chatman.utility.Ui;

/**
 * Implements ChatMan's ability to list all currently stored tasks.
 *
 * @author LWachtel1
 * */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from Parser object) to use in perform()
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

        if (Tasklist.isTasklistEmpty()) {
            throw new EmptyListException("LIST", userCommand);
        }

        System.out.printf("%s%n", Ui.getChatbotSeparator());
        for (int i = 0; i < Tasklist.getSize(); i++) {
            System.out.printf("%d.%s%n", (i + 1), Tasklist.getTask(i).toString());
        }
    }
}

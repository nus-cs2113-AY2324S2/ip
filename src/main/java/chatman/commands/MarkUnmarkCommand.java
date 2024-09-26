package chatman.commands;

import chatman.exceptions.EmptyListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.exceptions.IncorrectIndexException;
import chatman.utility.Tasklist;
import chatman.utility.Ui;

/**
 * Implements ChatMan's ability to mark/unmark any currently stored task.
 *
 * @author LWachtel1
 * */
public class MarkUnmarkCommand extends Command {

    /**
     * Constructor for MarkUnmarkCommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from Parser object) to use in perform()
     * method.
     * */
    public MarkUnmarkCommand(String userCommand) {
        super(userCommand);
    }

    /**
     * Parses mark/unmark command into command and index to mark/unmark, then accesses task object at given index
     * (if valid) & calls instance method setDone() with true or false argument depending on choice of mark or unmark.
     *
     * @throws IncorrectArgumentNumException If command provided with incorrect number of arguments.
     * @throws IncorrectIndexException If command provided with non-numerical argument or index outside of bounds
     * of task arraylist.
     * @throws EmptyListException If list of stored tasks is currently empty.
     * */
    @Override
    public void perform() throws IncorrectArgumentNumException, IncorrectIndexException, EmptyListException {
        String[] markUnmarkCommand = userCommand.split(" ");

        //Checks for only mark/unmark command & 1 argument being present
        if (markUnmarkCommand.length != 2) {
            throw new IncorrectArgumentNumException("MARKUNMARK", userCommand);
        }

        if (Tasklist.isTasklistEmpty()) {
            throw new EmptyListException("MARKUNMARK", userCommand);
        }

        String position = markUnmarkCommand[1];
        int storagePosition = 0;

        try {
            storagePosition = Integer.parseInt(position);
            if (storagePosition > Tasklist.getSize() || storagePosition <= 0) {
                throw new IncorrectIndexException("MARKUNMARK",position);
            }
        } catch(NumberFormatException exception) {
            throw new IncorrectIndexException("MARKUNMARK",position);
        }

        int storageIndex = storagePosition - 1;

        switch (markUnmarkCommand[0].toUpperCase()) {
        case "MARK":
            Tasklist.getTask(storageIndex).setDone(true);
            System.out.printf("%s%n", Ui.getChatbotSeparator());
            System.out.printf("Nice! I've marked this task as done:%n%s%n",
                   Tasklist.getTask(storageIndex).toString());
            break;

        case "UNMARK":
            Tasklist.getTask(storageIndex).setDone(false);
            System.out.printf("%s%n", Ui.getChatbotSeparator());
            System.out.printf("OK, I've marked this task as not done yet:%n%s%n",
                    Tasklist.getTask(storageIndex).toString());
            break;
        }

    }
}


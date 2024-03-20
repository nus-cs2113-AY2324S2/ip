package chatman.commands;

import chatman.ChatMan;
import chatman.exceptions.EmptyListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.exceptions.IncorrectMarkUnmarkException;

/**
 * Implements ChatMan's ability to mark/unmark any currently stored task.
 *
 * @author LWachtel1
 * */
public class MarkUnmarkCommand extends Command {

    /**
     * Constructor for MarkUnmarkCommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from CommandParser object) to use in perform()
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
     * @throws IncorrectMarkUnmarkException If command provided with non-numerical argument or index outside of bounds
     * of task arraylist.
     * @throws EmptyListException If list of stored tasks is currently empty.
     * */
    @Override
    public void perform() throws IncorrectArgumentNumException, IncorrectMarkUnmarkException, EmptyListException {
        String[] markUnmarkCommand = userCommand.split(" ");

        if (markUnmarkCommand.length != 2) {
            throw new IncorrectArgumentNumException();
        }

        if (ChatMan.accessTasks().isEmpty()) {
            throw new EmptyListException();
        }

        String position = markUnmarkCommand[1];
        int storageIndex = 0;

        try {
            storageIndex = Integer.parseInt(position) - 1;
            if (storageIndex + 1 > ChatMan.accessTasks().size() || storageIndex <= 0) {
                throw new IncorrectMarkUnmarkException();
            }
        } catch(NumberFormatException exception){
            throw new IncorrectMarkUnmarkException();
        }

        switch (markUnmarkCommand[0].toUpperCase()) {
        case "MARK":
            ChatMan.accessTasks().get(storageIndex).setDone(true);
            System.out.printf("%s%n", "____________________________________________________________");
            System.out.printf("Nice! I've marked this task as done:%n%s%n",
                   ChatMan.accessTasks().get(storageIndex).toString());
            break;

        case "UNMARK":
            ChatMan.accessTasks().get(storageIndex).setDone(false);
            System.out.printf("%s%n", "____________________________________________________________");
            System.out.printf("OK, I've marked this task as not done yet:%n%s%n",
                    ChatMan.accessTasks().get(storageIndex).toString());
            break;
        }

    }
}


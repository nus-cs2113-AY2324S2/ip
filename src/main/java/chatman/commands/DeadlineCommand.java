package chatman.commands;

import chatman.ChatMan;
import chatman.exceptions.FullListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.tasks.Deadline;

/**
 * Implements functionality to enable ChatMan response to user-entered "deadline DESCRIPTION /by  DEADLINE" command.
 *
 * @author LWachtel1
 * */
public class DeadlineCommand extends TaskCommand{

    /**
     * Constructor for DeadlineCommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from CommandParser object) to use in perform()
     * method.
     * */
    public DeadlineCommand(String userCommand) {
        super(userCommand);
    }

    /**
     * Parses deadline command into its arguments, description and date-time Strings, then instantiates new Deadline
     * object and adds this to task arraylist.
     *
     * @throws FullListException If task arraylist size equals MAX_NUM_TASKS when attempting to add new Deadline object.
     * @throws IncorrectArgumentNumException If command provided with incorrect number of arguments.
     * */
    public void perform() throws FullListException, IncorrectArgumentNumException{
        String[] deadLineCommand = userCommand.split(" ",1);
        if (deadLineCommand.length != 2) {
            throw new IncorrectArgumentNumException();
        }

        deadLineCommand = userCommand.split("/");
        if (deadLineCommand.length != 2) {
            throw new IncorrectArgumentNumException();
        }

        if (ChatMan.storedTasks.size() == ChatMan.MAX_NUM_TASKS) {
            throw new FullListException();
        }

        String deadLineDesc = deadLineCommand[0].replaceAll("(?i)DEADLINE", "");
        String by = deadLineCommand[1].replaceAll("(?i)BY", "").stripLeading();

        ChatMan.storedTasks.add(new Deadline(deadLineDesc, by));

        super.replyAddedTask();
    }

}

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
        String[] deadLineCommand = userCommand.split("/",2);

        if (deadLineCommand.length != 2) {
            throw new IncorrectArgumentNumException();
        }

        //checks for any additional / which would mean unnecessary arguments
        int argumentCount = 0;
        for (String argument: deadLineCommand) {
            //removes leading and trailing whitespace to prevent failure of "by" guard clause & date argument extraction
            deadLineCommand[argumentCount] = deadLineCommand[argumentCount].trim();
            if (argument.contains("/")) {
                throw new IncorrectArgumentNumException();
            }
            argumentCount++;
        }

        if (!deadLineCommand[1].startsWith("by")) {
            //replace with IncorrectFormatException
            throw new IncorrectArgumentNumException();
        }

        /*
        String[] deadLineCommand = userCommand.split(" ",1);


        deadLineCommand = userCommand.split("/");
        if (deadLineCommand.length != 2) {
            throw new IncorrectArgumentNumException();
        }*/

        if (ChatMan.accessTasks().size() == ChatMan.MAX_NUM_TASKS) {
            throw new FullListException();
        }

        String deadLineDesc = deadLineCommand[0].replaceAll("(?i)DEADLINE", "").trim();
        String by = deadLineCommand[1].replaceAll("(?i)BY", "").trim();

        if (deadLineDesc.isEmpty() || by.isEmpty()) {
            //replace with New Exception Type
            throw new IncorrectArgumentNumException();
        }

        ChatMan.accessTasks().add(new Deadline(deadLineDesc, by));

        super.replyAddedTask();
    }

}

package Commands;
import Task.Task;
import Utils.Parser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the Base Command. An abstract class of all the command.
 */
public abstract  class Command {
    protected String commandType;

    public Command(Parser parser){
        this.commandType = parser.getType();
    }
    /**
     * Represent the execution of the command, an abstract method
     *
     * @param tasksList represent the tasksList of the ChatBot
     * @throws IOException if save or Load meet IOException
     */
    public abstract void execute(ArrayList<Task> tasksList) throws IOException;
}

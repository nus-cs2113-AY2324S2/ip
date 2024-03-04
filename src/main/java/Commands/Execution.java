package Commands;

import DukeException.Command_Not_Exist;
import Utils.Parser;

import Task.*;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Represents the Execution process. A <code>execution</code> object corresponds to
 * a execution process which is going to wrong.
 */
public class Execution {
    protected Command command;
    protected ArrayList<Task> tasksList;

    /**
     * Construction function of the execution create the command according to user input parser
     *
     * @param tasksList represent the tasksList of the ChatBot
     * @param parser represent the parser of the user input Command
     * @throws Command_Not_Exist if user input a command does not exist
     */

    public Execution(Parser parser , ArrayList<Task> tasksList) throws Command_Not_Exist {
        this.tasksList = tasksList;
        switch (parser.getType()){
            case "list":{
                this.command = new List(parser);
                break;
            }
            case "mark":{
                this.command = new Mark(parser);
                break;
            }
            case "unmark":{
                this.command = new Unmark(parser);
                break;
            }
            case "delete":{
                this.command = new Delete(parser);
                break;
            }
            case "todo":
            case "event":
            case "deadline": {
                this.command = new Add(parser);
                break;
            }
            case "bye":{
                this.command = new Bye(parser);
                break;
            }
            case "find":{
                this.command = new Find(parser);
                break;
            }
            default:{
                throw new Command_Not_Exist();
            }
        }
    }
    public boolean isExit(Parser parser){
        return parser.getType().equals("bye");
    }
    public void runCommand() throws IOException {

        command.execute(tasksList);

    }
}

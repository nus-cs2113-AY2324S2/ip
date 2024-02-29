package suv.Helpers;

import suv.Task.FileStorage;
import suv.Command.SuvException;
import suv.Command.AddEventCommand;
import suv.Command.AddTodoCommand;
import suv.Command.AddDeadlineCommand;
import suv.Command.MarkCommand;
import suv.Command.UnMarkCommand;
import suv.Command.PrintListCommand;
import suv.Command.ByeCommand;
import suv.Command.DeleteTaskCommand;


/**
 * The Parser class is responsible for parsing user input and executing corresponding commands.
 * It handles user input and invokes appropriate command objects based on the input.
 */
public class Parser {
    final static String LINE = "____________________________________________________________\n";

    /**
     * Handles the user input by parsing it and executing the corresponding command.
     *
     * @param input The user input to be processed.
     * @throws SuvException If there is an error processing the input.
     */
    public void handleInput(String input) throws SuvException{
        try {
            if(input.equals("bye")){
                new ByeCommand();
            } else if (input.equals("list")){
                new PrintListCommand();
            } else if (input.contains("unmark")){
                new UnMarkCommand(input);
            } else if (input.contains("mark")){
                new MarkCommand(input);
            } else if(input.contains("todo")){
                new AddTodoCommand(input);
            }else if(input.contains("deadline")){
                new AddDeadlineCommand(input);
            } else if(input.contains("event")){
                new AddEventCommand(input);
            } else if(input.contains("delete")){
                new DeleteTaskCommand(input);
            } else {
                throw new SuvException(LINE +"Oh no! I am not sure what you mean.");
            }
            FileStorage.saveTasksToFile("data/data.txt");
        } catch (SuvException e){
            System.out.println(e.warning);
        }
    }
}

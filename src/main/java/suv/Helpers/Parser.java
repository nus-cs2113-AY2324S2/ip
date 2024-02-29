package suv.Helpers;

import suv.Command.*;
import suv.Task.FileStorage;

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
            } else if (input.startsWith("list")){
                new PrintListCommand();
            } else if (input.startsWith("unmark")){
                new UnMarkCommand(input);
            } else if (input.startsWith("mark")){
                new MarkCommand(input);
            } else if(input.startsWith("todo")){
                new AddTodoCommand(input);
            } else if(input.startsWith("deadline")){
                new AddDeadlineCommand(input);
            } else if(input.startsWith("event")){
                new AddEventCommand(input);
            } else if(input.startsWith("delete")){
                new DeleteTaskCommand(input);
            } else if(input.startsWith("find")) {
                new FindTasksCommand(input);
            } else {
                throw new SuvException(LINE +"Oh no! I am not sure what you mean.");
            }
            FileStorage.saveTasksToFile("data/data.txt");
        } catch (SuvException e){
            System.out.println(e.warning);
        }
    }
}

package suv.Helpers;

import suv.Command.*;
import suv.Task.FileStorage;


public class Parser {
    final static String LINE = "____________________________________________________________\n";

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

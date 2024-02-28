package suv.Command;

import suv.Helpers.Ui;
import suv.Task.Todo;

import static suv.Task.TaskList.tasksList;

public class AddTodoCommand {
    final static int TODO_KEYWORD_END_INDEX = 4;
    final static String LINE = "____________________________________________________________\n";

    public AddTodoCommand(String input) throws SuvException{
        if(input.trim().length() > 4){
            Todo newTask = new Todo(input.substring(TODO_KEYWORD_END_INDEX).trim());

            tasksList.add(newTask);
            Ui.printAddTodoMessage(newTask, tasksList.size());
        } else {
            throw new SuvException(LINE +"Oh no! You are missing the suv.Task.Todo description\n" + LINE);
        }
    }
}

package Command;

import Exceptions.MarioErrorExecutingCommand;
import Templates.TaskList;
import Templates.Task.ToDo;

public class ToDoCommand extends BaseCommand{
    public ToDoCommand(String taskString){
        super(false, taskString);
    }
    public String execute(TaskList taskList) throws Exception{
        try {
            ToDo newToDo = new ToDo(taskString);
            System.out.println("here" + taskString);
            return taskList.addTask(newToDo);
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}


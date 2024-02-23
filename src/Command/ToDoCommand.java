package command;

import exceptions.MarioErrorExecutingCommand;
import templates.TaskList;
import templates.task.ToDo;

public class ToDoCommand extends BaseCommand{
    public ToDoCommand(String taskString){
        super(false, taskString);
    }
    public String execute(TaskList taskList) throws Exception{
        try {
            ToDo newToDo = new ToDo(taskString.replaceAll("[^a-zA-Z0-9]", ""));
            return taskList.addTask(newToDo);
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}


package chris.commands;

import chris.tasktypes.ToDo;
import chris.tasktypes.taskList;

public class toDoCommand extends Command{
    public toDoCommand(String[] description) {
        super(description);
    }

    public String execute(taskList tasks) {
        tasks.addTask(new ToDo(description, false));
        return "New to do added!\n" + tasks.printTaskList();
    }

}

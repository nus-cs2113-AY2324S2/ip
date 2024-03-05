package chris.commands;

import chris.tasktypes.ToDo;
import chris.tasktypes.taskList;

public class toDoCommand extends Command{
    public toDoCommand(String[] description) {
        super(description);
    }

    public void execute(taskList tasks) {
        tasks.addTask(new ToDo(description, false));
        System.out.println("New to do added!");
        tasks.printTaskList();
    }

}

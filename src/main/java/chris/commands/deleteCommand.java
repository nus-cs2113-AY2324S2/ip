package chris.commands;

import chris.customexceptions.illegalTaskNumberInput;
import chris.tasktypes.Task;
import chris.tasktypes.taskList;

public class deleteCommand extends Command{
    public deleteCommand(String[] description) {
        super(description);
    }

    public void execute(taskList tasks) throws illegalTaskNumberInput{
        try {
            Task deletedTask = tasks.deleteTask(description[0]);
            System.out.println("The following task was deleted!");
            System.out.println(deletedTask);
        } catch (Exception e) {
            throw new illegalTaskNumberInput();
        }
    }
}

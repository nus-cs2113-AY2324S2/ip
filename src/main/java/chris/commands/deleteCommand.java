package chris.commands;

import chris.customexceptions.illegalTaskNumberInput;
import chris.tasktypes.Task;
import chris.tasktypes.taskList;

public class deleteCommand extends Command{
    public deleteCommand(String[] description) {
        super(description);
    }

    public String execute(taskList tasks) throws illegalTaskNumberInput{
        try {
            Task deletedTask = tasks.deleteTask(description[0]);
            return "The following task was deleted!\n" + deletedTask.toString();
        } catch (Exception e) {
            throw new illegalTaskNumberInput();
        }
    }
}

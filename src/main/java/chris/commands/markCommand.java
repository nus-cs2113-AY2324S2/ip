package chris.commands;

import chris.customexceptions.illegalTaskNumberInput;
import chris.tasktypes.taskList;

public class markCommand extends Command{
    public markCommand(String[] description) {
        super(description);
    }

    public String execute(taskList tasks) throws illegalTaskNumberInput{
        try {
            return tasks.markTask(description[0]);
        } catch (Exception e) {
            throw new illegalTaskNumberInput();
        }
    }
}

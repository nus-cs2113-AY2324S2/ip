package chris.commands;

import chris.customexceptions.illegalTaskNumberInput;
import chris.tasktypes.taskList;

public class markCommand extends Command{
    public markCommand(String[] description) {
        super(description);
    }

    public void execute(taskList tasks) throws illegalTaskNumberInput{
        try {
            tasks.markTask(description[0]);
            tasks.printTaskList();
        } catch (Exception e) {
            throw new illegalTaskNumberInput();
        }
    }
}

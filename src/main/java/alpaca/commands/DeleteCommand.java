package alpaca.commands;

import alpaca.exceptions.InvalidIndexException;
import alpaca.tasks.TaskList;
import alpaca.ui.Ui;


public class DeleteCommand extends AlpacaCommand{
    private int index;

    public DeleteCommand (int index, TaskList tasks) {
        super(tasks);
        this.index = index;
    }
    @Override
    public void execute(){
        try {
            if (!tasks.isCountValid(index)) {
                throw new InvalidIndexException();
            }
            tasks.deleteTask(index);
            Ui.printLine();
        } catch (InvalidIndexException e) {
            Ui.printErrorMessage(e.toString());
        }
    }
}
package alpaca.commands;

import alpaca.exceptions.InvalidIndexException;
import alpaca.tasks.TaskList;
import alpaca.ui.Ui;


public class DeleteCommand extends AlpacaCommand{
    private int index;

    public DeleteCommand (Ui ui, int index, TaskList tasks) {
        super(ui, tasks);
        this.index = index;
    }
    @Override
    public void execute(){
        try {
            if (!tasks.isCountValid(index)) {
                throw new InvalidIndexException();
            }
            tasks.deleteTask(index);
            ui.printLine();
        } catch (InvalidIndexException e) {
            ui.printErrorMessage(e.toString());
        }
    }
}
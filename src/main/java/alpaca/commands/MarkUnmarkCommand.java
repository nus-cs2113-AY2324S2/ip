package alpaca.commands;

import alpaca.exceptions.InvalidIndexException;
import alpaca.tasks.TaskList;
import alpaca.ui.Ui;


public class MarkUnmarkCommand extends AlpacaCommand{
    private int index;
    private boolean isMark;

    public MarkUnmarkCommand(Ui ui, int index, boolean isMark, TaskList tasks) {
        super(ui, tasks);
        this.index = index;
        this.isMark = isMark;
    }

    @Override
    public void execute() {
        try {
            if (!tasks.isCountValid(index)) {
                throw new InvalidIndexException();
            }
            if (isMark) {
                tasks.markTask(index);
            } else {
                tasks.unmarkTask(index);
            }
            ui.printLine();
        } catch (InvalidIndexException e) {
            ui.printErrorMessage(e.toString());
        }
    }
}
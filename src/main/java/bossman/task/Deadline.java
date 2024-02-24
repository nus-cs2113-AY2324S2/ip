package bossman.task;

import bossman.ui.Ui;

import java.time.LocalDate;

public class Deadline extends Todo {
    private final LocalDate BY;
    public Deadline(String task, boolean isMark, LocalDate by) {
        super(task, isMark);
        this.BY = by;
        this.typeSymbol = "[D]";
    }

    @Override
    public void printTask() {
        super.printTask();
        Ui.printMessageNoSepSameLine("(do by:" + BY + ")");
    }

    @Override
    public String formatForSave() {
        return "D" + "," + isMark + "," + DESCRIPTION + "," + BY;
    }
}

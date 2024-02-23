package bossman.task;

import bossman.ui.Ui;

public abstract class Task {
    protected boolean isMark;
    protected final String DESCRIPTION;
    protected String typeSymbol;

    public Task(String task, boolean isMark) {
        this.isMark = isMark;
        this.DESCRIPTION = task;
        this.typeSymbol = "";
    }

    public void printTask() {
        String markSymbol = isMark ? "[x] " : "[ ] ";
        String taskSymbol = getTypeSymbol();

        Ui.printMessageNoSepSameLine(taskSymbol + markSymbol + getTask());
    }

    public void setMark() {
        isMark = true;
    }

    public void setUnmark() {
        isMark = false;
    }

    public String getTask() {
        return DESCRIPTION;
    }

    public String getTypeSymbol() {
        return typeSymbol;
    }

    public String formatForSave() {
        return "";
    }
}
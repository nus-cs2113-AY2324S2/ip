package tasks;

import static main.Ui.printHeaders;

public class Todo extends TaskList {

    boolean newInput;

    public Todo(String description,  boolean newInput) {
        super(description);
        setNewInput(newInput);
        toPrint();
    }

    public void setNewInput(boolean newInput) {
        this.newInput = newInput;
    }


    @Override
    public String toString() {
        String[] splitLine = description.split("\\s+");
        StringBuilder action = new StringBuilder();

        //obtain the action of the task
        for (int i = 1; i < splitLine.length; i += 1) {
            action.append(splitLine[i]).append(" ");
        }
        return "[T]" + "[" + this.getStatusIcon() + "] " + action;
    }

    public void toPrint() {
        if (newInput) {
            printHeaders();
        }
    }
}
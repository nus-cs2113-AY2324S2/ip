package tasks;

import main.DukeException;

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


    /* Returns the type of task and their action. */
    @Override
    public String toString() {
        String[] splitLine = description.split("\\s+");
        StringBuilder action = new StringBuilder();

        if (splitLine.length < 2) {
            try {
                throw new DukeException("Invalid Syntax! Please try again!");
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }
        }

        //obtain the action of the task
        for (int i = 1; i < splitLine.length; i += 1) {
            action.append(splitLine[i]).append(" ");
        }
        return "[T]" + "[" + this.getStatusIcon() + "] " + action;
    }

    public void toPrint() {
        if (toString() != null && newInput) {
                printHeaders();
        }
    }
}
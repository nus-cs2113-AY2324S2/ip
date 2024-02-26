package mimi.classes;

import mimi.exceptions.MimiException;
import static mimi.helper.Storage.FILE_DELIMINITER;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;

    }
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + this.getDeadline() + ")";
    }



    @Override
    public String toFileString(){
        return "D" +  FILE_DELIMINITER + super.toFileString() + FILE_DELIMINITER + this.getDeadline();
    }

}

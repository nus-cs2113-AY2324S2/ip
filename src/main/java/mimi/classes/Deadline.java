package mimi.classes;

import mimi.exceptions.MimiException;
import static mimi.Duke.FILE_DELIMINITER;

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

    public static Deadline processDeadline(String[] inputs) throws
            MimiException.InsufficientParameters,
            MimiException.IncorrectFormat {

        if (inputs.length < 2) {
            // Throws an error if /by is missing
            throw new MimiException.IncorrectFormat(MimiException.INCORRECT_DEADLINE_FORMAT_MSG);
        }

        for (String s : inputs) {
            // Throws an error if parameters is incomplete
            if (s == null || s.isEmpty()) {
                throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS_MSG);
            }
        }

        if (inputs[1].strip().isBlank()){
            throw new MimiException.InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS_MSG);
        }

        String taskName = inputs[0];
        String dueDate = inputs[1].strip();

        return new Deadline(taskName, dueDate);

    }

    @Override
    public String toFileString(){
        return "D" +  FILE_DELIMINITER + super.toFileString() + FILE_DELIMINITER + this.getDeadline();
    }

}

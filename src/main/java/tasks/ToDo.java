package tasks;

public class ToDo extends Task {


    public ToDo(String userInput) {
        super(userInput);
    }

    @Override
    public String taskString() {
        return "[T]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}


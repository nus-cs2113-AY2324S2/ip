package Brad.Tasks;

public class Todo extends Task {
    public Todo (String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getFullDescription() {
        String output = "[T][";
        output += getIsDone() ? "X] " : " ] ";
        output += getTaskDescription();
        return output;
    }
}
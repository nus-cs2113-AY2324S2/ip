package Brad.Tasks;

public class Todo extends Task {
    public Todo (String description) {
        super(description);
    }

    @Override
    public String getFullDescription() {
        String output = "[T][";
        output += getIsDone() ? "X] " : " ] ";
        output += getTaskDescription();
        return output;
    }
}
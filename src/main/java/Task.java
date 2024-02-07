public class Task {
    private final StringBuilder description;
    private static final int START_INDEX = 4;
    private static final int END_INDEX = 5;

    Task(String input, String type) {
        this.description = new StringBuilder("[" + type + "]" + "[ ] " + input);
    }

    Task (StringBuilder description) {
        this.description = description;
    }

    Task markTask() {
        return new Task(this.description.replace(START_INDEX,END_INDEX,"X"));
    }

    Task unmarkTask() {
        return new Task(this.description.replace(START_INDEX,END_INDEX," "));
    }

    String getTask() {
        return this.description.toString();
    }

    @Override
    public String toString() {
        return this.description.toString();
    }
}

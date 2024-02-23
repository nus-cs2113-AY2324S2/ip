package jason.tasks;

public class Events extends Task {

    private final String from;
    private final String to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public static Task parseFromFile(String data) {
        boolean isDone = data.charAt(4) == 'X';
        int fromIndex = data.indexOf("(from: ");
        if (fromIndex == -1) { //replace this with throw statements
            return null;
        }
        String description = data.substring(6, fromIndex).trim();
        int toIndex = data.indexOf(" to: ", fromIndex);
        if (toIndex == -1) { //replace this with throw statements
            return null;
        }
        String from = data.substring(fromIndex + 7, toIndex).trim();
        String to = data.substring(toIndex + 5, data.length() - 1).trim();

        Events task = new Events(description, from, to);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

}

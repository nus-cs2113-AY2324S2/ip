public class Deadline extends Task {
    private static int taskStartIndex = 9;

    public Deadline(String line) {
        super(line.substring(taskStartIndex, line.indexOf("/by")));
    }
}

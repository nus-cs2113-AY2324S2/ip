public class Deadline extends Task {
    protected String due;

    public Deadline(String label, String due) {
        super(label);
        this.due = due;
    }

    public static String[] getDeadline(String input) {

        String[] results = new String[2];


        int index = input.indexOf("/by");

        String label = input.substring(0, index).trim();


        String deadline = input.substring(index + 3).trim();

        results[0] = label;
        results[1] = deadline;

        return results;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }
}


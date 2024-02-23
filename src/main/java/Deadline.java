public class Deadline extends Task {
    protected String due;

    public Deadline(String label, String due) {
        super(label);
        this.due = due;
    }

    public static String[] getDeadline(String input) {

        String[] results = new String[Constant.DEADLINE_PARAMETERS];


        int index = input.indexOf("/by");

        String label = input.substring(0, index).trim();


        String deadline = input.substring(index + Constant.DEADLINE_BY_OFFSET).trim();

        results[0] = label;
        results[1] = deadline;

        return results;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }
    @Override
    public String getType() {
        return "DEADLINE";
    }
    @Override
    public String getRange() {
        return due;
    }
}


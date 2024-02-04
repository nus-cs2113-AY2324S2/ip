public class Deadline extends Task {

    protected String deadline = "Unknown";

    public Deadline(String input) {

        if (!input.contains("/by")) {
            if (!input.trim().equals("")) {
                super.name = input.trim();
            }
            return;
        }

        String[] inputSplit = input.split("/by");

        if (!inputSplit[0].trim().equals("")) {
            super.name = inputSplit[0].trim();
        }

        if (inputSplit.length > 1) {
            this.deadline = inputSplit[1].trim();
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.trim() + ")";
    }

}

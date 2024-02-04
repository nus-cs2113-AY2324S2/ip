public class Event extends Task {

    protected String from = "Unknown";
    protected String to = "Unknown";

    public Event(String input) {

        String[] inputSplit;

        if (input.contains("/from")) {

            inputSplit = input.split("/from");

            if (!inputSplit[0].trim().equals("")) {
                super.name = inputSplit[0].trim();
            }

            if (input.contains("/to")) {

                inputSplit = inputSplit[1].split("/to");

                if (!inputSplit[0].trim().equals("")) {
                    this.from = inputSplit[0].trim();
                }

                if (inputSplit.length > 1) {
                    this.to = inputSplit[1].trim();
                }

            } else if (inputSplit.length > 1) {
                this.from = inputSplit[1].trim();
            }

        } else if (input.contains("/to")) {

            inputSplit = input.split("/to");

            if (!inputSplit[0].trim().equals("")) {
                super.name = inputSplit[0].trim();
            }

            if (inputSplit.length > 1) {
                this.to = inputSplit[1].trim();
            }

        } else {
            if (!input.trim().equals("")) {
                super.name = input.trim();
            }
        }

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.trim() + " to: " + to.trim() + ")";
    }

}

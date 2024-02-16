package chatbot.task;

import chatbot.ChatbotException;

public class Deadline extends Task {
    private final String startTime;
    public Deadline(String description) throws ChatbotException {
        super(description);
        String[] descriptionList = description.split("/by ", 2);
        if (descriptionList.length != 2) {
            throw new ChatbotException("Formatting error. Use /by to state timing. ");
        }
        this.setDescription(descriptionList[0]);
        this.startTime = descriptionList[1];
    }
    public String getTypeDisplay() {
        return "[D]";
    }

    public String getData() {
        return this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription()
                + "(by: " + this.startTime + ")";
    }
}

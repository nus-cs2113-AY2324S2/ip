package chatbot.task;

import chatbot.ChatbotException;

public class Event extends Task {
    private final String startTime;
    private final String endTime;
    public Event(String description) throws ChatbotException {
        super(description);
        String[] descriptionList = description.split(" /", 3);
        if (descriptionList.length != 3) {
            throw new ChatbotException("Formatting error. Use /from and /to to state timings. ");
        }
        this.setDescription(descriptionList[0]);
        this.startTime = descriptionList[1].split("from ")[1];
        this.endTime = descriptionList[2].split("to ")[1];
    }
    public String getTypeDisplay() {
        return "[E]";
    }
    public String getTaskName() {
        return "event";
    }
    public String getData() {
        return this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription()
                + " (from: " + this.startTime + ", to: " + this.endTime + ")";
    }
}

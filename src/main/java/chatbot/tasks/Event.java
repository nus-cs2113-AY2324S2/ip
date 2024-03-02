package chatbot.tasks;

import chatbot.ChatbotException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Represents a Event task with a start and end date.
 */
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
        try {
            LocalDate inputStartTime = LocalDate.parse(descriptionList[1].split("from ")[1]);
            LocalDate inputEndTime = LocalDate.parse(descriptionList[1].split("from ")[1]);
            this.startTime = inputStartTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
            this.endTime = inputEndTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        } catch (DateTimeParseException e) {
            throw new ChatbotException("Date format error. Use YYYY-MM-DD. ");
        }
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
    public void printData() {
        System.out.println(this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription()
                + " (from: " + this.startTime + ", to: " + this.endTime + ")");
    }
}

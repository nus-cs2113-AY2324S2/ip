package chatbot.tasks;

import chatbot.ChatbotException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private final String startTime;
    public Deadline(String description) throws ChatbotException {
        super(description);
        String[] descriptionList = description.split("/by ", 2);
        if (descriptionList.length != 2) {
            throw new ChatbotException("Formatting error. Use /by to state timing. ");
        }
        this.setDescription(descriptionList[0]);
        try {
            LocalDate inputTime = LocalDate.parse(descriptionList[1]);
            this.startTime = inputTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        } catch (DateTimeParseException e) {
            throw new ChatbotException("Date format error. Use YYYY-MM-DD. ");
        }

    }
    public String getTypeDisplay() {
        return "[D]";
    }

    public String getTaskName() {
        return "deadline";
    }

    public String getData() {
        return this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription()
                + "(by: " + this.startTime + ")";
    }
    public void printData() {
        System.out.println(this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription()
                + "(by: " + this.startTime + ")");
    }
}

package lovie.task;

/**
 * Represents an event task.
 */
public class Event extends Task {

    /**
     * Constructor for Event.
     *
     * @param description The description of the event.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Returns the description of the event.
     *
     * @return The description of the event.
     */
    @Override
    public String getDescription() {
        String[] parts = description.split("(?i)/from ");
        if (parts.length < 2) {
            return description;
        }
        return parts[0].split("(?i)event")[1].trim();
    }

    /**
     * Returns the timespan of the event.
     *
     * @return The timespan of the event.
     */
    @Override
    public String getTimespan() {
        try {
            String startTime = description.split("(?i)/from")[1].split("/")[0].trim();
            String endTime = description.split("(?i)/to")[1].trim();
            return " (from: " + startTime + " to: " + endTime + ")";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Returns the icon of the event.
     *
     * @return The icon of the event.
     */
    @Override
    public String getTaskIcon() {
        return "E";
    }

}

package lovie.task;

public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        String[] parts = description.split("(?i)/from ");
        if (parts.length < 2) {
            return description;
        }
        String realDescription = parts[0].split("(?i)event")[1].trim();
        return realDescription;
    }

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

    @Override
    public String getTaskIcon() {
        return "E";
    }

}

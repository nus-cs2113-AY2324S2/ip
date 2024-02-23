package helpy.task;

import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task{
    protected String eventFrom;
    protected String eventTo;

    public Event(String command) {
        super();
        String description = command.replace("event", "").trim();
        String[] details = description.split("/from");
        taskName = details[0].trim();

        String[] eventPeriod = details[1].split("/to");
        eventFrom = eventPeriod[0].trim();
        eventTo = eventPeriod[1].trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + eventFrom + " to: " + eventTo + ")";
    }

    public void saveToFile(String filePath) throws IOException {
        FileWriter helpyWriter = new FileWriter(filePath, true);
        String isDone = isDone() ? "1" : "0";
        helpyWriter.write("E | " + isDone + " | " + this.taskName
                + " /from " + this.eventFrom + " /to " + this.eventTo + "\n");
        helpyWriter.close();
    }
}

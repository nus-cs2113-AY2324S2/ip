package helpy.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String deadlineStr;
    protected LocalDateTime deadline;

    public Deadline(String command) {
        super();
        String[] details = command.split("/by");
        taskName = details[0].trim();
        deadlineStr = details[1].trim();
        deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    @Override
    public String toString() {
        String deadlineDate = deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
        return "[D]" + super.toString()
                + " (by: " + deadlineDate + ")";
    }

    @Override
    public void saveToFile(String filePath) throws IOException {
            FileWriter helpyWriter = new FileWriter(filePath, true);
            String isDone = isDone() ? "1" : "0";
            helpyWriter.write("D | " + isDone + " | " + taskName
                    + " /by " + deadlineStr + "\n");
            helpyWriter.close();
    }
}

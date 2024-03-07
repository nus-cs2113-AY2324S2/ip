package helpy.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a due date and time.
 */
public class Deadline extends Task{
    protected String deadlineStr;
    protected LocalDateTime deadline;

    /**
     * Constructs a new Deadline task object from the given command string.
     *
     * @param command The command string used to create the Deadline.
     */
    public Deadline(String command) {
        super();
        String[] details = command.split("/by");
        taskName = details[0].trim();
        deadlineStr = details[1].trim();
        deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns a string representation of the Deadline.
     *
     * @return A string representation of the Deadline, including its status icon, name, and due date and time.
     */
    @Override
    public String toString() {
        String deadlineDate = deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
        return "[D]" + super.toString()
                + " (by: " + deadlineDate + ")";
    }

    /**
     * Saves the Deadline to the specified file.
     *
     * @param filePath The path of the file to save the Deadline to.
     * @throws IOException If an error occurs while writing to the file.
     */
    @Override
    public void saveToFile(String filePath) throws IOException {
            FileWriter helpyWriter = new FileWriter(filePath, true);
            String isDone = isDone() ? "1" : "0";
            helpyWriter.write("D | " + isDone + " | " + taskName
                    + " /by " + deadlineStr + "\n");
            helpyWriter.close();
    }
}

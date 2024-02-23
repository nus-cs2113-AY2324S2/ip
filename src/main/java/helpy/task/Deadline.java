package helpy.task;

import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task{
    protected String dueDate;

    public Deadline(String command) {
        super();
        String description = command.replace("deadline", "").trim();
        String[] details = description.split("/by");
        this.taskName = details[0].trim();
        this.dueDate = details[1].trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueDate + ")";
    }

    public void saveToFile(String filePath) throws IOException {
            FileWriter helpyWriter = new FileWriter(filePath, true);
            String isDone = isDone() ? "1" : "0";
            helpyWriter.write("D | " + isDone + " | " + this.taskName
                    + " /by " + this.dueDate + "\n");
            helpyWriter.close();
    }
}

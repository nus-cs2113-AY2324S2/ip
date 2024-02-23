package helpy.task;

import helpy.exceptions.IllegalDescriptionException;

import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task{
    public Todo(String command) throws IllegalDescriptionException {
        super();
        String description = command.replace("todo", "").trim();
        if (description.isEmpty()) {
            throw new IllegalDescriptionException();
        }
        taskName = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public void saveToFile(String filePath) throws IOException {
        FileWriter helpyWriter = new FileWriter(filePath, true);
        String isDone = isDone() ? "1" : "0";
        helpyWriter.write("T | " + isDone + " | " + this.taskName + "\n");
        helpyWriter.close();
    }
}

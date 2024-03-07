package helpy.task;

import helpy.exceptions.IllegalDescriptionException;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a Todo task.
 */
public class Todo extends Task{
    /**
     * Constructs a new Todo task from the given command string.
     *
     * @param command The command string used to create the Todo task.
     * @throws IllegalDescriptionException If the description of the Todo task is empty.
     */
    public Todo(String command) throws IllegalDescriptionException {
        super();;
        if (command.isEmpty()) {
            throw new IllegalDescriptionException();
        }
        taskName = command;
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string representation of the Todo task, prefixed with "[T]".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Saves the Todo task to the specified file.
     *
     * @param filePath The path of the file to save the Todo task to.
     * @throws IOException If an error occurs while writing to the file.
     */
    @Override
    public void saveToFile(String filePath) throws IOException {
        FileWriter helpyWriter = new FileWriter(filePath, true);
        String isDone = isDone() ? "1" : "0";
        helpyWriter.write("T | " + isDone + " | " + this.taskName + "\n");
        helpyWriter.close();
    }
}

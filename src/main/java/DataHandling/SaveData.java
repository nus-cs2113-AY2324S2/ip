package DataHandling;

import Tasks.*;

import java.io.FileWriter;
import java.io.IOException;

public class SaveData {
    private static final String filePath = "src/data/GermaBotData.txt";

    public static void addTodoToFile(ToDo toDoTask, char taskType) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write("T | " + (toDoTask.isDone() ? "1" : "0") + " | " + toDoTask.getDescription() + System.lineSeparator());
        writer.close();
    }

    public static void addDeadlineToFile(Deadline deadlineTask, char taskType) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write("D | " +  (deadlineTask.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription() +
                " | " + deadlineTask.getBy() + System.lineSeparator());
        writer.close();
    }

    public static void addEventToFile(Event eventTask, char taskType) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write("E | " + (eventTask.isDone() ? "1" : "0") + " | " + eventTask.getDescription() +
                " | " + eventTask.getFrom() + " | " + eventTask.getTo() + System.lineSeparator());
        writer.close();
    }
}

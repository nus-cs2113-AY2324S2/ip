package savedData;

import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

import java.io.FileWriter;
import java.io.IOException;

public class AppendData {
    private static String filePath = "src/data/peekay.txt";

    public static void appendToDo(ToDo input) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        String description = input.getDescription();
        String taskType = input.getTaskType();
        String isCompleted = (input.getStatusIcon().equals("X")) ? "1" : "0";
        String textToAppend = taskType + " | " + isCompleted + " | " + description;
        fw.write(textToAppend);
        fw.close();
    }

    public static void appendDeadline(Deadline input) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        String description = input.getDescription();
        String taskType = input.getTaskType();
        String isCompleted = (input.getStatusIcon().equals("X")) ? "1" : "0";
        String by = input.getEndDate();
        String textToAppend = taskType + " | " + isCompleted + " | " + description + " | " + by;
        fw.write(textToAppend);
        fw.close();
    }

    public static void appendEvent(Event input) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        String description = input.getDescription();
        String taskType = input.getTaskType();
        String isCompleted = (input.getStatusIcon().equals("X")) ? "1" : "0";
        String by = input.getEndDate();
        String from = input.getStartDate();
        String textToAppend = taskType + " | " + isCompleted + " | " + description + " | " + by;
        fw.write(textToAppend);
        fw.close();
    }

}

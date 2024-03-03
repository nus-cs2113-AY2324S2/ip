package Storage;

import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

import java.io.FileWriter;
import java.io.IOException;

public class AppendData {
    public static void appendToDo(ToDo input, FileWriter fw) throws IOException {
        String description = input.getDescription();
        String taskType = input.getTaskType();
        String isCompleted = (input.getStatusIcon().equals("X")) ? "1" : "0";
        String textToAppend = taskType + " | " + isCompleted + " | " + description;
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static void appendDeadline(Deadline input, FileWriter fw) throws IOException {
        String description = input.getDescription();
        String taskType = input.getTaskType();
        String isCompleted = (input.getStatusIcon().equals("X")) ? "1" : "0";
        String by = input.getEndDate();
        String textToAppend = taskType + " | " + isCompleted + " | " + description + " | " + by;
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static void appendEvent(Event input, FileWriter fw) throws IOException {
        String description = input.getDescription();
        String taskType = input.getTaskType();
        String isCompleted = (input.getStatusIcon().equals("X")) ? "1" : "0";
        String by = input.getEndDate();
        String from = input.getStartDate();
        String textToAppend = taskType + " | " + isCompleted + " | " + description + " | " + from + " | " + by;
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

}

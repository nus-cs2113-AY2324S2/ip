package Quokka.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static Task parseFromFileString(String data) {
        String[] parts = data.split("] ", 2);
        if (parts.length == 2) {
            boolean isDone = parts[0].charAt(4) == 'X';
            String taskType = parts[0].substring(1, 2);
            String taskData = parts[1];
            switch (taskType) {
            case "T":
                return Todo.parseFromString(taskData, isDone);
            case "D":
                return Deadline.parseDeadlineFromString(taskData, isDone);
            case "E":
                return Event.parseFromString(taskData, isDone);
            default:
                return null;
            }
        }
        return null;
    }

    public static Task parseDeadlineFromString(String taskData, boolean isDone) {
        String[] parts = taskData.split(" \\(by: ", 2);
        if (parts.length == 2) {
            String description = parts[0].trim();
            LocalDateTime by = LocalDateTime.parse(parts[1].substring(0, parts[1].length() - 1),
                    DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return new Deadline(description, by, isDone);
        } else {
            return null;
        }
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return this.description;
    }
}

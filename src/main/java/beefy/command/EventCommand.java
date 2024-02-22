package beefy.command;

import beefy.BeefyException;
import beefy.storage.Storage;
import beefy.task.TaskList;
import beefy.task.Task;
import java.time.LocalDateTime;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand implements Command {
    private TaskList userTasks;
    private String taskDescription;
    private LocalDateTime taskFrom, taskTo;

    public EventCommand(TaskList userTasks, String userParams) throws BeefyException {
        this.userTasks = userTasks;
        String[] taskDetails = userParams.trim().split("/from|/to");
        if (taskDetails.length < 3) {
            LocalDateTime currDateTime = LocalDateTime.now();
            throw new BeefyException("Use format:" + System.lineSeparator()
                    + "event (Task Description) /from (DateTime) /to (DateTime)" + System.lineSeparator()
                    + "E.g: " + System.lineSeparator()
                    + "event Learn how to use event command /from "
                    + currDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) + " /to "
                    + currDateTime.plusHours(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        }
        taskDescription = taskDetails[0].trim();
        try {
            taskFrom = LocalDateTime.parse(taskDetails[1].trim());
            taskTo = LocalDateTime.parse(taskDetails[2].trim());
        } catch (DateTimeParseException e) {
            LocalDateTime currDateTime = LocalDateTime.now();
            throw new BeefyException("Use format: " + System.lineSeparator()
                    + "event (Task Description) /from (DateTime) /to (DateTime)" + System.lineSeparator()
                    + "E.g: " + System.lineSeparator()
                    + "event Learn how to use event command /from "
                    + currDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) + " /to "
                    + currDateTime.plusHours(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        }
    }

    @Override
    public void execute() throws IOException {
        Task addedTask = userTasks.addTask(taskDescription, taskFrom, taskTo, false);
        Storage.addToDisk(addedTask.toDiskFormat());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

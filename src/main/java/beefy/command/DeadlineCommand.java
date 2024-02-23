package beefy.command;

import beefy.BeefyException;
import beefy.task.TaskList;
import beefy.task.Task;
import beefy.storage.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    private TaskList userTasks;
    private String taskDescription;
    private LocalDateTime taskBy;

    public DeadlineCommand(TaskList userTasks, String userParams) throws BeefyException {
        this.userTasks = userTasks;
        String[] taskDetails = userParams.trim().split("/by");
        if (taskDetails.length < 2) {
            throw new BeefyException("Use format:" + System.lineSeparator()
                    + "deadline (Task Description) /by (DateTime)" + System.lineSeparator()
                    + "E.g: " + System.lineSeparator()
                    + "deadline Learn how to use deadline command /by "
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));

        }
        taskDescription = taskDetails[0].trim();
        try {
            taskBy = LocalDateTime.parse(taskDetails[1].trim());
        } catch (DateTimeParseException e) {
            throw new BeefyException("Use format: " + System.lineSeparator()
                    + "deadline (Task Description) /by (DateTime)" + System.lineSeparator()
                    + "E.g: " + System.lineSeparator()
                    + "deadline Learn how to use deadline command /by "
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        }
    }

    @Override
    public void execute() throws IOException, BeefyException {
        Task addedTask = userTasks.addTask(taskDescription, taskBy, false);
        Storage.addToDisk(addedTask.toDiskFormat());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

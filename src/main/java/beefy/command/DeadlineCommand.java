package beefy.command;

import beefy.BeefyException;
import beefy.task.TaskList;
import beefy.task.Task;
import beefy.storage.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add deadlines to task list.
 */
public class DeadlineCommand implements Command {
    private TaskList userTasks;
    private String taskDescription;
    private LocalDateTime taskBy;

    /**
     * Constructs a new DeadlineCommand object with user input.
     *
     * @param userTasks User list of tasks.
     * @param userParams User input of the deadline command.
     * @throws BeefyException if there is any formatting issues.
     */
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
        if (taskDescription.isEmpty()) {
            throw new BeefyException("Quit fooling me, I do not see any task to add!");
        }
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

    /**
     * Executes the deadline command, adding the deadline task into user task list, saving data in text file.
     *
     * @throws IOException if an IO error occurs.
     */
    @Override
    public void execute() throws IOException, BeefyException {
        Task addedTask = userTasks.addTask(taskDescription, taskBy, false);
        Storage.addToDisk(addedTask.toDiskFormat());
    }

    /**
     * Indicates whether this is an exit command.
     * Returns false since this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

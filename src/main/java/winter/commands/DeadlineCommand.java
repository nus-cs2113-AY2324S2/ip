package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Deadline;
import winter.task.Task;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents the command given by the user to add a Deadline task
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private static final String MESSAGE_SUCCESS = "Great! New Deadline added: ";

    private String deadlineName;
    private LocalDateTime deadlineTime;

    public DeadlineCommand(String deadlineName, LocalDateTime deadlineTime) {
        this.deadlineName = deadlineName;
        this.deadlineTime = deadlineTime;
    }
    /**
     * Upon receiving the command from the user, create a new Deadline object, add it to task list,
     * show the confirmation message and update it in storage
     * @param tasks The TaskList object representing a list of the tasks
     * @param ui The user interface that provides feedback to the user
     * @param storage The storage object which helps store changes made to the list
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task newDeadline = new Deadline(tasks.getCurrentTaskIndex() + 1, false, deadlineName,deadlineTime);
        tasks.addNewTask(newDeadline);
        //tasks.increaseLastTaskIndex();
        ui.showTaskAddedConfirm(tasks,newDeadline);
        try {
            storage.writeToFile(formatDeadlineForStorage(newDeadline), true);
        } catch (IOException e) {
            System.out.println("Could not write to file: " + e.getMessage());
        }
    }


    /**
     * Format the Deadline object into a string that can be written to the storage file
     * @param newDeadline The new deadline object that was created
     * @return String in the format that can be written to storage and read later
     */

    private String formatDeadlineForStorage(Task newDeadline) {

        return "D" + " | " + newDeadline.getIsMarked() + " | " +
                    newDeadline.getTaskName() + " | " + newDeadline.getDeadline() + System.lineSeparator();

    }

}

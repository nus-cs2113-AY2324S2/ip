package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Deadline;
import winter.task.Task;

import java.io.IOException;


public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private static final String MESSAGE_SUCCESS = "Great! New Deadline added: ";

    private String deadlineName;
    private  String deadlineTime;

    public DeadlineCommand(String deadlineName, String deadlineTime) {
        this.deadlineName = deadlineName;
        this.deadlineTime = deadlineTime;
    }

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

    private String formatDeadlineForStorage(Task newEvent) {

        return "D" + " | " + newEvent.getIsMarked() + " | " +
                newEvent.getTaskName() + " | " + newEvent.getEndTime() + System.lineSeparator();
    }

}

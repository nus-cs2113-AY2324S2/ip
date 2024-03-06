package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the command given by the user to delete a task
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int taskDeleteNum;
    //public static final String MESSAGE_DELETE_TASK_SUCCESS = "No problemo, I've removed this task: ";

    public DeleteCommand(int taskDeleteNum) {
        this.taskDeleteNum = taskDeleteNum;
    }
    /**
     * Upon receiving the command from the user, show the confirmation message, find the task of interest, remove it from the task list,
     * and update it in storage
     * @param tasks The TaskList object representing a list of the tasks
     * @param ui The user interface that provides feedback to the user
     * @param storage The storage object which helps store changes made to the list
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskRemovedConfirm(tasks, taskDeleteNum);
        ArrayList<Task> taskArrayList = tasks.deleteTask(taskDeleteNum);
        String writeFileString = updateTaskListForStorage(taskArrayList);

        try {
            storage.writeToFile(writeFileString,false);
        } catch (IOException e) {
            System.out.println("Error writing to file after removing item:" + e.getMessage());
        }
    }

    /**
     * Prepare the String that can be written to the storage file
     * The String contains the remaining tasks in the task list after deletion
     * @param taskArrayList The task list
     * @return String that consists of all the tasks in the list in a specified format
     */

    private String updateTaskListForStorage(ArrayList<Task> taskArrayList) {
        String writeFileString = "";
        for (Task task : taskArrayList) {
            switch (task.getType()) {
            case "E":
                writeFileString += "E" + " | " + task.getIsMarked() + " | " +
                        task.getTaskName() + " | " + task.getStartTime() + " | " +
                        task.getEndTime() + System.lineSeparator();
                break;
            case "D":
                writeFileString += "D" + " | " + task.getIsMarked() + " | " +
                        task.getTaskName() + " | " + task.getEndTime() + System.lineSeparator();
                break;
            default:
                writeFileString += "T" + " | " + task.getIsMarked() + " | " +
                        task.getTaskName() + System.lineSeparator();
                break;
            }
        }
        return writeFileString;
    }
}

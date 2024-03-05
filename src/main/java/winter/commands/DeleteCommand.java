package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int taskDeleteNum;
    //public static final String MESSAGE_DELETE_TASK_SUCCESS = "No problemo, I've removed this task: ";

    public DeleteCommand(int taskDeleteNum) {
        this.taskDeleteNum = taskDeleteNum;
    }
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

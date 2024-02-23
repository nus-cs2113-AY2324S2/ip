package gab.command;

import gab.task.TaskList;
import gab.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList) {
        Ui.listTask(taskList);
    }
}

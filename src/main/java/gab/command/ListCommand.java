package gab.command;

import gab.task.TaskList;
import gab.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(String task, TaskList taskList) {
        Ui.listTask(taskList);
    }
}

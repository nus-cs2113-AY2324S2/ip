package gab.command;

import gab.task.TaskList;
import gab.task.ToDo;
import gab.ui.Ui;

public class TodoCommand implements Command {
    private final String TASK_NAME;

    public TodoCommand(String task) {
        this.TASK_NAME = task;
    }

    @Override
    public void execute (String taskDescription, TaskList taskList) {
        ToDo newToDo = new ToDo(TASK_NAME);
        taskList.addToList(newToDo);
        Ui.printTodoTask(newToDo.toString());
        Ui.printTaskCount(taskList.getTaskCount());
    }
}

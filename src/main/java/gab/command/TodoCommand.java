package gab.command;

import gab.task.TaskList;
import gab.task.ToDo;
import gab.ui.Ui;

/**
 * Command to add new Todo task to taskList
 */
public class TodoCommand extends Command {
    private final String TASK_NAME;

    /**
     * Initialise the todo task name to display
     *
     * @param task task name of todo task to dispaly
     */
    public TodoCommand(String task) {
        this.TASK_NAME = task;
    }

    /**
     * Creates a new todo task in the arraylist
     * Display the new todo task and final task count
     *
     * @param taskList arraylist of task
     */
    @Override
    public void execute (TaskList taskList) {
        ToDo newToDo = new ToDo(TASK_NAME);
        taskList.addToList(newToDo);
        Ui.printTodoTask(newToDo.toString());
        Ui.printTaskCount(taskList.getTaskCount());
    }
}

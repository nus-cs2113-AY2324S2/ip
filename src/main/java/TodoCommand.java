public class TodoCommand implements Command {
    public static final int TODO_START_INDEX = 5;
    private final String task;

    public TodoCommand(String TASK) {
        this.task = TASK;
    }

    @Override
    public void execute (String taskDescription, Task[] taskList) {
        //String todoName = task.substring(TODO_START_INDEX);
        ToDo newToDo = new ToDo(task);
        taskList[Task.getTaskCount() - 1] = newToDo; //creating new class(to do) in the array
        Ui.printTodoTask(newToDo.toString());
        Ui.printTaskCount(Task.getTaskCount());
    }
}

public class TodoCommand implements Command {
    //public static final int TODO_START_INDEX = 5;
    private final String taskName;

    public TodoCommand(String task) {
        this.taskName = task;
    }

    @Override
    public void execute (String taskDescription, TaskList taskList) {
        //String todoName = task.substring(TODO_START_INDEX);
        ToDo newToDo = new ToDo(taskName);
        taskList.addToList(newToDo);
        Ui.printTodoTask(newToDo.toString());
        Ui.printTaskCount(taskList.getTaskCount());
        //taskList[Task.getTaskCount() - 1] = newToDo; //creating new class(to do) in the array
        //Ui.printTodoTask(newToDo.toString());
        //Ui.printTaskCount(Task.getTaskCount());
    }
}

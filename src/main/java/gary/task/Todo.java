package gary.task;

public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
        this.taskType = TaskType.TODO;
    }

    //function to print when user adds a task
    @Override
    public void printAdd(int todosCount) {
        System.out.println("Got it! I've added this task: ");
        System.out.println("  [T][ ] " + this.getTaskDescription());
        System.out.println("Now you have " + todosCount + " tasks in your list.");
    }

    //function to print when user list tasks
    @Override
    public void printTask(int todoCount) {
        System.out.println((todoCount + 1)
                + ".[T]"
                + "[" + (this.getTaskStatus() ? "X" : " ") + "] "
                + this.getTaskDescription());
    }
}

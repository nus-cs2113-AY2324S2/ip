public class TaskList {
    private int numberOfTask;
    private final Task[] tasks;
    private static final String LIST_TASK_MESSAGE =
            "Here are the tasks in your list:\n";
    private static final String UNMARKED_MESSAGE =
            "OK, I've marked this task as not done yet:\n";
    private static final String MARKED_MESSAGE =
            "Nice! I've marked this task as done:\n";

    TaskList() {
        this.numberOfTask = 0;
        this.tasks = new Task[100];
    }

    public void add(Task task) {
        tasks[numberOfTask] = task;
        numberOfTask++;
    }

    public int getSize() {
        return numberOfTask;
    }

    public void markTask(int taskNum) {
        tasks[taskNum - 1].mark();
        ResponseManager.indentPrint(MARKED_MESSAGE +
                tasks[taskNum - 1].toString());
    }

    public void unmarkTask(int taskNum) {
        tasks[taskNum - 1].unmark();
        ResponseManager.indentPrint(UNMARKED_MESSAGE +
                tasks[taskNum - 1].toString());
    }

    public void listTasks() {
        String tasksToBeListed = "";
        int index = 0;
        for (int i = 0; i < numberOfTask - 1; i++) {
            index = i + 1;
            tasksToBeListed += String.format("%d.%s\n", index, tasks[i].toString());
        }
        tasksToBeListed += String.format("%d.%s", numberOfTask, tasks[index].toString());
        ResponseManager.indentPrint(LIST_TASK_MESSAGE + tasksToBeListed);
    }

    public Task showNewlyAddedTask() {
        return tasks[numberOfTask - 1];
    }

    @Override
    public String toString() {
        String formOfTask = numberOfTask > 1 ? "tasks" : "task";
        return String.format("Now you have %d %s in the list", numberOfTask, formOfTask);
    }
}

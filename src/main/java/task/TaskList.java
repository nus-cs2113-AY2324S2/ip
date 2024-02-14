package task;

public class TaskList {
    private int numberOfTask;
    private final Task[] tasks;

    public TaskList() {
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

    public Task getPosAt(int index) {
        if (index <= 0) {
            return null;
        }
        return tasks[index - 1];
    }

    public void markTask(int taskNum) {
        tasks[taskNum - 1].mark();
    }

    public void unmarkTask(int taskNum) {
        tasks[taskNum - 1].unmark();
    }

    public String listTasks() {
        int index;
        String tasksToBeListed = "";

        for (int i = 0; i < numberOfTask; i++) {
            index = i + 1;
            tasksToBeListed += String.format("%d.%s\n", index, tasks[i].toString());
        }
        return tasksToBeListed;
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

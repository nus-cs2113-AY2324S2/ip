public class TaskList {
    private int numberOfTask;
    private final Task[] tasks;

    TaskList() {
        this.numberOfTask = 0;
        this.tasks = new Task[100];
    }

    public void add(Task task) {
        tasks[numberOfTask] = task;
        numberOfTask++;
    }

    public void markTask(int taskNum) {
        if (taskNum > numberOfTask) {
            System.out.println("wrong index!! please try again :(");
            return;
        }
        tasks[taskNum - 1].mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + tasks[taskNum - 1].toString());
    }

    public void unmarkTask(int taskNum) {
        if (taskNum > numberOfTask) {
            System.out.println("wrong index!! please try again :(");
            return;
        }
        tasks[taskNum - 1].unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + tasks[taskNum - 1].toString());
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTask; i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks[i].toString());
        }
    }
}

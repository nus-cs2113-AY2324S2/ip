public class TaskManager {
    public static final int MAX_TASKS = 100;
    private int currIndex;
    private Task[] Tasks;

    public TaskManager() {
        this.currIndex = 0;
        this.Tasks = new Task[MAX_TASKS];
    }

    public void addTask(String taskToAdd) {
        String typeOfTask;
        String taskName;
        if (taskToAdd.contains("todo")) {
            taskName = taskToAdd.substring(5);
            Tasks[currIndex] = new ToDo(taskName);
        } else if (taskToAdd.contains("deadline")) {
            int firstBackslashIndex = taskToAdd.indexOf("/");
            taskName = taskToAdd.substring(9, firstBackslashIndex - 1);
            int byWhenIndex = firstBackslashIndex + 4;
            String byWhen = taskToAdd.substring(byWhenIndex);
            Tasks[currIndex] = new Deadline(taskName, byWhen);
        } else if (taskToAdd.contains("event")) {
            int firstBackslashIndex = taskToAdd.indexOf("/");
            taskName = taskToAdd.substring(6, firstBackslashIndex - 1);
            int fromWhenIndex = firstBackslashIndex + 6;
            int secondBackslashIndex = taskToAdd.indexOf("/", fromWhenIndex + 1);
            String fromWhen = taskToAdd.substring(fromWhenIndex, secondBackslashIndex - 1);
            String toWhen = taskToAdd.substring(secondBackslashIndex + 4);
            Tasks[currIndex] = new Event(taskName, fromWhen, toWhen);
        } else {
            System.out.println("ERROR: Invalid input");
            return;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(Tasks[currIndex]);
        System.out.println("Now you have " + (currIndex+1) + " tasks in the list");
        currIndex++;
    }

    public void markTask(int taskIndex, boolean isDone) {
        Task targetTask = Tasks[taskIndex];
        if (isDone) {
            targetTask.markDone();
            System.out.println("Nice! I've marked this Task as done:");
            System.out.println(targetTask);
        } else {
            targetTask.markNotDone();
            System.out.println("Ok, I've marked this Task as not done yet:");
            System.out.println(targetTask);
        }
    }

    public void listTasks() {
        for (int i = 0; i < currIndex; i++) {
            System.out.println((i+1) + ". " + Tasks[i]);
        }
    }
}

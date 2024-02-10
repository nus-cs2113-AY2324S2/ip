public class TaskManager {
    public static final int MAX_TASKS = 100;
    public static final int TODO_LENGTH= 5;
    public static final int DEADLINE_LENGTH = 9;
    public static final int EVENT_LENGTH = 6;
    public static final int BY_LENGTH = 4;
    public static final int FROM_LENGTH = 6;
    public static final int TO_LENGTH = 4;
    private int currIndex;
    private Task[] tasks;

    public TaskManager() {
        this.currIndex = 0;
        this.tasks = new Task[MAX_TASKS];
    }

    public void addTask(String taskToAdd) {
        String taskType = taskToAdd.split(" ")[0];
        if (taskType.equals("todo")) {
            processToDo(taskToAdd);
        } else if (taskType.equals("deadline")) {
            processDeadline(taskToAdd);
        } else if (taskType.equals("event")) {
            processEvent(taskToAdd);
        } else {
            System.out.println("ERROR: Invalid input");
            return;
        }
        printAndIncrementAfterAddTask();
    }
    
    private void processToDo(String taskToAdd) {
        String taskName;
        taskName = taskToAdd.substring(TODO_LENGTH);
        tasks[currIndex] = new ToDo(taskName);
    }

    private void processDeadline(String taskToAdd) {
        String taskName;
        int firstBackslashIndex = taskToAdd.indexOf("/");
        taskName = taskToAdd.substring(DEADLINE_LENGTH, firstBackslashIndex - 1);
        int byWhenIndex = firstBackslashIndex + BY_LENGTH;
        String byWhen = taskToAdd.substring(byWhenIndex);
        tasks[currIndex] = new Deadline(taskName, byWhen);
    }

    private void processEvent(String taskToAdd) {
        String taskName;
        int firstBackslashIndex = taskToAdd.indexOf("/");
        taskName = taskToAdd.substring(EVENT_LENGTH, firstBackslashIndex - 1);
        int fromWhenIndex = firstBackslashIndex + FROM_LENGTH;
        int secondBackslashIndex = taskToAdd.indexOf("/", fromWhenIndex + 1);
        int toWhenIndex = secondBackslashIndex + TO_LENGTH;
        String fromWhen = taskToAdd.substring(fromWhenIndex, secondBackslashIndex - 1);
        String toWhen = taskToAdd.substring(toWhenIndex);
        tasks[currIndex] = new Event(taskName, fromWhen, toWhen);
    }
    
    private void printAndIncrementAfterAddTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[currIndex]);
        System.out.println("Now you have " + (currIndex+1) + " tasks in the list");
        currIndex++;
    }
    
    public void markTask(int taskIndex, boolean isDone) {
        Task targetTask = tasks[taskIndex];
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
            System.out.println((i+1) + ". " + tasks[i]);
        }
    }
}

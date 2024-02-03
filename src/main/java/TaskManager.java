public class TaskManager {
    public static final int MAX_TASKS = 100;
    public static final int TODO_TASK_NAME_BEGIN_INDEX = 5;
    public static final int DEADLINE_TASK_NAME_BEGIN_INDEX = 9;
    public static final int SKIP_BY_IN_DEADLINE = 4;
    public static final int EVENT_TASK_NAME_BEGIN_INDEX = 6;
    public static final int SKIP_FROM_IN_EVENT = 6;
    public static final int SKIP_TO_IN_EVENT = 4;
    private int currIndex;
    private Task[] tasks;

    public TaskManager() {
        this.currIndex = 0;
        this.tasks = new Task[MAX_TASKS];
    }

    public void addTask(String taskToAdd) {
        if (taskToAdd.contains("todo")) {
            processToDo(taskToAdd);
        } else if (taskToAdd.contains("deadline")) {
            processDeadline(taskToAdd);
        } else if (taskToAdd.contains("event")) {
            processEvent(taskToAdd);
        } else {
            System.out.println("ERROR: Invalid input");
            return;
        }
        printAndIncrementAfterAddTask();
    }
    
    private void processToDo(String taskToAdd) {
        String taskName;
        taskName = taskToAdd.substring(TODO_TASK_NAME_BEGIN_INDEX);
        tasks[currIndex] = new ToDo(taskName);
    }

    private void processDeadline(String taskToAdd) {
        String taskName;
        int firstBackslashIndex = taskToAdd.indexOf("/");
        taskName = taskToAdd.substring(DEADLINE_TASK_NAME_BEGIN_INDEX, firstBackslashIndex - 1);
        int byWhenIndex = firstBackslashIndex + SKIP_BY_IN_DEADLINE;
        String byWhen = taskToAdd.substring(byWhenIndex);
        tasks[currIndex] = new Deadline(taskName, byWhen);
    }

    private void processEvent(String taskToAdd) {
        String taskName;
        int firstBackslashIndex = taskToAdd.indexOf("/");
        taskName = taskToAdd.substring(EVENT_TASK_NAME_BEGIN_INDEX, firstBackslashIndex - 1);
        int fromWhenIndex = firstBackslashIndex + SKIP_FROM_IN_EVENT;
        int secondBackslashIndex = taskToAdd.indexOf("/", fromWhenIndex + 1);
        int toWhenIndex = secondBackslashIndex + SKIP_TO_IN_EVENT;
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

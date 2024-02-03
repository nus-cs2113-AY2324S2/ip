import java.util.LinkedList;
public class TaskManager {
    private LinkedList<Task> taskList;
    private static final String TASK_DONE_STRING = "uwu  marked the task as done: ";
    private static final String TASK_NOT_DONE_STRING = ":( marked the task as not done yet: ";
    private static final String TASK_ADDED_STRING = "added: ";
    private int numTasks;

    public TaskManager() {
        this.numTasks = 0;
        this.taskList = new LinkedList<Task>();
    }

    public void printTaskList() {
        printlnHorizontalLine();
        for(int i = 0; i < numTasks; i++) {
            Task currentTask = taskList.get(i);
            System.out.print(i+1 + ". ");
            currentTask.printTask();
            System.out.println();
        }
        printlnHorizontalLine();
    }
    public String getTaskList(){
        StringBuilder listOfTasks = new StringBuilder();
        for(int i = 0; i < numTasks; i++) {
            Task currentTask = taskList.get(i);
            String lineOfTask = Integer.toString(i + 1) +
                    ". " + currentTask.toString() + System.lineSeparator();
            listOfTasks.append(lineOfTask);
        }
        return listOfTasks.toString();
    }
    public String markTaskAsDone(int stdoutTaskIndex) throws IndexOutOfBoundsException {
        if( stdoutTaskIndex <= 0 || stdoutTaskIndex > numTasks) {
            throw new IndexOutOfBoundsException();
        }
        Task taskToMark = taskList.get(stdoutTaskIndex - 1);
        taskToMark.setDone();
        return TASK_DONE_STRING + taskToMark.toString();
    }
    public String unmarkTask(int stdoutTaskIndex) throws IndexOutOfBoundsException {
        if( stdoutTaskIndex <= 0 || stdoutTaskIndex > numTasks) {
            throw new IndexOutOfBoundsException();
        }
        Task taskToMark = taskList.get(stdoutTaskIndex - 1);
        taskToMark.setNotDone();
        return TASK_NOT_DONE_STRING + taskToMark.toString();
    }
    private void printlnHorizontalLine() {
        String line = "--------------------------------------";
        System.out.println(line);
    }
    public String addTask(String task) {
        Task newTask = new Task(task);
        taskList.add(newTask);
        numTasks++;
        return TASK_ADDED_STRING + task;
    }
}

package duke.tasks;
import java.io.FileWriter;
import java.io.IOException;
public class TaskList {
    /** Array of tasks */
    private Task[] tasks;

    /** Number of tasks */
    private int noOfTasks = 0;
    public TaskList(int taskLength) {
        tasks = new Task[taskLength];
    }

    /**
     * Adds task to the task list
     * @param task The task
     */
    public void addTask(Task task){
        tasks[noOfTasks] = task;
        noOfTasks++;
    }

    /**
     * Checks the task in the task list
     * @param taskNumber The index of the task
     */
    public void checkTask(int taskNumber){
        tasks[taskNumber-1].setTaskStatus(true);
        System.out.println("      " + tasks[taskNumber-1]);
    }

    /**
     * Unchecks the task in the task list
     * @param taskNumber The index of the task
     */
    public void uncheckTask(int taskNumber){
        tasks[taskNumber-1].setTaskStatus(false);
        System.out.println("      " + tasks[taskNumber-1]);
    }

    /**
     * Prints out all the task
     */
    public void listTasks(){
        int taskCount = 0;
        for (Task task: tasks) {
            if (task == null) {
                break;
            }
            taskCount++;
            System.out.println("     " + taskCount +"." + task);

        }
    }

    /**
     * returns the number of tasks in the list
     * @return the number of tasks
     */
    public int getNoOfTasks(){
        return noOfTasks;
    }

    public void saveTaskList() throws IOException {
        FileWriter fw = new FileWriter("src/main/java/duke.txt");

        for (Task task : tasks) {
            if (task instanceof ToDos) {
                fw.write("T/ " + (task.getTaskStatus() ? "1 /" : "0 /") + task.getTask() + System.lineSeparator());
            } else if (task instanceof Events) {
                fw.write("E/ " + (task.getTaskStatus() ? "1 /" : "0 /") + task.getTask() + System.lineSeparator());
            } else if (task instanceof Deadlines) {
                fw.write("D/ " + (task.getTaskStatus() ? "1 /" : "0 /") + task.getTask() + System.lineSeparator());
            }
        }
        fw.close();
    }


}

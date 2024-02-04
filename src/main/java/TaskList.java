import java.util.Arrays;

public class TaskList {
    private Task[] tasks;
    private int numTasks;
    private int numTasksDone;

    public int getNumTasks() {
        return numTasks;
    }

    public Task[] getTaskList() {
        return Arrays.copyOf(tasks, numTasks);
    }

    public int getNumTasksDone() {
        return numTasksDone;
    }

    public Task addTask(String description) {
        tasks[numTasks] = new ToDo(description);
        numTasks += 1;
        return tasks[numTasks - 1];
    }

    public Task addTask(String description, String by) {
        tasks[numTasks] = new Deadline(description, by);
        numTasks += 1;
        return tasks[numTasks - 1];
    }

    public Task addTask(String description, String start, String end) {
        tasks[numTasks] = new Event(description, start, end);
        numTasks += 1;
        return tasks[numTasks - 1];
    }

    public Task markTask(int index, boolean isDone) {
        if(index >= numTasks){
            return null;
        }
        else if (isDone) {
            tasks[index].setDone();
            numTasksDone += 1;
        }
        else {
            tasks[index].setUndone();
            numTasksDone -= 1;
        }
        return tasks[index];
    }

    public TaskList() {
        tasks = new Task[100];
        numTasks = 0;
        numTasksDone = 0;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numTasks; i += 1) {
            result.append("    ").append(i+1).append('.').append(tasks[i].toString());
            if (i != numTasks - 1) {
                result.append('\n');
            }
        }
        return result.toString();
    }
}

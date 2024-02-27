package Chat.tasks;

import Chat.exceptions.RepeatMark;
import Chat.exceptions.RepeatUnmark;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int index) {
        Task task = tasks.get(index);
        try {
            task.markAsDone();
        } catch (RepeatMark e) {
            System.out.println("This task is already marked.");
        }
    }
    public Task getTask(int index){
        Task task = tasks.get(index);
        return task;
    }
    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        try {
            task.markAsNotDone();
        } catch (RepeatUnmark e) {
            System.out.println("This task is already unmarked.");
        }
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public void printList() {
        int index = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            } else {
                System.out.println(index + ". " + task.toString());
                index++;
            }
        }
    }
    public int getSize() {
        return tasks.size();
    }
}

package task;

import format.Formatter;

import java.util.ArrayList;

/**
 * Maintain a list of task with extra functionalities
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    protected Formatter formatter;

    public TaskList() {
        tasks = new ArrayList<>();
        formatter = new Formatter();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public void printTaskList() {
        formatter.printDividingLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i).getIdentity()
                    + tasks.get(i).getStatusIcon() + " " + tasks.get(i));
        }
        formatter.printDividingLine();
    }

    public void printTaskListWithCondition(String condition) {
        int index = 0;
        formatter.printDividingLine();
        System.out.println("\tHere are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.getDescription().contains(condition)) {
                System.out.println("\t" + (index + 1) + "." + task.getIdentity()
                        + task.getStatusIcon() + " " + task);
                index++;
            }
        }
        formatter.printDividingLine();
    }
}

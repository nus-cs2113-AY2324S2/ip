package chris.tasktypes;

import java.util.ArrayList;
public class taskList {
    protected ArrayList<Task> tasks;
    protected int taskCount = 0;
    public taskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public String markTask(String taskNumber){
        int index = Integer.parseInt(taskNumber);
        if (tasks.get(index - 1).markTask()) {
            return "Task marked!";
        } else {
            return "Task unmarked!";
        }
    }

    public Task deleteTask(String taskNumber){
        int index = Integer.parseInt(taskNumber);
        Task deletedTask = tasks.remove(index - 1);
        taskCount--;
        return deletedTask;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public String printTaskList() {
        StringBuilder string = new StringBuilder();
        if (taskCount == 0) {
            return "You currently have no tasks!";
        }
        string.append("Here are your current tasks!");
        int count = 1;
        for (Task i : tasks) {
            string.append("\n");
            string.append(count);
            string.append(". ");
            string.append(i.toString());
            count++;
        }
        return string.toString();
    }

    public taskList find(String keyword) {
        taskList foundTasks = new taskList();
        for (Task i : tasks) {
            if (i.getDescription().contains(keyword)) {
                foundTasks.addTask(i);
            }
        }
        return foundTasks;
    }
}

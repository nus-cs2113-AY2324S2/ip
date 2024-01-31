import java.util.ArrayList;

public class TaskList {
    private int numberOfTasks;
    private ArrayList<Task> tasks;

    public TaskList() {
        numberOfTasks = 0;
        tasks = new ArrayList<Task>();
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }


    public void addTask(String taskDescription) {
        Task userTask = new Task(taskDescription);
        tasks.add(userTask);
        numberOfTasks++;
        System.out.println( "---" + userTask.getDescription() + " has been added to task list!---");
    }

    public void listOut() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            Task currTask = tasks.get(i);
            System.out.println((i + 1) + ". [" + currTask.getStatusIcon() + "]"+ currTask.getDescription());
        }
    }

    public void markTask(int taskId) {
        if (taskId >= 1 && taskId <= numberOfTasks) {
            Task selectedTask = tasks.get(taskId - 1);
            if (selectedTask.getStatus()) {
                System.out.println("Are you blind mate?");
            } else {
                selectedTask.setMark();
                System.out.println("Well done mate! I've marked this task as done:");
                System.out.println("[x] " + selectedTask.getDescription());
            }
        } else {
            System.out.println("Can you not do math, mate?");
        }
    }

    public void unmarkTask(int taskId) {
        if (taskId >= 1 && taskId <= numberOfTasks) {
            Task selectedTask = tasks.get(taskId - 1);
            if (!selectedTask.getStatus()) {
                System.out.println("Are you blind mate?");
            } else {
                selectedTask.setUnmark();
                System.out.println("WHY?! I've marked this task as not done:");
                System.out.println("[ ] " + selectedTask.getDescription());
            }
        } else {
            System.out.println("Can you not do math, mate?");
        }
    }
}

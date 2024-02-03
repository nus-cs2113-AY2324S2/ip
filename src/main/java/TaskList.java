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
        ToDo userTask = new ToDo(taskDescription);
        tasks.add(userTask);
        numberOfTasks++;
        System.out.println( "---" + taskDescription + " has been added to task list!---");
        System.out.println("---Number of Tasks in List: " + numberOfTasks + "---");
    }

    public void addTask(String taskDescription, String by) {
        Deadline userTask = new Deadline(taskDescription, by);
        tasks.add(userTask);
        numberOfTasks++;
        System.out.println( "---" + taskDescription + " has been added to task list!---");
        System.out.println("---Number of Tasks in List: " + numberOfTasks + "---");
    }

    public void addTask(String taskDescription, String from, String to) {
        Event userTask = new Event(taskDescription, from, to);
        tasks.add(userTask);
        numberOfTasks++;
        System.out.println( "---" + taskDescription + " has been added to task list!---");
        System.out.println("---Number of Tasks in List: " + numberOfTasks + "---");
    }

    public void listOut() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            Task currTask = tasks.get(i);
            System.out.println((i + 1) + ". " + currTask);
        }
    }

    public void markTask(int taskId) {
        if (taskId >= 1 && taskId <= numberOfTasks) {
            Task selectedTask = tasks.get(taskId - 1);
            if (selectedTask.getStatus()) {
                System.out.println("Are you blind mate?");
            } else {
                selectedTask.setMark();
                System.out.println("Gnarly mate! I've marked this task as done:");
                System.out.println(selectedTask);
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
                System.out.println(selectedTask);
            }
        } else {
            System.out.println("Can you not do math, mate?");
        }
    }
}

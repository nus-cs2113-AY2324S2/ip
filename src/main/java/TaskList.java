import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Task task) {
        tasks.add(task);
        indicateNewTask(tasks.get(tasks.size() - 1), tasks.size());
    }
    public void deleteTask(int indexTask) {
        Task removedTask = tasks.get(indexTask - 1);
        tasks.remove(removedTask);
        System.out.println("Command received. I've removed this task:");
        System.out.println((indexTask) + ". " + "[" + removedTask.getStatusIcon() + "]" + removedTask.description);
        System.out.println("Currently you have " + tasks.size() + " tasks in the list below.");
    }


    public void markTaskAsCompleted(int indexTask) {

        tasks.get(indexTask - 1).markAsCompleted();

        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println((indexTask) + ". " + "[" + tasks.get(indexTask - 1).getStatusIcon() + "]" + tasks.get(indexTask - 1).description);
        System.out.println("____________________________________________________________");
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {

            System.out.println((i + 1) + ". " + " " + "[" + tasks.get(i).typeOfTask + "]" + "[" + tasks.get(i).getStatusIcon() + "]" + tasks.get(i).description);
        }
        System.out.println("____________________________________________________________");
    }

    public void markTaskAsNotCompleted(int indexTask) {

        tasks.get(indexTask - 1).markAsNotCompleted();

        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println((indexTask) + ". " + "[" + tasks.get(indexTask - 1).getStatusIcon() + "]" + tasks.get(indexTask - 1).description);
        System.out.println("____________________________________________________________");
    }
    public void indicateNewTask(Task newTask, int currentNumberOfTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Well done, you've added a new task: ");
        System.out.println("[" + newTask.typeOfTask + "]" + "[" + newTask.getStatusIcon() + "]" + newTask.description);
        System.out.println("Currently you have " + currentNumberOfTasks + " task(s) in your list!");
        System.out.println("____________________________________________________________");
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }
    public int size() {
        return tasks.size();
    }
}

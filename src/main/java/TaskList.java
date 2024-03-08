import java.util.ArrayList;
public class TaskList {
    public static ArrayList<Task> tasks;

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
        Ui.deleteTaskDisplay(indexTask, removedTask);
    }

    public void markTaskAsCompleted(int indexTask) {
        tasks.get(indexTask - 1).markAsCompleted();
        Ui.markedTasksAsCompletedDisplay(indexTask);
    }

    public void listTasks() {
        Ui.listTasksDisplay();
    }

    public void markTaskAsNotCompleted(int indexTask) {
        tasks.get(indexTask - 1).markAsNotCompleted();
        Ui.markedTasksAsNotCompletedDisplay(indexTask);
    }

    public void indicateNewTask(Task newTask, int currentNumberOfTasks) {
        Ui.indicateNewTaskDisplay(newTask, currentNumberOfTasks);
    }

    public void findTasks(String keyword, ArrayList<Task> tasksList) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            Ui.noTasksFoundDisplay();
        } else {
            Ui.showTasksFoundDisplay(matchingTasks);
        }
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
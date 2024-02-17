import java.util.ArrayList;

public class ListKeeper {
    private ArrayList<Task> tasks;

    public ListKeeper() {
        this.tasks = new ArrayList<>();
    }

    public void addToList(Task task) {
        this.tasks.add(task);
        // Provide feedback to user
        System.out.println("I have added this task:");
        System.out.println(task);
    }

    public void deleteTask(int taskIndex) {
        Task removedTask = this.tasks.get(taskIndex - 1);
        this.tasks.remove(taskIndex - 1);
        // Provide feedback
        System.out.println("I have removed this task:");
        System.out.println(removedTask);
    }

    public void printList() {
        System.out.println("You have " + this.tasks.size() + " tasks");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + ". " + this.tasks.get(i));
        }
    }

    public boolean isValidTaskIndex(int inputIndex) {
        return inputIndex >= 1 && inputIndex <= this.tasks.size();
    }

    public void processMark(int inputIndex, boolean isCompleted) {
        Task task = this.tasks.get(inputIndex - 1);
        task.mark(isCompleted);
    }
}

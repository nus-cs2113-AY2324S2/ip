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

    public void printList() {
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

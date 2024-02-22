import tasks.Task;

import java.util.ArrayList;
import java.io.IOException;

public class ListKeeper {
    private ArrayList<Task> tasks;

    public ListKeeper() {
        this.tasks = new ArrayList<>();
    }

    public void addToList(Task task) {
        this.tasks.add(task);
        // Provide feedback to user
        System.out.println("Added task: " + task);
    }

    public void deleteTask(int taskIndex) {
        Task removedTask = this.tasks.get(taskIndex - 1);
        this.tasks.remove(taskIndex - 1);
        // Provide feedback
        System.out.println("Removed task: " + removedTask);
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

    public void saveTasksData(SaveManager saveManager) {
        try {
            saveManager.clearFile();
            if (this.tasks.isEmpty()) {
                return;
            }
            for (Task task : this.tasks) {
                System.out.println("Saving: " + task);
                saveManager.writeToFile(task.getStringRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Data could not be saved");
        }
    }
}

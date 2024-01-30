import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> toDoList = new ArrayList<>();

    public void addTask(String command) {
        Task newTask = new Task(command);
        toDoList.add(newTask);
        Gene.printLineSeparation();
        System.out.println("added: " + newTask.description);
        Gene.printLineSeparation();
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= toDoList.size();
    }

    public void markTaskAsDone(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            Task task = toDoList.get(taskNumber - 1);
            task.markAsDone();
            Gene.printLineSeparation();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("   " + task.getStatusIcon() + " " + task.description);
            Gene.printLineSeparation();
        } else {
            System.out.println("Invalid task number. Please provide a valid task number.");
        }
    }

    public void markTaskAsNotDone(int taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            Task task = toDoList.get(taskNumber - 1);
            task.markAsNotDone();
            Gene.printLineSeparation();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("   " + task.getStatusIcon() + " " + task.description);
            Gene.printLineSeparation();
        } else {
            System.out.println("Invalid task number. Please provide a valid task number.");
        }
    }

    public void printListItems() {
        Gene.printLineSeparation();
        System.out.println("Here are the items in your list:");
        for (int i = 0; i < toDoList.size(); i++) {
            Task task = toDoList.get(i);
            System.out.println((i + 1) + ". " + task.getStatusIcon() + " " + task.description);
        }
        Gene.printLineSeparation();
    }

}

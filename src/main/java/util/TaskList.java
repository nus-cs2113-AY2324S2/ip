package util;

import java.util.ArrayList;
import tasks.Task;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected Storage storage = new Storage();

    public TaskList() {
        this.storage.readFromFile(this);
    }

    public void addTask(Task task) {
        System.out.println("A task I see. I have added it.");
        Ui.printTask(task);
        this.tasks.add(task);
        this.storage.saveToFile(this);
    }

    public void addTaskStealth(Task task) {
        this.tasks.add(task);
        this.storage.saveToFile(this);
    }

    public void deleteTask(int index) {
        try {
            System.out.println("Should all acquaintance be forgot...");
            Ui.printTask(this.tasks.get(index - 1));
            this.tasks.remove(index - 1);
            System.out.println(this.tasks.size() + " task(s) remain.");
        } catch (IndexOutOfBoundsException e) {
            Ui.indexOutOfBoundsMessage();
        }
        this.storage.saveToFile(this);
    }

    public void listTasks() {
        if (this.tasks.isEmpty()) {
            Ui.emptyListMessage();
        }

        for(int i = 0; i < this.tasks.size(); ++i) {
            System.out.print(i + 1 + ".");
            System.out.println(this.tasks.get(i));
        }

    }

    public void markTask(int index) {
        try {
            (this.tasks.get(index - 1)).markAsDone();
            System.out.println("Congratulations. I have marked this task as finished:");
            Ui.printTask(this.tasks.get(index - 1));
        } catch (IndexOutOfBoundsException var3) {
            Ui.indexOutOfBoundsMessage();
        }

        this.storage.saveToFile(this);
    }

    public void unmarkTask(int index) {
        try {
            (this.tasks.get(index - 1)).markAsUndone();
            System.out.println("Do not neglect your duties. I have marked this task as unfinished:");
            Ui.printTask(this.tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            Ui.indexOutOfBoundsMessage();
        }

        this.storage.saveToFile(this);
    }

    public void findTask(String keyFind) {
        try {
            System.out.println("Here is what you are looking for: ");
            int count = 1;
            for (Task task : this.tasks) {
                if (task.getDescription().contains(keyFind)) {
                    System.out.println(count + ". " + task);
                    ++count;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.indexOutOfBoundsMessage();
        }
    }
}

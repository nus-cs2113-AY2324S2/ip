package bossman.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final String SEP = "____________________________________________________________";
    private final List<Task> TASKS;

    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    public void addTask(Task task) {
        TASKS.add(task);
    }

    public void printTasks() {
        System.out.println("Todo List:");

        int counter = 1;

        for (Task t: TASKS) {
            System.out.print(counter + ". ");
            t.printTask();
            counter += 1;
            System.out.println();
        }

        System.out.println(SEP);
    }

    public void markTask(int num)  {
        if (isValidTask(num)) {
            this.TASKS.get(num - 1).setMark();
            System.out.println("Nice! I've marked this task as done:");
            echo(num);
        } else {
            System.out.println("Selected index out of range" + "\n" + SEP);
        }
    }

    public void unmarkTask(int num) {
        if (isValidTask(num)) {
            this.TASKS.get(num - 1).setUnmark();
            System.out.println("OK, I've marked this task as not done yet:");
            echo(num);
        } else {
            System.out.println("Selected index out of range" + "\n" + SEP);
        }
    }

    private void echo(int num) {
        this.TASKS.get(num - 1).printTask();
        System.out.println("\n" + SEP);
    }

    public boolean isValidTask(int num) {
        return num > 0 && num <= TASKS.size();
    }

    public int getTaskListSize() {
        return TASKS.size();
    }
}



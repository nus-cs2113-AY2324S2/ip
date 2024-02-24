package bossman.task;

import bossman.ui.Ui;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> TASKS;

    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    public void addTask(Task task) {
        TASKS.add(task);
    }

    public void findTask(String taskDescription) {
        String message = "Matching tasks: " + System.lineSeparator();

        int counter = 1;

        for (Task t: TASKS) {
            if (t.getTask().contains(taskDescription)) {
                Ui.printMessageNoSepSameLine(counter + ". ");
                t.printTask();
                counter += 1;
                Ui.printMessageNoSepNewLine("");
            }
        }

        Ui.printSep();
    }

    public void removeTask(int taskId) throws IndexOutOfBoundsException {
        if (isValidTask(taskId)) {
            Task toDeleteTask = TASKS.get(taskId - 1);

            Ui.printMessageNoSepSameLine("Removed task: ");
            toDeleteTask.printTask();
            Ui.printNewLineWithSep();

            TASKS.remove(taskId - 1);
        } else {
            Ui.printMessageWithSepNewLine("No such task");
        }
    }

    public List<Task> getTasks() {
        return TASKS;
    }

    public void printTasks() {
        Ui.printMessageNoSepNewLine("Todo List:");

        int counter = 1;

        for (Task t: TASKS) {
            Ui.printMessageNoSepSameLine(counter + ". ");
            t.printTask();
            counter += 1;
            Ui.printMessageNoSepNewLine("");
        }

        Ui.printSep();
    }

    public void markTask(int taskId)  {
        if (isValidTask(taskId)) {
            this.TASKS.get(taskId - 1).setMark();
            Ui.printMessageNoSepNewLine("Nice! I've marked this task as done:");
            echo(taskId);
        } else {
            Ui.printMessageWithSepNewLine("Selected index out of range");
        }
    }

    public void unmarkTask(int taskId) {
        if (isValidTask(taskId)) {
            this.TASKS.get(taskId - 1).setUnmark();
            Ui.printMessageNoSepNewLine("OK, I've marked this task as not done yet:");
            echo(taskId);
        } else {
            Ui.printMessageWithSepNewLine("Selected index out of range");
        }
    }

    private void echo(int taskId) {
        this.TASKS.get(taskId - 1).printTask();
        Ui.printNewLineWithSep();
    }

    public boolean isValidTask(int taskId) {
        return taskId > 0 && taskId <= TASKS.size();
    }

    public int getTaskListSize() {
        return TASKS.size();
    }
}



package chris.tasktypes;

import chris.customexceptions.illegalTaskNumberInput;

import java.util.ArrayList;
public class taskList {
    protected static ArrayList<Task> tasks;
    protected static int taskCount = 0;
    public taskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTask(String taskNumber) throws illegalTaskNumberInput {
        if (taskNumber.trim().isEmpty()) {
            throw new illegalTaskNumberInput();
        }
        try {
            int index = Integer.parseInt(taskNumber);
            try {
                if (tasks.get(index - 1).markTask()) {
                    System.out.println("Task marked!");
                } else {
                    System.out.println("Task unmarked!");
                }
            } catch (IndexOutOfBoundsException e) {
                throw new illegalTaskNumberInput();
            }
        } catch (NumberFormatException e) {
            throw new illegalTaskNumberInput();
        }
    }

    public Task deleteTask(String taskNumber) throws illegalTaskNumberInput {
        if (taskNumber.trim().isEmpty()) {
            throw new illegalTaskNumberInput();
        }
        try {
            int index = Integer.parseInt(taskNumber);
            try {
                Task deletedTask = tasks.remove(index - 1);
                taskCount--;
                return deletedTask;
            } catch (IndexOutOfBoundsException e) {
                throw new illegalTaskNumberInput();
            }
        } catch (NumberFormatException e) {
            throw new illegalTaskNumberInput();
        }
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void printTaskList() {
        for (Task i : tasks) {
            System.out.println(i);
        }
    }
}

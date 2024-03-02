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

    public void markTask(String taskNumber) throws illegalTaskNumberInput {
        if (taskNumber.trim().isEmpty()) {
            throw new illegalTaskNumberInput();
        }
        try {
            int index = Integer.parseInt(taskNumber);
            try {
                if (tasks.get(index).markTask()) {
                    System.out.println("chris.tasktypes.Task marked!");
                } else {
                    System.out.println("chris.tasktypes.Task unmarked!");
                }
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

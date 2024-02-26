package Alexis.task;

import Alexis.console.Ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int numberOfTasks;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addToTaskList(Task task) {
        tasks.add(task);
        numberOfTasks++;
        printAddTaskMessage(task);
    }

    public void addToTaskListFromFIle(Task task) {
        tasks.add(task);
        numberOfTasks++;
    }

    private void printAddTaskMessage(Task task) {
        System.out.println(Ui.ADD_MESSAGE);
        System.out.print("\t" + task);
        System.out.printf(Ui.LIST_UPDATE_MESSAGE, numberOfTasks);
    }

    public void addTask(TaskType taskType, String input) {
        switch (taskType) {
        case TODO:
        default:
            ToDo toDo = ToDo.getToDo(input);
            addToTaskList(toDo);
            break;
        case DEADLINE:
            Deadline deadline = Deadline.getDeadline(input);
            if (deadline != null) {
                addToTaskList(deadline);
            }
            break;
        case EVENT:
            Event event = Event.getEvent(input);
            if (event != null) {
                addToTaskList(event);
            }
            break;
        }
    }

    public void markTask(String input) {
        Integer number = extractInt(input);
        if (isNotValid(number)) {
            return;
        }
        Task task = tasks.get(number - 1);
        task.markAsDone();
        System.out.println(Ui.MARK_DONE_MESSAGE);
        System.out.print("\t");
        printTask(task, -1);
    }

    public void unmarkTask(String input) {
        Integer number = extractInt(input);
        if (isNotValid(number)) {
            return;
        }
        Task task = tasks.get(number - 1);
        task.markAsNotDone();
        System.out.println(Ui.MARK_UNDONE_MESSAGE);
        System.out.print("\t");
        printTask(task, -1);
    }

    private boolean isNotValid(Integer number) {
        if (number == null) {
            System.out.println(Ui.MISSING_TASK_INDEX_MESSAGE);
            return true;
        }
        if (number <= 0 || number > numberOfTasks) {
            System.out.println(Ui.INVALID_TASK_INDEX_MESSAGE);
            return true;
        }
        return false;
    }

    public void printTasks() {
        int taskIndex = 1;
        System.out.println(Ui.LIST_MESSAGE);
        for (Task task : tasks){
            printTask(task, taskIndex);
            taskIndex++;
        }
    }

    public void printTask(Task task, int index) {
        if (index < 0) {
            System.out.printf(task.toString());
        } else {
            System.out.printf("%d.%s", index, task.toString());
        }
    }

    private Task removeTask(int number) {
        Task task = tasks.remove(number - 1);
        numberOfTasks--;
        return task;
    }

    public void deleteFromTaskList(String input) {
        Integer number = extractInt(input);
        if (isNotValid(number)) {
            return;
        }
        Task task = removeTask(number);
        printDeleteTaskMessage(task);
    }

    private void printDeleteTaskMessage(Task task) {
        System.out.println(Ui.REMOVE_MESSAGE);
        System.out.print("\t");
        printTask(task, -1);
        System.out.printf(Ui.LIST_UPDATE_MESSAGE, numberOfTasks);
    }

    public Integer extractInt(String input) {
        try {
            String number = input.replaceAll("[^-0-9]", "");
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

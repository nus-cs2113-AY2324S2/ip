package daisy.tasklist;

import daisy.task.Deadline;
import daisy.task.Event;
import daisy.task.Task;
import daisy.task.Todo;
import daisy.ui.Ui;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void listTasks() {
        for (Task task : tasks) {
            System.out.println((tasks.indexOf(task) + 1) + "." + task);
        }
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markDone(int taskNo, Ui ui) {
        getTask(taskNo).setDone();
        ui.printMarked(getTask(taskNo));
    }

    public void markUndone(int taskNo, Ui ui) {
        tasks.get(taskNo).setUndone();
        ui.printUnmarked(tasks.get(taskNo));
    }

    public void deleteTask(int taskNo) {
        System.out.println("I see. The following task will be removed from your list:\n" + tasks.get(taskNo));
        tasks.remove(taskNo);
        outputSize();
    }

    public int getSize() {
        return tasks.size();
    }

    public void outputSize() {
        System.out.println(String.format("Now you have %d tasks in your todo list.", getSize()));
    }

    public void addTask(Task task, boolean printOut) {
        tasks.add(task);

        if (printOut) {
            System.out.println("Task received! The following has been added to your list of todos:\n" + task);
            outputSize();
        }
    }

    public void createTodo(String taskName, boolean printOut, boolean setDone) {
        Todo newTodo = new Todo(taskName);
        if (setDone) {
            newTodo.setDone();
        }
        addTask(newTodo, printOut);
    }

    public void createDeadline(String taskName, String taskDeadline, boolean printOut, boolean setDone) {
        Deadline newDeadline = new Deadline(taskName,taskDeadline);
        if (setDone) {
            newDeadline.setDone();
        }
        addTask(newDeadline, printOut);
    }

    public void createEvent(String taskName, String fromDate, String toDate, boolean printOut, boolean setDone) {
        Event newEvent = new Event(taskName, fromDate, toDate);
        if (setDone) {
            newEvent.setDone();
        }
        addTask(newEvent, printOut);
    }

}

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> listOfTasks = Storage.loadListOfTasks();;

    public static void addTask(Task task) {
        listOfTasks.add(task);
        Storage.writeAllTasksToStorage(listOfTasks);

        Ui.printAddedTask(task, listOfTasks);
    }

    public static void deleteTask(int taskIndex) {
        try {
            Task taskToRemove = listOfTasks.get(taskIndex);
            listOfTasks.remove(taskIndex);
            Storage.writeAllTasksToStorage(listOfTasks);

            Ui.printDeletedTask(taskToRemove, listOfTasks);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }

    public static void changeTaskStatus(boolean isDone, int taskIndex) {
        // listOfTasks is indexed from 0.
        // taskIndex is indexed from 1, as how the user sees the list is ordered
        Task task = listOfTasks.get(taskIndex - 1);

        task.setStatus(isDone);
        Storage.writeAllTasksToStorage(listOfTasks);
        Ui.printChangedTaskStatus(isDone, task);
    }

    public static ArrayList<Task> getTaskList() {
        return listOfTasks;
    }
}

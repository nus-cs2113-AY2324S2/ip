public class Quokka {
    private static TaskList taskList = new TaskList();
    private static final int MAX_TASKS = 100;


    public static void main(String[] args) {

        Storage.loadTasksFromFile(taskList, MAX_TASKS);
        Ui.start(taskList);

    }
}

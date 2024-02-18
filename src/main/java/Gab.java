public class Gab {
    //public static final int TASK_LIST_LENGTH = 100;
    //public static Task[] taskList = new Task[TASK_LIST_LENGTH]; //array of Task instances
    public TaskList taskList; //taskList arraylist
    public static boolean isExit;
    public Gab() {
        isExit = false;
        taskList = new TaskList();
    }

    public static void main(String[] args) {
        Gab gab = new Gab();
        Ui.printWelcome();

        while (!isExit) {
            try {
                String task = Ui.getTask();
                Command command = CommandHandler.checkCommand(task, gab.taskList);
                command.execute(task, gab.taskList);
            } catch (GabException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}



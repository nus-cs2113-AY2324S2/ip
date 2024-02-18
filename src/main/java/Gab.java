public class Gab {
    public static final int TASK_LIST_LENGTH = 100;
    public static Task[] taskList = new Task[TASK_LIST_LENGTH]; //array of Task instances
    public static boolean isExit;
    public Gab() {
        isExit = false;
    }

    public static void main(String[] args) {
        Ui.printWelcome();

        while (!isExit) {
            try {
                String task = Ui.getTask();
                Command command = CommandHandler.checkCommand(task, taskList);
                command.execute(task, taskList);
            } catch (GabException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}



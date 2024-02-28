public class Dobby {

    public static void main(String[] args) {

        Ui.showLogo();
        Ui.greetUser();

        TaskList taskmanager = new TaskList();
        FileManager fileManager = new FileManager();
        fileManager.loadTasksFromFile(taskmanager);
        ProcessCommands parse = new ProcessCommands(taskmanager);
        parse.userCommand();
    }
    
}

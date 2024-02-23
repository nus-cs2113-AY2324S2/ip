public class Dobby {

    public static void main(String[] args) {
        String logo = " _____        _     _           \n"
                + "|  __ \\      | |   | |\n"
                + "| |  | | ___ | |__ | |__  _   _ \n"
                + "| |  | |/ _ \\| '_ \\| '_ \\| | | |\n"
                + "| |__| | |_| | |_) | |_) | |_| |\n"
                + "|_____/ \\___/|_.__/|_.__/ \\__, |\n"
                + "                           __/ |\n"
                + "                          |___/ \n";
        System.out.println(logo);
        System.out.println("Dobby say's Hello!\nHow can Dobby help?\n");

        TaskList taskmanager = new TaskList();
        FileManager fileManager = new FileManager();
        fileManager.loadTasksFromFile(taskmanager);
        taskmanager.userCommand();
    }
    
}

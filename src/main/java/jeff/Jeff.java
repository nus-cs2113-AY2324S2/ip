package jeff;

public class Jeff {
    public static void main(String[] args) {
        Printer.printWelcome();
        FileManager.loadTasks();
        UserInterface.handleUserInput();
    }
}

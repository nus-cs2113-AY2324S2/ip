package jeff;

public class Jeff {
    public static void main(String[] args) {
        Printer.printWelcome();
        Storage.loadTasks();
        Ui.handleUserInput();
    }
}

package natsu;

import natsu.util.TaskManager;

import static natsu.util.Printer.printWelcomeMessage;

public class Natsu {
    public static void main(String[] args) {
        printWelcomeMessage();
        new TaskManager();
    }
}

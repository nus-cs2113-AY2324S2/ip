package alexis;

import alexis.console.Ui;
import alexis.console.Storage;
import alexis.task.TaskList;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Alexis {
    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        try {
            Storage.readFromFile(tasks);
        }
        catch (FileNotFoundException ignored) {
        }
        Scanner in = new Scanner(System.in);

        Ui.printWelcomeMessage();
        Ui.processUserInput(tasks, in);
        Ui.printGoodbyeMessage();
    }
}

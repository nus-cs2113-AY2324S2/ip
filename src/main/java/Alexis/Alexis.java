package Alexis;

import Alexis.console.Console;
import Alexis.console.Storage;
import Alexis.task.TaskList;

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

        Console.printWelcomeMessage();
        Console.processUserInput(tasks, in);
        Console.printGoodbyeMessage();
    }
}

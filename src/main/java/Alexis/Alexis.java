package Alexis;

import Alexis.console.Console;
import Alexis.task.TaskList;

import java.util.Scanner;

public class Alexis {
    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        Scanner in = new Scanner(System.in);

        Console.printWelcomeMessage();
        Console.processUserInput(tasks, in);
        Console.printGoodbyeMessage();
    }
}

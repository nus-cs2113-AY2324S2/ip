package chris;

import chris.commands.Command;
import chris.customexceptions.*;
import chris.tasktypes.taskList;

import java.util.Scanner;

public class Chris {
    protected static Storage storage;
    protected static taskList tasks;
    public static void main(String[] args) {
        storage = new Storage("data.txt");
        tasks = storage.loadTasks();
        printLine();
        System.out.println("Hello, I am Chris");
        menu();
        storage.saveTasks(tasks);
        printLine();
    }

    public static void printLine() {
        System.out.println("------------------------------------------------");
    }

    public static void menu() {
        boolean done = false;
        Scanner in = new Scanner(System.in);
        while (!done) {
            System.out.println("What can I do for you?");
            printLine();
            String input = in.nextLine();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks);
                done = command.quit();
            } catch (customExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

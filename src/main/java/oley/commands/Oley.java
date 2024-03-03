package oley.commands;

import oley.tasks.TaskList;

public class Oley {
    public static TaskList tasks;

    public static void initialise() {
        Ui.printGreeting();
        tasks = new TaskList();
        String file = "./data/Oley.txt";
        Storage.load(tasks, file);
    }

    public static void run() {
        Command instructions = new Command(false);
        while (!instructions.isExit()) {
            instructions.execute(tasks);
        }
    }

    public static void main(String[] args) {
        initialise();
        run();
    }
}

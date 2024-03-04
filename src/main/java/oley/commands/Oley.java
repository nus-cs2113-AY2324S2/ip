package oley.commands;

import oley.tasks.TaskList;

/**
 * Represents the main body of the chatbot Oley, including initialising and reading in commands to be executed.
 */
public class Oley {
    /**
     * Represents task list that contains the existing tasks.
     */
    public static TaskList tasks;

    /**
     * Initialise Oley by printing greetings and loading all tasks from the data file to the task list.
     */
    public static void initialise() {
        Ui.printGreeting();
        tasks = new TaskList();
        String file = "./data/Oley.txt";
        Storage.load(tasks, file);
    }

    /**
     * Iterate to read in user's commands and execute them, until isExit equals to true.
     */
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

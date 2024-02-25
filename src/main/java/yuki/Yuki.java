package yuki;

import yuki.data.Storage;
import yuki.task.TaskList;
import yuki.ui.UI;

import java.io.IOException;

public class Yuki {

    private TaskList taskData;

    private static UI ui;

    public Yuki(String filePath) {
        ui = new UI();
        Storage storage = new Storage(filePath);
        taskData = new TaskList(storage.loadData());
    }

    public void run() {
        ui.greetUser();
        runCommandLoopUntilExitCommand();
        ui.sayByeToUser();
    }

    /**
     * Main execution loop.
     * Runs until the user enters "exit" into the command line.
     */
    public void runCommandLoopUntilExitCommand() {
        String userInput = ui.getUserInput();
        String parsedCommand = InputParser.parseCommand(userInput);

        while (!parsedCommand.equals(Constants.EXIT_COMMAND)) {
            taskData.executeCommand(parsedCommand, userInput);
            userInput = ui.getUserInput();
            parsedCommand = InputParser.parseCommand(userInput);
        }
        try {
            Storage.writeFile(taskData.getTaskData());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        new Yuki("data/tasks.txt").run();
    }
}

import dukeRobot.Commands.*;
import dukeRobot.Tools.*;
import dukeRobot.Tasks.*;

import java.io.*;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath,ui);
        tasks = new TaskList();
        try {
            storage.FileLoader();
            tasks = storage.getTasks();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                ui.showLine(); // show the divider line ("_______")
                Parser parser = new Parser(fullCommand,ui);
                CommandBinding commandBinding = new CommandBinding(parser,tasks,ui,storage);
                commandBinding.execute();
                isExit = commandBinding.getCommand().isExit();
            } catch (IOException e) {
                ui.showIOException();
            } finally {
                ui.showLine();
            }
        }
    }



    public static void main(String[] args) {
        new Duke("/Users/celinelam/Desktop/nus/course/CS2113/ip/src/main/java/dukeRobot/Output.txt").run();
    }
}


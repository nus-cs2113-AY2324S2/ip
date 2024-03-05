import commands.Command;
import commands.ExitCommand;
import exceptions.KikuException;
import parsers.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class KikuBot {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private static final String FILE_PATH = "./data/Kiku.txt";
    private static final String HORIZONTAL = "____________________________________________________________";

    public KikuBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.errorMessage("File not found. Starting with an empty task list :)");
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.greetingMessage();

        boolean isExit = false;
        while(!isExit) {
            String userInput = ui.readCommand();
            try {
                Command command = Parser.parseCommand(userInput);
                command.execute(tasks, ui, storage);
                if(command instanceof ExitCommand) {
                    isExit = true;
                }
                System.out.println(HORIZONTAL);
            } catch (KikuException e) {
                ui.errorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        KikuBot kikuBot = new KikuBot(FILE_PATH);
        kikuBot.run();
    }
}
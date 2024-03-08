import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Bot {
    private final String filePath = "./data/Venti.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Command command;

    public Bot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.createFileIfNotExist();
        if (storage.loadTasksFromFile().isEmpty())
        {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        else {
            tasks = new TaskList(storage.loadTasksFromFile());
            System.out.println("There are "+tasks.size()+" tasks in the list.");
        }
        command = new Command();

    }

    protected void run() {
        ui.showWelcome();
        ui.printLine();
        boolean isExit = false;
        while(!isExit)
        {
            try{
                String fullCommand = ui.readCommand();
                ui.printLine();
                if (fullCommand.equals("bye")) {
                    isExit = true;
                }
                command.setCommand(fullCommand);
                command.execute(this.tasks,this.ui,this.storage);
            } catch (BotException e) {
                ui.printLine();
                System.out.println("OOPS!!! " + e.getMessage());
                ui.printLine();
            }
        }

    }

}


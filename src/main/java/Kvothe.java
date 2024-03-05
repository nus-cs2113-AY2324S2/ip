import kvothe.TaskList;
import kvothe.command.ListCommand;
import kvothe.exception.KvotheException;
import kvothe.exception.WrongArgumentsException;
import kvothe.task.*;
import kvothe.command.Command;
import kvothe.Parser;
import kvothe.Storage;
import kvothe.Ui;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main class of the application
 */
public class Kvothe {

    private static final String FILEPATH = "data/kvothe.txt";


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Kvothe object with the default file path.
     * @throws KvotheException if there is an error creating the Storage
     */
    public Kvothe() throws KvotheException{

        ui = new Ui();
        storage = new  Storage(FILEPATH);
        tasks = new TaskList(storage.loadFromFile());
    }

    /**
     * Runs the application. Shows the welcome message and processes the commands
     * until the exit command is given. Finally, shows the goodbye message.
     */
    public void run() {

        ui.showWelcome();
        ui.echo("Here are the tasks in your list from the previous time:", false, false);
        new ListCommand().execute(tasks, ui, storage);
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();

                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KvotheException e) {
                ui.echo(e.getMessage());
            }
        }

        ui.showGoodbye();


    }


    public static void main(String[] args) {

        try{
            Kvothe app = new Kvothe();
            app.run();
        } catch (KvotheException e) {
            System.out.println("Error initiating Kvothe: " + e.getMessage());
            return;
        }

    }

}
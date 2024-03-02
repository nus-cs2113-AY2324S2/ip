package porato;

import porato.tasks.TaskList;
import java.io.FileNotFoundException;

public class Porato {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Porato(){
        parser = new Parser();
        taskList = new TaskList();
        storage = new Storage("porato.txt");
        ui = new Ui();
        // Loads porato.txt file
        try{
            storage.loadFile(taskList);
        } catch (FileNotFoundException e){
            ui.printFileNotFound();
        } catch (PoratoException e) {
            ui.printPoratoError(e);
        }
    }


    /**
     * Runs the Poratobot
     */
    public void runBot(){
        // Prints greeting when user starts the programme
        ui.printGreeting();
        // Reads the inputs from the user
        parser.readInputs(ui, taskList, storage);
        // Exits programme when user says bye
        ui.printGoodbye();
    }
    public static void main(String[] args) {
        // Runs the bot
        new Porato().runBot();
    }
}


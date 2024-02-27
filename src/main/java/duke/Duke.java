package duke;

import duke.tasks.TaskList;
import java.io.FileNotFoundException;
public class Duke {
    // Creates a class of TaskList
    private TaskList taskList;
    // Creates a class of Storage
    private Storage storage;
    // Creates a class of Ui
    private Ui ui;
    // Creates a class of Parser
    private Parser parser;

    public Duke(){
        parser = new Parser();
        taskList = new TaskList();
        storage = new Storage("duke.txt");
        ui = new Ui();
        // Loads Duke.txt file
        try{
            storage.loadFile(taskList);
        } catch (FileNotFoundException e){
            ui.printFileNotFound();
        } catch (DukeException e) {
            ui.printDukeError(e);
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
        new Duke().runBot();
    }
}


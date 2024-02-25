import Commands.Excution;
import DukeException.Command_Not_Exist;
import DukeException.No_Description;

import java.util.ArrayList;
import java.io.IOException;

import Task.Event;
import Task.Todo;
import Task.Deadline;
import Task.Task;
import Utils.Parser;
import Utils.Storage;
import Utils.Ui;

public class Duke {
    private Storage storage;
    private ArrayList<Task> tasks_list;
    private Ui ui;
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks_list = storage.loadStatus();
        }catch(IOException e){
            System.out.println("IOExeption occurs");
        }
    }
    public static void run() {
        //init the parameters to be used
        Duke duke = new Duke();
        duke.ui.showWelcome();
        ArrayList<Task> tasksList = duke.tasks_list;
        boolean isExit = false;

        while (!isExit) //main loop of the chat bot
        {
            try {
                String instruction = duke.ui.readCommand();
                duke.ui.printLine();
                Parser parser = new Parser(instruction);
                Excution excution = new Excution(parser, tasksList);
                excution.runCommand();
                isExit = excution.isExit(parser);
            }catch(Command_Not_Exist e){
                System.out.println("\tInput Error: Command does not exist!");
            }catch (IOException e){
                throw(new RuntimeException());
            }catch (IndexOutOfBoundsException e){
                System.out.println("\tSorry, you should give a more detailed description of your command.");
            }
            duke.ui.printLine();
        }
    }

    public static void main(String args[]){
        Duke duke = new Duke();

        duke.run();

    }
}

package Logic;

import java.io.Serializable;
import Templates.*;

public class Mario implements Serializable {

    private TaskList taskList;
    private Storage storage;
    private String filename;

    public Mario(String filename) {
        this.storage = new Storage();
        this.taskList = storage.loadMario(filename);
        this.filename = filename;
    }

    // public void run(){
    //     ui.showWelcome();
    //     boolean isExit = false;
    //     while (!isExit) {
    //         try{
    //             String fullCommand = ui.readCommand();
    //             BaseCommand command = Parser.parse(fullCommand);
    //             command.execute(ui, taskList);
    //             isExit = command.isExit();
    //         } catch (Exception e){
    //             ui.printError(e);
    //         }finally{
    //             storage.saveMario(taskList, mario_filename);
    //         }
    //     }
    // }

    public void saveMario(){
        this.storage.saveMario(taskList, this.filename);
    }

    public TaskList geTaskList(){return this.taskList;}

}

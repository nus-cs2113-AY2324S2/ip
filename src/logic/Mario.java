package logic;

import templates.TaskList;
import command.BaseCommand;

public class Mario{
    private static String MARIO_FILEPATH = "Mario.txt";
    private TaskList taskList;
    private Storage storage;
    private String filename;
    private UI ui;

    public Mario(String filename) {
        this.storage = new Storage();
        try{this.taskList = storage.loadMario(filename);}
        catch(Exception e){
            this.taskList = new TaskList();
        }
        this.filename = filename;
        this.ui = new UI();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        String response = "";
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                BaseCommand command = Parser.parse(fullCommand);
                response = command.execute(taskList);
                this.ui.printWithIndent(response);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.printError(e);
            } finally {
                storage.saveMario(taskList, MARIO_FILEPATH);
            }
        }
    }

    public static void main(String args[]) {
        Mario mario = new Mario(MARIO_FILEPATH);
        mario.run();
    }

    public void saveMario() {
        this.storage.saveMario(taskList, this.filename);
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

}

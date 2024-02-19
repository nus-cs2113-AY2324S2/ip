package gab;

import gab.command.Command;
import gab.command.CommandHandler;
import gab.exception.GabException;
import gab.storage.Storage;
import gab.task.TaskList;
import gab.ui.Ui;

import java.io.IOException;

public class Gab {
    public TaskList taskList; //taskList arraylist
    public static boolean isExit;
    public Storage storage;

    public Gab() {
        isExit = false;
        taskList = new TaskList();

        String filePath = "src/main/java/gab.txt";
        storage = new Storage(filePath);

        try {
            taskList = storage.loadTaskList();
        } catch (GabException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Gab gab = new Gab();
        Ui.printWelcome();

        while (!isExit) {
            try {
                String task = Ui.getTask();
                Command command = CommandHandler.checkCommand(task, gab.taskList);
                command.execute(task, gab.taskList);
                gab.storage.saveTask(gab.taskList);
            } catch (GabException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}



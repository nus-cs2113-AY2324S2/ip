package com.arriky.app;

import com.arriky.exception.ArrikyRuntimeException;
import com.arriky.task.TaskList;

import java.util.ArrayList;

public class Arriky {
    private TaskList taskList;

    public void run() {
        UI.greet();
        boolean isRunning = true;

        while(isRunning) {
            String rawCommand = UI.getCommand();
            UI.printSeparation();

            try {
                isRunning = Parser.parseCommand(rawCommand, taskList);
                UI.printSeparation();

                // get serializable from task list and save to local file
                ArrayList<String> linesToSave = taskList.getSerializableStrings();
                Storage.writeLinesToFile(linesToSave);
            } catch (ArrikyRuntimeException e) {
                UI.displayError(e.getMessage());
            }
        }
    }

    public Arriky() {
        try {
            ArrayList<String> savedLines = Storage.loadAllLines();
            taskList = new TaskList(savedLines);
            UI.displayImportSuccess();
        } catch (ArrikyRuntimeException e) {
            UI.displayError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Arriky().run();
    }
}

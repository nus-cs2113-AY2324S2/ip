package com.arriky.app;

import com.arriky.exception.ArrikyRuntimeException;
import com.arriky.task.TaskList;

import java.util.ArrayList;

/**
 * A CLI chatbot to assist the user manage daily tasks, including to-dos, deadlines, and events.
 * @author Songyue Wang
 * @version 1.0
 */
public class Arriky {
    private TaskList taskList;

    /**
     * Main operation logic of Arriky app.
     * Repetitively takes and executes user's input. Terminate when a termination instruction is received.
     */
    public void run() {
        UI.greet();
        boolean isRunning = true;

        while (isRunning) {
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

    /**
     * Constructor of Arriky class.
     * Instantiate required classes, including Storage manager and TaskList.
     */
    public Arriky() {
        // attempt to load tasklist from file. If failed, instantiate an empty one.
        try {
            ArrayList<String> savedLines = Storage.loadAllLines();
            taskList = new TaskList(savedLines);
            UI.displayImportSuccess();
        } catch (ArrikyRuntimeException e) {
            UI.displayError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Entry point of the app.
     * @param args Arguments given in the CLI
     */
    public static void main(String[] args) {
        new Arriky().run();
    }
}

package soot;
import soot.parser.Parser;
import soot.task.TaskList;
import soot.ui.UserUi;
import soot.storage.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Soot {
    private Storage storage;
    private TaskList tasks;
//    private Ui ui;

    public Soot(String savedFilePath) {
//        try {
            tasks = new TaskList();
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }
    public static void main(String[] args) {
        UserUi.showUserGreeting();

        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();
        try {
            ArrayList<String> savedFile = Storage.readSavedFile("saved-data/saved.txt");
            for (int i = 0; i < savedFile.size(); i++) {
                Storage.loadSavedTasks(savedFile.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file was found, i will create one immediately");
            UserUi.displayDividerLine();
            File savedData = new File("saved-data/saved.txt");
        }

        String line = in.nextLine();
        while (!line.equals("bye")) {
            UserUi.displayDividerLine();
            Parser.parseCommand(line);
            line = in.nextLine();
        }
        UserUi.displayDividerLine();

        try {
            Storage.saveFinalFile();
        } catch (IOException e) {
            System.out.println("there was a problem saving your tasks to the hard disk...");
        }
        UserUi.showGoodbyeMessage();
    }
}

package soot;
import soot.manager.CommandManager;
import soot.manager.SavedFileManager;
import soot.ui.UserUi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Soot {
    public static void main(String[] args) {
        UserUi.displayDividerLine(); //initial greeting
        UserUi.showUserGreeting();

        String line;
        Scanner in = new Scanner(System.in);
        CommandManager commandManager = new CommandManager();
        try {
            ArrayList<String> savedFile = SavedFileManager.readSavedFile("saved-data/saved.txt");
            for (int i = 0; i < savedFile.size(); i++) {
                SavedFileManager.loadSavedTasks(savedFile.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file was found, i will create one immediately");
            UserUi.displayDividerLine();
            File savedData = new File("saved-data/saved.txt");
        }

        boolean isBye = false;

        while (!isBye) {
            line = in.nextLine(); //user input
            UserUi.displayDividerLine();
            isBye = commandManager.isInputBye(line);
        }
        try {
            SavedFileManager.saveFinalFile();
        } catch (IOException e) {
            System.out.println("there was a problem saving your tasks to the hard disk...");
        }
        UserUi.showGoodbyeMessage();
    }
}

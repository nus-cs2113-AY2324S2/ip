package soot;
import soot.parser.Parser;
import soot.ui.UserUi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Soot {
    public static void main(String[] args) {
        UserUi.showUserGreeting();

        String line;
        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();
//        try {
//            ArrayList<String> savedFile = SavedFileManager.readSavedFile("saved-data/saved.txt");
//            for (int i = 0; i < savedFile.size(); i++) {
//                SavedFileManager.loadSavedTasks(savedFile.get(i));
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("No file was found, i will create one immediately");
//            UserUi.displayDividerLine();
//            File savedData = new File("saved-data/saved.txt");
//        }

        while (!in.equals("bye")) {
            line = in.nextLine(); //user input
            UserUi.displayDividerLine();
            Parser.parseCommand(line);
        }
        UserUi.showGoodbyeMessage();
    }
}

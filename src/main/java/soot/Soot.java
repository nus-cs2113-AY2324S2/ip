package soot;
import soot.manager.CommandManager;
import soot.manager.SavedFileManager;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Soot {
    public void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        CommandManager commandManager = new CommandManager();

        File savedData;
        try {
            ArrayList<String> savedFile = SavedFileManager.readSavedFile("../../../saved-data/saved.txt");
            for (int i = 0; i < savedFile.size(); i++) {
                commandManager.addSavedTask(savedFile.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, will be created immediately");
            savedData = new File("../../../saved-data/saved.txt");
        }

        boolean isBye = false;

        drawLine(); //initial greeting
        greetUser();

        while (!isBye) {
            line = in.nextLine(); //user input
            drawLine();
            isBye = commandManager.isInputBye(line);
        }
        greetGoodbye();
    }

    public static void drawLine() {
        int LINE_LENGTH = 60;
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }
    public static void greetUser() {
        System.out.println("Hello! I'm Soot, your personal chatbot companion :)");
        System.out.println("What can I help you with?");
        drawLine();
    }
    public static void greetGoodbye() {
        System.out.println("Bye! Till the next time we meet...");
        drawLine();
    }
}

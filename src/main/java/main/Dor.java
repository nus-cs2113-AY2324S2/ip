package main;

import java.io.File;
import java.io.IOException;
import logic.UserInterface;

public class Dor {
    public static void main(String[] args) {
        findData();
        System.out.println("Hello! I'm Dor");
        System.out.println("What can I do for you?");
        startUI();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void findData() {
        if (new File("./data").mkdir()) {
            System.out.println("data folder created");
        } else {
            System.out.println("data folder found");
        }
        try {
            if (new File("./data/dor.txt").createNewFile()) {
                System.out.println("dor.txt created");
            } else {
                System.out.println("dor.txt found");
            }
        } catch (IOException e) {
            System.out.println("ERROR: Could not create or find dor.txt!");
        }
    }

    public static void startUI() {
        UserInterface UI = new UserInterface();
        UI.loadDataIntoTaskManager();
        UI.processInput();
    }
}
